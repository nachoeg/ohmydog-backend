package org.acme.ohmydog.services;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import org.acme.ohmydog.repository.UsuarioRepository;
import org.acme.ohmydog.entities.Sesion;
import org.acme.ohmydog.entities.Usuario;
import javax.naming.AuthenticationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@SessionScoped
public class AuthService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    Sesion sesion;

    /**
     * Recibe como parametros un email y una contraseña, llama al metodo buscarUsuarioPorEmail de usuarioRepository para buscar un usuario con el email proporcionado,
     * si no se encuentra un usuario con ese email o si la contraseña no coincide con la del usuario encontrado, lanza una excepcion AuthenticationException con un
     * mensaje de error. Si el usuario es encontrado y la contraseña coincide, lo agrega a la sesion actual, genera un token JWT para dicha sesion y devulve la sesion.
     */
    public Sesion authenticate(String email, String password) throws AuthenticationException {
        Usuario usuario = usuarioRepository.buscarUsuarioPorEmail(email);
        if (usuario == null || !usuario.getPassword().equals(password)) {
            throw new AuthenticationException("Email o contraseña incorrecta");
        }
        sesion.setUsuario(usuario);
        sesion.setToken(generateToken(email));
        return sesion;
    }

    /**
     *  Genera un token JWT con una duración de validez de 12 horas.
     * @param email
     * @return token
     */
    private String generateToken(String email) {
//        LocalDateTime expirationTime = LocalDateTime.now().plusHours(12); // Token valido por 12 horas
//        Date expirationDate = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());
//        return Jwts.builder()
//                .setSubject(email)
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS256, "claveSecreta") // Clave secreta para firmar el token
//                .compact();
        return "0";
    }

    /**
     * Llama a la sesion actual y la borra
     */
    public void logout() {
        sesion.clear();
    }

    public boolean isLoggedIn(String token) {
        return sesion.isLoggedIn(token);
    }

    public boolean esVeterinario() { return this.sesion.esVeterinario(); }

}
