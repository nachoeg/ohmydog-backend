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

    @PUT
    @Path("/modify/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarPerro(@HeaderParam("token") String token, @PathParam("id") Long id,
            PerroRequest perroRequest) {
        if (authService.isLoggedIn(token)) {
            if (perroService.modificarPerro(id, perroRequest.getNombre(), perroRequest.getRaza(),
                    perroRequest.getEdad(), perroRequest.getEnfermedad(), perroRequest.getSexo(),
                    perroRequest.getCaracteristicas(), perroRequest.getCastrado(), perroRequest.getVacunaAntirrabica(),
                    perroRequest.getVacunaAntienfermedades())) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @DELETE // Indica que se esta realizando una operacion de eliminacion
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarPerro(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if (authService.isLoggedIn(token) && (authService.esVeterinario())) {
            if (perroService.eliminarPerro(id)) {
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
    public Response listarPerros(@HeaderParam("token") String token) {
        if (authService.isLoggedIn(token) && (authService.esVeterinario())) {
            return Response.ok(perroService.listarPerros()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarPerrosCliente(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if (authService.isLoggedIn(token)) {
            return Response.ok(perroService.listarPerrosCliente(id)).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/perrosBorrados")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarPerrosBorrados(@HeaderParam("token") String token) {
        if (authService.isLoggedIn(token)) {
            return Response.ok(perroService.listarPerrosBorrados()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/perroPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPerroPorId(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if (authService.isLoggedIn(token)) {
            return Response.ok(perroService.buscarPerroPorId(id)).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("/cruzar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cambiarDisponibilidadDeCruza(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if (authService.isLoggedIn(token)) {
            if (perroService.cruzar(id)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/disponiblesCruza/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPerrosParaCruzaPorDuenio(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if (authService.isLoggedIn(token)) {
            return Response.ok(perroService.getPerrosDisponibleParaCruza(id)).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/disponiblesCruza")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTodosLosPerrosDisponibleParaCruza(@HeaderParam("token") String token) {
        return Response.ok(perroService.getTodosLosPerrosDisponibleParaCruza()).build();
    }

    @GET
    @Path("/opcionesCruza/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getOpcionesCruza(@HeaderParam("token") String token, @PathParam("id") Long id) {
        return Response.ok(perroService.getOpcionesCruza(id)).build();
    }

    // Endpoint para recuperar las campanias borradas
    @PUT
    @Path("/recover/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recuperarPerro(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if ((authService.isLoggedIn(token)) && (authService.esVeterinario())) {
            if (perroService.recuperarPerro(id)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
