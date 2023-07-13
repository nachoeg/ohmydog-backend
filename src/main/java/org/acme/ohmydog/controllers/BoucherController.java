package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import org.acme.ohmydog.requests.CampaniaRequest;
import org.acme.ohmydog.requests.GuarderiaRequest;
import org.acme.ohmydog.services.AuthService;
import org.acme.ohmydog.services.BoucherService;

@Path("/bouchers")
public class BoucherController {
    @Inject
    BoucherService boucherService;

    @Inject
    AuthService authService;

    @PUT
    @Path("/agregarBoucher/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarBoucher(@HeaderParam("token") String token, @PathParam("dni") Long dni) {
        if (boucherService.agregarBoucher(dni)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/quemarBoucher/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response quemarBoucher(@HeaderParam("token") String token, @PathParam("dni") Long dni) {
        if (authService.isLoggedIn(token) && authService.esVeterinario()) {
            if (boucherService.quemarBoucher(dni)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
