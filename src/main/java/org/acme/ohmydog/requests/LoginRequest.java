package org.acme.ohmydog.requests;

// LoginRequest encapsula la informacion de inicio de sesion del usuario, se utiliza para autenticar al usuario en el sistema
public class LoginRequest {
    private String email;
    private String contrasena;

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }
}
