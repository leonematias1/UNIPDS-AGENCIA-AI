package dev.ia;

import jakarta.enterprise.context.ApplicationScoped;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class BookingService {

    private final Map<Long, Booking> bookings = new HashMap<>();

    public BookingService() {
        bookings.put(12345L, new Booking(12345L, "Jhon Cena", "Tesouros do Egito",
                LocalDate.now().plusMonths(2),LocalDate.now().plusMonths(2).plusDays(15),BookingStatus.CONFIRMED, Category.TREASURES));
        bookings.put(8976L, new Booking(8976L, "Janet Jackson", "Aventura amazônia",
                LocalDate.now().plusMonths(2),LocalDate.now().plusMonths(2).plusDays(5),BookingStatus.CONFIRMED, Category.ADVENTURE));
        bookings.put(8234L, new Booking(8234L, "Mateus Sodré", "Aventura amazônia",
                LocalDate.now().plusMonths(5),LocalDate.now().plusMonths(5).plusDays(5),BookingStatus.CONFIRMED, Category.ADVENTURE));
    }

    public Optional<Booking> getBookingDetails(Long id) {
        return Optional.ofNullable(bookings.get(id));
    }

    public Optional<Booking> cancelBooking(Long id) {
        String currentUser = SecurityContext.getCurrentUser();
        if(bookings.containsKey(id)) {
            Booking booking = bookings.get(id);
            if(booking.customerName().equals(currentUser)){
                Booking cancelledBooking =
                        new Booking(
                            booking.id(),
                            booking.customerName(),
                            booking.destination(),
                            booking.startDate(),
                            booking.endDate(),
                            BookingStatus.CANCELLED,
                            booking.category());
                bookings.put(booking.id(), cancelledBooking);
                return Optional.of(cancelledBooking);
            }
        }
        return Optional.empty();
    }

    public List<Booking> findPackagesByCategory(Category category) {
        return bookings.values().stream()
                .filter(booking -> category.equals(category))
                .toList();
    }

}
