package com.OdontoVison.Java.controller;

import com.OdontoVison.Java.model.HistoricoPontuacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.OdontoVison.Java.service.HistoricoPontuacaoService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/historico-pontuacao")
public class HistoricoPontuacaoController {

    @Autowired
    private HistoricoPontuacaoService historicoPontuacaoService;

    // Listar histórico de pontuação de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<EntityModel<HistoricoPontuacao>>> listarHistoricoPorUsuario(@PathVariable Long usuarioId) {
        List<HistoricoPontuacao> historicos = historicoPontuacaoService.listarHistoricoPorUsuario(usuarioId);

        List<EntityModel<HistoricoPontuacao>> historicosModel = historicos.stream()
                .map(h -> EntityModel.of(h,
                        linkTo(methodOn(HistoricoPontuacaoController.class).obterHistorico(h.getId())).withSelfRel(),
                        linkTo(methodOn(HistoricoPontuacaoController.class).listarHistoricoPorUsuario(usuarioId)).withRel("historico-usuario")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<HistoricoPontuacao>> resource = CollectionModel.of(historicosModel,
                linkTo(methodOn(HistoricoPontuacaoController.class).listarHistoricoPorUsuario(usuarioId)).withSelfRel(),
                linkTo(methodOn(HistoricoPontuacaoController.class).listarHistoricoGeral()).withRel("todos-historicos"));

        return ResponseEntity.ok(resource);
    }

    // Obter histórico de pontuação por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<HistoricoPontuacao>> obterHistorico(@PathVariable Long id) {
        HistoricoPontuacao historico = historicoPontuacaoService.obterHistoricoPorId(id);
        EntityModel<HistoricoPontuacao> resource = EntityModel.of(historico,
                linkTo(methodOn(HistoricoPontuacaoController.class).obterHistorico(id)).withSelfRel(),
                linkTo(methodOn(HistoricoPontuacaoController.class).listarHistoricoGeral()).withRel("todos-historicos"));

        return ResponseEntity.ok(resource);
    }

    // Listar histórico de pontuação geral
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<HistoricoPontuacao>>> listarHistoricoGeral() {
        List<HistoricoPontuacao> historicos = historicoPontuacaoService.listarHistoricoGeral();

        List<EntityModel<HistoricoPontuacao>> historicosModel = historicos.stream()
                .map(h -> EntityModel.of(h,
                        linkTo(methodOn(HistoricoPontuacaoController.class).obterHistorico(h.getId())).withSelfRel(),
                        linkTo(methodOn(HistoricoPontuacaoController.class).listarHistoricoGeral()).withRel("todos-historicos")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<HistoricoPontuacao>> resource = CollectionModel.of(historicosModel,
                linkTo(methodOn(HistoricoPontuacaoController.class).listarHistoricoGeral()).withSelfRel());

        return ResponseEntity.ok(resource);
    }
}
