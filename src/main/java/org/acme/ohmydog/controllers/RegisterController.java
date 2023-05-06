package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import org.acme.ohmydog.requests.UsuarioRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.ohmydog.services.UsuarioService;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegisterController {
    @Inject
    UsuarioService usuarioService;

    /**
     * Se encarga de recibir una solicitud HTTP POST con los datos de un nuevo usuario a ser registrado en la base de datos. El metodo recibe un objeto
     * UsuarioRequest que contiene los datos del nuevo usuario. Luego, el método llama al servicio de usuario para registrar el usuario correspondiente en la base
     * de datos y devuelve una respuesta HTTP segun si la operacion fue exitosa o no
     * @param usuarioRequest
     * @return Response
     */
    @POST
    public Response register(UsuarioRequest usuarioRequest) {
        if (usuarioService.register(usuarioRequest)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
