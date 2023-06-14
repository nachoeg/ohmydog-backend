package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.ohmydog.excepciones.ExcepcionCastrado;
import org.acme.ohmydog.excepciones.ExcepcionTurno;
import org.acme.ohmydog.requests.TurnoRequest;
import org.acme.ohmydog.services.AuthService;
import org.acme.ohmydog.services.TurnoService;

@Path("/turnos")
public class TurnoController {

    @Inject
    TurnoService turnoService;

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("token") String token, TurnoRequest turnoRequest) {
        try {
            if (authService.isLoggedIn(token) && (authService.esCliente())) {
                if (turnoService.register(turnoRequest)) {
                    return Response.ok().build();
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
            }
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (ExcepcionCastrado e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (ExcepcionTurno e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT // Indica que se esta realizando una operación de actualizacion
    @Path("/modify/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarTurno(@HeaderParam("token") String token, @PathParam("id") Long id,
                                     TurnoRequest turnoRequest) {
        if (authService.isLoggedIn(token)) {
            if (turnoService.modificarTurno(id, turnoRequest.getIdPerro(), turnoRequest.getFecha(), turnoRequest.getMotivo(),
                    turnoRequest.getEstado())) {
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
    public Response listarTurnos(@HeaderParam("token") String token) {
        if (authService.isLoggedIn(token) && (authService.esVeterinario())) {
            return Response.ok(turnoService.listarTurnos()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/perro/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarTurnosPerro(@HeaderParam("token") String token, @HeaderParam("idCliente") Long idCliente, @PathParam("id") Long id) {
        if ((authService.isLoggedIn(token) && (authService.esVeterinario() || authService.estaAutorizado(idCliente)))) {
            return Response.ok(turnoService.listarTurnosPerro(id)).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/cliente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarTurnosCliente(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if ((authService.isLoggedIn(token)) && (authService.esVeterinario() || authService.estaAutorizado(id))) {
            return Response.ok(turnoService.listarTurnosUsuario(id)).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
