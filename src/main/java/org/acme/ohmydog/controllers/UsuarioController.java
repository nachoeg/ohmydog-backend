package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.ohmydog.requests.ModifyRequest;
import org.acme.ohmydog.requests.UsuarioRequest;
import org.acme.ohmydog.services.AuthService;
import org.acme.ohmydog.services.UsuarioService;

@Path("/usuarios")
public class UsuarioController {

    @Inject // Se utiliza para decirle a Quarkus que proporcione automaticamente el objeto en la app en tiempo de ejecucion
    UsuarioService usuarioService;

    @Inject
    AuthService authService;

    /**
     * Se encarga de recibir una solicitud HTTP POST con los datos de un nuevo usuario a ser registrado en la base de datos. El metodo recibe un objeto
     * UsuarioRequest que contiene los datos del nuevo usuario. Luego, el método llama al servicio de usuario para registrar el usuario correspondiente en la base
     * de datos y devuelve una respuesta HTTP segun si la operacion fue exitosa o no
     * @param usuarioRequest
     * @return Response
     */
    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("token") String token, UsuarioRequest usuarioRequest) {
        if (authService.isLoggedIn(token) && (authService.esVeterinario())) {
            if (usuarioService.register(usuarioRequest)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    /**
     * Se encarga de recibir una solicitud HTTP PUT con los datos de un usuario a ser actualizado en la base de datos. El metodo recibe un parámetro de ruta que
     * representa el ID del usuario a actualizar y un objeto UsuarioRequest que contiene los nuevos datos del usuario y llama al servicio de usuario para modificar
     * el usuario correspondiente en la base de datos. Devuelve una respuesta HTTP segun si la operacion fue exitosa o no
     * @param modifyRequest
     * @return Response
     */
    @PUT // Indica que se esta realizando una operación de actualizacion
    @Path("/modify/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarUsuario(@HeaderParam("token") String token, ModifyRequest modifyRequest) {
        if (authService.isLoggedIn(token)) {
            if (usuarioService.modificarUsuario(modifyRequest.getDni(), modifyRequest.getEmail(), modifyRequest.getLocalidad(),
                    modifyRequest.getDireccion(), modifyRequest.getTelefono())) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    /**
     * Se encarga de recibir una solicitud HTTP DELETE con el ID de un usuario que se desea eliminar de la base de datos. El metodo llama al servicio de
     * usuario para eliminar el usuario correspondiente en la base de datos y devuelve una respuesta HTTP segun si la operacion fue exitosa o no
     * @param token
     * @param id
     * @return Response
     */
    @DELETE // Indica que se esta realizando una operacion de eliminacion
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarUsuario(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if (authService.isLoggedIn(token) && (authService.esVeterinario())) {
            if (usuarioService.eliminarUsuario(id)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    /**
     * Devuelve una lista de todos los usuarios en la base de datos.
     *
     * @return Lista de usuarios
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarUsuarios(@HeaderParam("token") String token) {
        if (authService.isLoggedIn(token) && (authService.esVeterinario())) {
            return Response.ok(usuarioService.listarUsuarios()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

//    @GET
//    @Path("/turnos")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response listarTurnos(@HeaderParam("token") String token) {
//        if (authService.isLoggedIn(token) && (authService.esCliente())) {
//            return Response.ok(usuarioService.listarTurnos(authService.getUsuario())).build();
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).build();
//    }
}
