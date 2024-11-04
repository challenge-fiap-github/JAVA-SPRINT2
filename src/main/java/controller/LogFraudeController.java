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
import service.LogFraudeService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/logs-fraude")
public class LogFraudeController {

    @Autowired
    private LogFraudeService logFraudeService;

    // Listar logs de fraude de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<EntityModel<LogFraude>>> listarLogsFraudePorUsuario(@PathVariable Long usuarioId) {
        List<LogFraude> logs = logFraudeService.listarLogsPorUsuario(usuarioId);

        List<EntityModel<LogFraude>> logsModel = logs.stream()
                .map(log -> EntityModel.of(log,
                        linkTo(methodOn(LogFraudeController.class).obterLogFraude(log.getId())).withSelfRel()))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<LogFraude>> resource = CollectionModel.of(logsModel,
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
