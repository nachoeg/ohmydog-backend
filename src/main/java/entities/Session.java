package entities;

public class Session {
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void clear() {
        usuario = null;
    }

    public boolean isLoggedIn() {
        return usuario != null;
    }
}
