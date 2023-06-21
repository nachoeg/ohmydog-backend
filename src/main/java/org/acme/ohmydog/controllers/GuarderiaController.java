package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.ohmydog.requests.GuarderiaRequest;
import org.acme.ohmydog.services.AuthService;
import org.acme.ohmydog.services.GuarderiaService;

@Path("/guarderia")
public class GuarderiaController {

    @Inject
    GuarderiaService guarderiaService;

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("token") String token, GuarderiaRequest guarderiaRequest) {
        if ((authService.isLoggedIn(token) && (authService.esVeterinario()))) {
            if (guarderiaService.register(guarderiaRequest)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
