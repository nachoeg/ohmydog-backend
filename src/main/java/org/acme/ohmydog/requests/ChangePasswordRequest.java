package org.acme.ohmydog.requests;

public class ChangePasswordRequest {
    private String contrasenaVieja;
    private String contrasenaNueva;
    private String contrasenaConfirmacion;

    public String getContrasenaVieja() {
        return contrasenaVieja;
    }

    public String getContrasenaNueva() {
        return contrasenaNueva;
    }

    public String getContrasenaConfirmacion() {
        return contrasenaConfirmacion;
    }
}
