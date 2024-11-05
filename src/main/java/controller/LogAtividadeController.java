package controller;

import model.LogAtividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.LogAtividadeService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/logs-atividade")
public class LogAtividadeController {

    @Autowired
    private LogAtividadeService logAtividadeService;

    // Listar logs de atividade de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<EntityModel<LogAtividade>>> listarLogsPorUsuario(@PathVariable Long usuarioId) {
        List<LogAtividade> logs = logAtividadeService.listarLogsPorUsuario(usuarioId);

        List<EntityModel<LogAtividade>> logsModel = logs.stream()
                .map(log -> EntityModel.of(log,
                        linkTo(methodOn(LogAtividadeController.class).obterLog(log.getId())).withSelfRel(),
                        linkTo(methodOn(LogAtividadeController.class).listarLogsPorUsuario(usuarioId)).withRel("logs-usuario")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<LogAtividade>> resource = CollectionModel.of(logsModel,
                linkTo(methodOn(LogAtividadeController.class).listarLogsPorUsuario(usuarioId)).withSelfRel(),
                linkTo(methodOn(LogAtividadeController.class).listarLogsGeral()).withRel("todos-logs"));

        return ResponseEntity.ok(resource);
    }

    // Obter log de atividade por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<LogAtividade>> obterLog(@PathVariable Long id) {
        LogAtividade log = logAtividadeService.obterLogPorId(id);
        EntityModel<LogAtividade> resource = EntityModel.of(log,
                linkTo(methodOn(LogAtividadeController.class).obterLog(id)).withSelfRel(),
                linkTo(methodOn(LogAtividadeController.class).listarLogsGeral()).withRel("todos-logs"));

        return ResponseEntity.ok(resource);
    }

    // Listar todos os logs de atividade (geral)
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<LogAtividade>>> listarLogsGeral() {
        List<LogAtividade> logs = logAtividadeService.listarLogsGeral();

        List<EntityModel<LogAtividade>> logsModel = logs.stream()
                .map(log -> EntityModel.of(log,
                        linkTo(methodOn(LogAtividadeController.class).obterLog(log.getId())).withSelfRel(),
                        linkTo(methodOn(LogAtividadeController.class).listarLogsGeral()).withRel("todos-logs")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<LogAtividade>> resource = CollectionModel.of(logsModel,
                linkTo(methodOn(LogAtividadeController.class).listarLogsGeral()).withSelfRel());

        return ResponseEntity.ok(resource);
    }
}
