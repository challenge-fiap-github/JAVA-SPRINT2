package controller;

import model.Recompensa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.RecompensaService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/recompensas")
public class RecompensaController {

    @Autowired
    private RecompensaService recompensaService;

    // Listar recompensas disponíveis
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Recompensa>>> listarRecompensasDisponiveis() {
        List<Recompensa> recompensas = recompensaService.listarRecompensasDisponiveis();

        List<EntityModel<Recompensa>> recompensasModel = recompensas.stream()
                .map(recompensa -> EntityModel.of(recompensa,
                        linkTo(methodOn(RecompensaController.class).obterRecompensa(recompensa.getId())).withSelfRel(),
                        linkTo(methodOn(RecompensaController.class).listarRecompensasDisponiveis()).withRel("recompensas"),
                        linkTo(methodOn(RecompensaController.class).resgatarRecompensa(recompensa.getId(), null)).withRel("resgatar-recompensa")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Recompensa>> resource = CollectionModel.of(recompensasModel,
                linkTo(methodOn(RecompensaController.class).listarRecompensasDisponiveis()).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    // Obter recompensa por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Recompensa>> obterRecompensa(@PathVariable Long id) {
        Recompensa recompensa = recompensaService.obterRecompensaPorId(id);
        EntityModel<Recompensa> resource = EntityModel.of(recompensa,
                linkTo(methodOn(RecompensaController.class).obterRecompensa(id)).withSelfRel(),
                linkTo(methodOn(RecompensaController.class).listarRecompensasDisponiveis()).withRel("recompensas"),
                linkTo(methodOn(RecompensaController.class).resgatarRecompensa(id, null)).withRel("resgatar-recompensa"));

        return ResponseEntity.ok(resource);
    }

    // Resgatar recompensa
    @PostMapping("/{id}/resgatar/usuario/{usuarioId}")
    public ResponseEntity<EntityModel<Recompensa>> resgatarRecompensa(@PathVariable Long id, @PathVariable Long usuarioId) {
        Recompensa recompensaResgatada = recompensaService.resgatarRecompensa(usuarioId, id);
        EntityModel<Recompensa> resource = EntityModel.of(recompensaResgatada,
                linkTo(methodOn(RecompensaController.class).obterRecompensa(id)).withSelfRel(),
                linkTo(methodOn(RecompensaController.class).listarRecompensasDisponiveis()).withRel("recompensas"));

        return ResponseEntity.ok(resource);
    }
}
