package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.ohmydog.requests.PaseadorRequest;
import org.acme.ohmydog.services.AuthService;
import org.acme.ohmydog.services.PaseadorService;

@Path("/paseador")
public class PaseadorController {

    @Inject
    PaseadorService paseadorService;

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("token") String token, PaseadorRequest paseadorRequest) {
        if ((authService.isLoggedIn(token) && (authService.esVeterinario()))) {
            if (paseadorService.register(paseadorRequest)) {
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
    public Response modificarPaseador(@HeaderParam("token") String token, @PathParam("id") Long id,
            PaseadorRequest paseadorRequest) {
        if (authService.isLoggedIn(token) && (authService.esVeterinario())) {
            if (paseadorService.modificarPaseador(id, paseadorRequest)) {
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
    public Response eliminarPaseador(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if (authService.isLoggedIn(token) && (authService.esVeterinario())) {
            if (paseadorService.eliminarPaseador(id)) {
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
    public Response listarPaseador() {
        return Response.ok(paseadorService.listarPaseador()).build();
    }

    // Endpoint para listar los borrados o no disponibles
    @GET
    @Path("/borrados")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarPaseadoresBorrados() {
        return Response.ok(paseadorService.listarPaseadoresBorrados()).build();
    }

    // Endpoint para recuperar los borrados
    @PUT
    @Path("/recover/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recuperar(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if (authService.isLoggedIn(token)) {
            if (paseadorService.recuperar(id)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    // Endpoint para listar los borrados o no disponibles
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerPaseadorPorId(@PathParam("id") Long id) {
        return Response.ok(paseadorService.buscarPaseadorPorId(id)).build();
    }
}
