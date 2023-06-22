package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.ohmydog.requests.GuarderiaRequest;
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
    @PUT
    @Path("/modify/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarGuarderia(@HeaderParam("token") String token, @PathParam("id") Long id,
                                      GuarderiaRequest guarderiaRequest) {
        if (authService.isLoggedIn(token)) {
            if (guarderiaService.modificarGuarderia(id, guarderiaRequest)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarGuarderia(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if (authService.isLoggedIn(token) && (authService.esVeterinario())) {
            if (guarderiaService.eliminarGuarderia(id)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarGuarderia(@HeaderParam("token") String token) {
        if (authService.isLoggedIn(token)) {
            return Response.ok(guarderiaService.listarGuarderia()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
