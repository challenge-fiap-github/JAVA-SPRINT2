package com.OdontoVison.Java.controller;

import com.OdontoVison.Java.model.Nivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.OdontoVison.Java.service.NivelService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/niveis")
public class NivelController {

    @Autowired
    private NivelService nivelService;

    // Listar níveis disponíveis
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Nivel>>> listarNiveis() {
        List<Nivel> niveis = nivelService.listarNiveis();

        List<EntityModel<Nivel>> niveisModel = niveis.stream()
                .map(nivel -> EntityModel.of(nivel,
                        linkTo(methodOn(NivelController.class).obterNivel(nivel.getId())).withSelfRel(),
                        linkTo(methodOn(NivelController.class).listarNiveis()).withRel("niveis")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Nivel>> resource = CollectionModel.of(niveisModel,
                linkTo(methodOn(NivelController.class).listarNiveis()).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    // Obter nível por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Nivel>> obterNivel(@PathVariable Long id) {
        Nivel nivel = nivelService.obterNivelPorId(id);
        EntityModel<Nivel> resource = EntityModel.of(nivel,
                linkTo(methodOn(NivelController.class).obterNivel(id)).withSelfRel(),
                linkTo(methodOn(NivelController.class).listarNiveis()).withRel("niveis"));

        return ResponseEntity.ok(resource);
    }
}
