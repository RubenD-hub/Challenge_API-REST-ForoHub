package ForoHub.api.domain.respuesta;

import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String mensaje,
        String autor,
        LocalDateTime fechaCreacion
) {}
