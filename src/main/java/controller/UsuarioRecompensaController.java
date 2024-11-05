package controller;

import model.UsuarioRecompensa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UsuarioRecompensaService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/recompensas")
public class UsuarioRecompensaController {

    @Autowired
    private UsuarioRecompensaService usuarioRecompensaService;

    // Listar recompensas resgatadas pelo usuário
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<UsuarioRecompensa>>> listarRecompensasResgatadas(@PathVariable Long usuarioId) {
        List<UsuarioRecompensa> recompensas = usuarioRecompensaService.listarRecompensasPorUsuario(usuarioId);

        List<EntityModel<UsuarioRecompensa>> recompensasModel = recompensas.stream()
                .map(recompensa -> EntityModel.of(recompensa,
                        linkTo(methodOn(UsuarioRecompensaController.class).obterRecompensaResgatada(usuarioId, recompensa.getId())).withSelfRel(),
                        linkTo(methodOn(UsuarioRecompensaController.class).listarRecompensasResgatadas(usuarioId)).withRel("recompensas-usuario")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<UsuarioRecompensa>> resource = CollectionModel.of(recompensasModel,
                linkTo(methodOn(UsuarioRecompensaController.class).listarRecompensasResgatadas(usuarioId)).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    // Obter recompensa resgatada por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioRecompensa>> obterRecompensaResgatada(@PathVariable Long usuarioId, @PathVariable Long id) {
        UsuarioRecompensa recompensa = usuarioRecompensaService.obterRecompensaResgatadaPorId(id);
        EntityModel<UsuarioRecompensa> resource = EntityModel.of(recompensa,
                linkTo(methodOn(UsuarioRecompensaController.class).obterRecompensaResgatada(usuarioId, id)).withSelfRel(),
                linkTo(methodOn(UsuarioRecompensaController.class).listarRecompensasResgatadas(usuarioId)).withRel("recompensas-usuario"));

        return ResponseEntity.ok(resource);
    }
}
