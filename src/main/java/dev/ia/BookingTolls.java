package dev.ia;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BookingTolls {

    @Inject
    BookingService bookingService;

    @Tool("Obtém os detalhes completos de uma reserva com base em seu número de identificação (bookingId)")
    public String getBookingDetails(long bookingId) {
        return bookingService.getBookingDetails(bookingId)
                .map(Booking::toString)
                .orElse("Reserva com o ID "+bookingId+ " Não encontrada.");
    }

    @Tool("""
          Cancela uma reverva existente.
          Para confirmar o cancelamento, é necessário fornecer o ID da reserva (bookingId)
          """)
    public String cancelBooking(long bookingId) {
        return bookingService.cancelBooking(bookingId)
                .map(booking -> "Reserva "+bookingId+" Cancelada com sucesso. Status atual: "+booking.status())
                .orElse("Não foi possível cancelar a reserva.");
    }

}
