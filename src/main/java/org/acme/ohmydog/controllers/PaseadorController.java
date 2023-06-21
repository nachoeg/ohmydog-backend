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
}
