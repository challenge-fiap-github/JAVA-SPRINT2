package controller;

import model.LogFraude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/logs-fraude")
public class LogFraudeController {

    @Autowired
    private LogFraudeService logFraudeService;

    // Listar logs de fraude de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<LogFraude>> listarLogsFraudePorUsuario(@PathVariable Long usuarioId) {
        List<LogFraude> logs = logFraudeService.listarLogsPorUsuario(usuarioId);
        logs.forEach(log -> log.add(linkTo(methodOn(LogFraudeController.class).obterLogFraude(log.getId())).withSelfRel()));

        CollectionModel<LogFraude> resource = CollectionModel.of(logs,
                linkTo(methodOn(LogFraudeController.class).listarLogsFraudePorUsuario(usuarioId)).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    // Obter log de fraude por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<LogFraude>> obterLogFraude(@PathVariable Long id) {
        LogFraude log = logFraudeService.obterLogPorId(id);
        EntityModel<LogFraude> resource = EntityModel.of(log,
                linkTo(methodOn(LogFraudeController.class).obterLogFraude(id)).withSelfRel());

        return ResponseEntity.ok(resource);
    }
}
