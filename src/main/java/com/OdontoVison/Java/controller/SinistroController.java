package com.OdontoVison.Java.controller;

import com.OdontoVison.Java.model.Sinistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.OdontoVison.Java.service.SinistroService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/sinistros")
public class SinistroController {

    @Autowired
    private SinistroService sinistroService;

    // Listar sinistros de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<EntityModel<Sinistro>>> listarSinistrosPorUsuario(@PathVariable Long usuarioId) {
        List<Sinistro> sinistros = sinistroService.listarSinistrosPorUsuario(usuarioId);

        List<EntityModel<Sinistro>> sinistrosModel = sinistros.stream()
                .map(sinistro -> EntityModel.of(sinistro,
                        linkTo(methodOn(SinistroController.class).obterSinistro(sinistro.getId())).withSelfRel(),
                        linkTo(methodOn(SinistroController.class).listarSinistrosPorUsuario(usuarioId)).withRel("sinistros-usuario"),
                        linkTo(methodOn(SinistroController.class).registrarSinistro(sinistro)).withRel("registrar-sinistro")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Sinistro>> resource = CollectionModel.of(sinistrosModel,
                linkTo(methodOn(SinistroController.class).listarSinistrosPorUsuario(usuarioId)).withSelfRel(),
                linkTo(methodOn(SinistroController.class).registrarSinistro(null)).withRel("registrar-sinistro"));

        return ResponseEntity.ok(resource);
    }

    // Obter sinistro por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Sinistro>> obterSinistro(@PathVariable Long id) {
        Sinistro sinistro = sinistroService.obterSinistroPorId(id);
        EntityModel<Sinistro> resource = EntityModel.of(sinistro,
                linkTo(methodOn(SinistroController.class).obterSinistro(id)).withSelfRel(),
                linkTo(methodOn(SinistroController.class).listarSinistrosPorUsuario(sinistro.getUsuarioId())).withRel("sinistros-usuario"),
                linkTo(methodOn(SinistroController.class).registrarSinistro(sinistro)).withRel("registrar-sinistro"));

        return ResponseEntity.ok(resource);
    }

    // Registrar novo sinistro
    @PostMapping
    public ResponseEntity<EntityModel<Sinistro>> registrarSinistro(@RequestBody Sinistro sinistro) {
        Sinistro novoSinistro = sinistroService.registrarSinistro(sinistro);
        EntityModel<Sinistro> resource = EntityModel.of(novoSinistro,
                linkTo(methodOn(SinistroController.class).obterSinistro(novoSinistro.getId())).withSelfRel(),
                linkTo(methodOn(SinistroController.class).listarSinistrosPorUsuario(novoSinistro.getUsuarioId())).withRel("sinistros-usuario"));

        return ResponseEntity.created(linkTo(methodOn(SinistroController.class).obterSinistro(novoSinistro.getId())).toUri()).body(resource);
    }
}
