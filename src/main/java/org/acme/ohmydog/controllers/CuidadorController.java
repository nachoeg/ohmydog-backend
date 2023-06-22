package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.ohmydog.requests.CuidadorRequest;
import org.acme.ohmydog.services.AuthService;
import org.acme.ohmydog.services.CuidadorService;

@Path("/cuidador")
public class CuidadorController {

    @Inject
    CuidadorService cuidadorService;

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("token") String token, CuidadorRequest cuidadorRequest) {
        if ((authService.isLoggedIn(token) && (authService.esVeterinario()))) {
            if (cuidadorService.register(cuidadorRequest)) {
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
    public Response modificarCuidador(@HeaderParam("token") String token, @PathParam("id") Long id,
                                     CuidadorRequest cuidadorRequest) {
        if (authService.isLoggedIn(token)) {
            if (cuidadorService.modificarCuidador(id, cuidadorRequest)) {
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
    public Response eliminarCuidador(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if (authService.isLoggedIn(token) && (authService.esVeterinario())) {
            if (cuidadorService.eliminarCuidador(id)) {
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
    public Response listarCuidador(@HeaderParam("token") String token) {
        if (authService.isLoggedIn(token)) {
            return Response.ok(cuidadorService.listarCuidador()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
