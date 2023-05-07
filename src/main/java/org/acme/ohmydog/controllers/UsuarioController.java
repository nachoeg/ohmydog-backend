package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.ohmydog.entities.Usuario;
import org.acme.ohmydog.requests.UsuarioRequest;
import org.acme.ohmydog.services.UsuarioService;

import java.util.List;

@Path("/usuarios")
public class UsuarioController {

    @Inject
    // Se utiliza para decirle a Quarkus que proporcione automaticamente el objeto en la app en tiempo de ejecucion
    UsuarioService usuarioService;

    /**
     * Se encarga de recibir una solicitud HTTP PUT con los datos de un usuario a ser actualizado en la base de datos. El metodo recibe un parámetro de ruta que
     * representa el ID del usuario a actualizar y un objeto UsuarioRequest que contiene los nuevos datos del usuario y llama al servicio de usuario para modificar
     * el usuario correspondiente en la base de datos. Devuelve una respuesta HTTP segun si la operacion fue exitosa o no
     * @param id
     * @param usuarioRequest
     * @return Response
     */
    @PUT // Indica que se esta realizando una operación de actualizacion
    @Path("/modify/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarUsuario(@PathParam("id") Long id, UsuarioRequest usuarioRequest) { // @PathParam para obtener el valor del parametro "id" de la URL
        if (usuarioService.modificarUsuario(id, usuarioRequest.getEmail(), usuarioRequest.getPassword(), usuarioRequest.getNombre(),
                usuarioRequest.getApellido(), usuarioRequest.getDni(), usuarioRequest.getLocalidad(), usuarioRequest.getDireccion(), usuarioRequest.getTelefono())) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     * Se encarga de recibir una solicitud HTTP DELETE con el ID de un usuario que se desea eliminar de la base de datos. El metodo llama al servicio de
     * usuario para eliminar el usuario correspondiente en la base de datos y devuelve una respuesta HTTP segun si la operacion fue exitosa o no
     * @param id
     * @return Response
     */
    @DELETE // Indica que se esta realizando una operacion de eliminacion
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarUsuario(@PathParam("id") Long id) {
        if (usuarioService.eliminarUsuario(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     * Devuelve una lista de todos los usuarios en la base de datos.
     *
     * @return Lista de usuarios
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        return Response.ok(usuarioService.listarUsuarios()).build();
    }
}
