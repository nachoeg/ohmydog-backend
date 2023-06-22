package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.ohmydog.requests.PerroPerdidoRequest;
import org.acme.ohmydog.services.AuthService;
import org.acme.ohmydog.services.PerroPerdidoService;

@Path("/perdidos")
public class PerroPerdidoController {

    @Inject
    PerroPerdidoService perroPerdidoService;

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(PerroPerdidoRequest perroPerdidoRequest) {
        if (perroPerdidoService.register(perroPerdidoRequest)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarPerroPerdido(@PathParam("id") Long id) {
        if (perroPerdidoService.eliminarPerroPerdido(id)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/modify/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarPerroPerdido(@HeaderParam("token") String token, @PathParam("id") Long id,
                                   PerroPerdidoRequest perroPerdidoRequest) {
        if (authService.isLoggedIn(token)) {
            if (perroPerdidoService.modificarPerroPerdido(id, perroPerdidoRequest)) {
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
    public Response listarPerrosPerdidos() {
        return Response.ok(perroPerdidoService.listarPerrosPerdidos()).build();
    }
}
