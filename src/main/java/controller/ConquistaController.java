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

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/conquistas")
public class ConquistaController {

    @Autowired
    private ConquistaService conquistaService;

    // Listar conquistas disponíveis
    @GetMapping
    public ResponseEntity<CollectionModel<Conquista>> listarConquistas() {
        List<Conquista> conquistas = conquistaService.listarConquistas();
        conquistas.forEach(c -> c.add(linkTo(methodOn(ConquistaController.class).obterConquista(c.getId())).withSelfRel()));

        CollectionModel<Conquista> resource = CollectionModel.of(conquistas,
                linkTo(methodOn(ConquistaController.class).listarConquistas()).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    // Obter conquista por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Conquista>> obterConquista(@PathVariable Long id) {
        Conquista conquista = conquistaService.obterConquistaPorId(id);
        EntityModel<Conquista> resource = EntityModel.of(conquista,
                linkTo(methodOn(ConquistaController.class).obterConquista(id)).withSelfRel(),
                linkTo(methodOn(ConquistaController.class).listarConquistas()).withRel("conquistas"));

        return ResponseEntity.ok(resource);
    }

    // Obter conquistas de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<Conquista>> listarConquistasPorUsuario(@PathVariable Long usuarioId) {
        List<Conquista> conquistas = conquistaService.listarConquistasPorUsuario(usuarioId);
        conquistas.forEach(c -> c.add(linkTo(methodOn(ConquistaController.class).obterConquista(c.getId())).withSelfRel()));

        CollectionModel<Conquista> resource = CollectionModel.of(conquistas,
                linkTo(methodOn(ConquistaController.class).listarConquistasPorUsuario(usuarioId)).withSelfRel());

        return ResponseEntity.ok(resource);
    }
}
