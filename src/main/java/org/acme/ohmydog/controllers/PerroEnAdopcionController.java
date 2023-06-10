package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.ohmydog.requests.PerroEnAdopcionRequest;
import org.acme.ohmydog.services.PerroEnAdopcionService;
import org.acme.ohmydog.services.AuthService;

@Path("/adopciones")
public class PerroEnAdopcionController {

    @Inject
    PerroEnAdopcionService perroEnAdopcionService;

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("token") String token, PerroEnAdopcionRequest perroEnAdopcionRequest) {
        if ((authService.isLoggedIn(token) && (authService.esCliente()))) {
            if (perroEnAdopcionService.register(perroEnAdopcionRequest)) {
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
    public Response listarPerrosEnAdopcion(@HeaderParam("token") String token) {
            return Response.ok(perroEnAdopcionService.listarPerrosEnAdopcion()).build();
    }
}

