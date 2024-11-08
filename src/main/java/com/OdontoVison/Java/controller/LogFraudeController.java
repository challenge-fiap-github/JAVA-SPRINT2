package com.OdontoVison.Java.controller;

import com.OdontoVison.Java.model.LogFraude;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.OdontoVison.Java.service.LogFraudeService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/logs-fraude")
public class LogFraudeController {

    private final LogFraudeService logFraudeService;

    public LogFraudeController(LogFraudeService logFraudeService) {
        this.logFraudeService = logFraudeService;
    }

    // Listar logs de fraude de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<EntityModel<LogFraude>>> listarLogsFraudePorUsuario(@PathVariable Long usuarioId) {
        List<LogFraude> logs = logFraudeService.listarLogsPorUsuario(usuarioId);

        List<EntityModel<LogFraude>> logsModel = logs.stream()
                .map(log -> EntityModel.of(log,
                        linkTo(methodOn(LogFraudeController.class).obterLogFraude(log.getId())).withSelfRel(),
                        linkTo(methodOn(LogFraudeController.class).listarLogsFraudePorUsuario(usuarioId)).withRel("logs-fraude-usuario")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<LogFraude>> resource = CollectionModel.of(logsModel,
                linkTo(methodOn(LogFraudeController.class).listarLogsFraudePorUsuario(usuarioId)).withSelfRel(),
                linkTo(methodOn(LogFraudeController.class).listarLogsFraudeGeral()).withRel("todos-logs-fraude"));

        return ResponseEntity.ok(resource);
    }

    // Obter log de fraude por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<LogFraude>> obterLogFraude(@PathVariable Long id) {
        LogFraude log = logFraudeService.obterLogPorId(id);
        EntityModel<LogFraude> resource = EntityModel.of(log,
                linkTo(methodOn(LogFraudeController.class).obterLogFraude(id)).withSelfRel(),
                linkTo(methodOn(LogFraudeController.class).listarLogsFraudeGeral()).withRel("todos-logs-fraude"));

        return ResponseEntity.ok(resource);
    }

    // Listar todos os logs de fraude (geral)
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<LogFraude>>> listarLogsFraudeGeral() {
        List<LogFraude> logs = logFraudeService.listarLogsGeral();

        List<EntityModel<LogFraude>> logsModel = logs.stream()
                .map(log -> EntityModel.of(log,
                        linkTo(methodOn(LogFraudeController.class).obterLogFraude(log.getId())).withSelfRel(),
                        linkTo(methodOn(LogFraudeController.class).listarLogsFraudeGeral()).withRel("todos-logs-fraude")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<LogFraude>> resource = CollectionModel.of(logsModel,
                linkTo(methodOn(LogFraudeController.class).listarLogsFraudeGeral()).withSelfRel());

        return ResponseEntity.ok(resource);
    }
}

