package ForoHub.api.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    List<Topico> findByCurso_NombreAndFechaCreacionBetween(String curso, LocalDateTime inicio, LocalDateTime fin);
}
