package ForoHub.api.domain.usuario;

public record DatosAutenticacionUsuario(
        String login,
        String clave,
        String role) {
}
