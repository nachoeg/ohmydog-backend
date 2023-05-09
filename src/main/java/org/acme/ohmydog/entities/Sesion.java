package org.acme.ohmydog.entities;


import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

@SessionScoped
public class Sesion implements Serializable {
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void clear() {
        usuario = null;
    }

    public boolean isLoggedIn() {
        return usuario != null;
    }
}
