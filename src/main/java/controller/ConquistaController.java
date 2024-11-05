package controller;

import model.Conquista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ConquistaService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/conquistas")
public class ConquistaController {

    @Autowired
    private ConquistaService conquistaService;

    // Listar conquistas disponíveis
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Conquista>>> listarConquistas() {
        List<Conquista> conquistas = conquistaService.listarConquistas();

        List<EntityModel<Conquista>> conquistasModel = conquistas.stream()
                .map(c -> EntityModel.of(c,
                        linkTo(methodOn(ConquistaController.class).obterConquista(c.getId())).withSelfRel(),
                        linkTo(methodOn(ConquistaController.class).listarConquistas()).withRel("conquistas")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Conquista>> resource = CollectionModel.of(conquistasModel,
                linkTo(methodOn(ConquistaController.class).listarConquistas()).withSelfRel(),
                linkTo(methodOn(ConquistaController.class).listarConquistasPorUsuario(1L)).withRel("conquistas-usuario"));

        return ResponseEntity.ok(resource);
    }

    // Obter conquista por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Conquista>> obterConquista(@PathVariable Long id) {
        Conquista conquista = conquistaService.obterConquistaPorId(id);
        EntityModel<Conquista> resource = EntityModel.of(conquista,
                linkTo(methodOn(ConquistaController.class).obterConquista(id)).withSelfRel(),
                linkTo(methodOn(ConquistaController.class).listarConquistas()).withRel("conquistas"),
                linkTo(methodOn(ConquistaController.class).listarConquistasPorUsuario(1L)).withRel("conquistas-usuario"));

        return ResponseEntity.ok(resource);
    }

    // Obter conquistas de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<EntityModel<Conquista>>> listarConquistasPorUsuario(@PathVariable Long usuarioId) {
        List<Conquista> conquistas = conquistaService.listarConquistasPorUsuario(usuarioId);

        List<EntityModel<Conquista>> conquistasModel = conquistas.stream()
                .map(c -> EntityModel.of(c,
                        linkTo(methodOn(ConquistaController.class).obterConquista(c.getId())).withSelfRel(),
                        linkTo(methodOn(ConquistaController.class).listarConquistas()).withRel("conquistas")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Conquista>> resource = CollectionModel.of(conquistasModel,
                linkTo(methodOn(ConquistaController.class).listarConquistasPorUsuario(usuarioId)).withSelfRel(),
                linkTo(methodOn(ConquistaController.class).listarConquistas()).withRel("todas-conquistas"));

        return ResponseEntity.ok(resource);
    }
}
