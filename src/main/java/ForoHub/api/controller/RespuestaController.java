package ForoHub.api.controller;

import ForoHub.api.domain.respuesta.DatosListadoRespuesta;
import ForoHub.api.domain.respuesta.DatosRegistroRespuesta;
import ForoHub.api.domain.respuesta.Respuesta;
import ForoHub.api.domain.respuesta.RespuestaRepository;
import ForoHub.api.domain.topico.TopicoRepository;
import ForoHub.api.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> responder(@RequestBody @Valid DatosRegistroRespuesta datos,
                                       @AuthenticationPrincipal Usuario usuario) {

        var topico = topicoRepository.findById(datos.topicoId())
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        var respuesta = new Respuesta();
        respuesta.setMensaje(datos.mensaje());
        respuesta.setTopico(topico);
        respuesta.setAutor(usuario);

        respuestaRepository.save(respuesta);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Respuesta registrada correctamente");
    }

    @GetMapping("/{id}/respuestas")
    public ResponseEntity<List<DatosListadoRespuesta>> listarRespuestas(@PathVariable Long id) {
        var respuestas = respuestaRepository.findByTopicoId(id)
                .stream()
                .map(respuesta -> new DatosListadoRespuesta(
                        respuesta.getId(),
                        respuesta.getMensaje(),
                        respuesta.getAutor().getLogin(),
                        respuesta.getFechaCreacion()
                )).toList();

        return ResponseEntity.ok(respuestas);
    }
}
