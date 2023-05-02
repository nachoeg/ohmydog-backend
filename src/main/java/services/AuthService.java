package services;

import com.oracle.svm.core.annotate.Inject;
import entities.Session;
import entities.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import repository.UsuarioRepository;

import javax.naming.AuthenticationException;

@ApplicationScoped // Asegura que el objeto se inicialice solo una vez y se reutilice en toda la aplicacion
public class AuthService {

    @Inject
    private UsuarioRepository usuarioRepository;

    @Inject
    private Session session;

    /**
     * Recibe como parametros un email y una contraseña, llama al metodo buscarUsuarioPorEmail de usuarioRepository para buscar un usuario con el email proporcionado,
     * si no se encuentra un usuario con ese email o si la contraseña no coincide con la del usuario encontrado, lanza una excepcion AuthenticationException con un
     * mensaje de error. Si el usuario es encontrado y la contraseña coincide, lo agrega a la sesion actual
     */
    public void authenticate(String email, String contrasena) throws AuthenticationException {
        Usuario usuario = usuarioRepository.buscarUsuarioPorEmail(email);
        if (usuario == null || !contrasena.equals(usuario.getContrasena())) {
            throw new AuthenticationException("Email o contraseña incorrecta");
        }
        session.setUsuario(usuario);
    }

    /**
     * Llama a la sesion actual y la borra
     */
    public void logout() {
        session.clear();
    }
}
