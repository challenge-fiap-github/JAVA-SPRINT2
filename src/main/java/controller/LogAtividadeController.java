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

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/logs-atividade")
public class LogAtividadeController {

    @Autowired
    private LogAtividadeService logAtividadeService;

    // Listar logs de atividade de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<LogAtividade>> listarLogsPorUsuario(@PathVariable Long usuarioId) {
        List<LogAtividade> logs = logAtividadeService.listarLogsPorUsuario(usuarioId);
        logs.forEach(log -> log.add(linkTo(methodOn(LogAtividadeController.class).obterLog(log.getId())).withSelfRel()));

        CollectionModel<LogAtividade> resource = CollectionModel.of(logs,
                linkTo(methodOn(LogAtividadeController.class).listarLogsPorUsuario(usuarioId)).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    // Obter log de atividade por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<LogAtividade>> obterLog(@PathVariable Long id) {
        LogAtividade log = logAtividadeService.obterLogPorId(id);
        EntityModel<LogAtividade> resource = EntityModel.of(log,
                linkTo(methodOn(LogAtividadeController.class).obterLog(id)).withSelfRel());

        return ResponseEntity.ok(resource);
    }
}
