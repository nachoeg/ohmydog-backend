package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.ohmydog.requests.PerroRequest;
import org.acme.ohmydog.services.PerroService;
import org.acme.ohmydog.services.AuthService;

@Path("/perros")
public class PerroController {

    @Inject
    PerroService perroService;

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("token") String token, PerroRequest perroRequest) {
        if ((authService.isLoggedIn(token) && (authService.esVeterinario()))) {
            if (perroService.register(perroRequest)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
         return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarPerros(@HeaderParam("token") String token) {
        if (authService.isLoggedIn(token) && (authService.esVeterinario())) {
            return Response.ok(perroService.listarPerros()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}

