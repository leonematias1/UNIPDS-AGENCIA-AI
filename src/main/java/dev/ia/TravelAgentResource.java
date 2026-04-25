package dev.ia;

import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


@Path("/travel")
public class TravelAgentResource {

    @Inject
    PackageExpert packageExpert;
    //TravelAgentAssistant travelAgentAssistant;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String ask(String question, @HeaderParam("X-User-Name") String userName) {
        if(userName != null && !userName.isEmpty()){
            try{
                SecurityContext.setCurrentUser(userName);
                return packageExpert.chat(userName, question);
            }finally {
                SecurityContext.clear();
            }
        }else{
            return "Usuário precisa estar autenticado!";
        }
        //return packageExpert.chat("session-123",question);
        //return travelAgentAssistant.chat(question);
    }


}
