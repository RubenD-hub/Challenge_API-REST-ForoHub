package ForoHub.api.domain.topico;

public record DatosTopico(
        Long id,
        String titulo,
        String mensaje,
        String status,
        String autor,
        String curso
) {
}
