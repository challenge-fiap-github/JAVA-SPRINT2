package controller;

import model.HistoricoPontuacao;
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
@RequestMapping("/api/historico-pontuacao")
public class HistoricoPontuacaoController {

    @Autowired
    private HistoricoPontuacaoService historicoPontuacaoService;

    // Listar histórico de pontuação de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<HistoricoPontuacao>> listarHistoricoPorUsuario(@PathVariable Long usuarioId) {
        List<HistoricoPontuacao> historicos = historicoPontuacaoService.listarHistoricoPorUsuario(usuarioId);
        historicos.forEach(h -> h.add(linkTo(methodOn(HistoricoPontuacaoController.class).obterHistorico(h.getId())).withSelfRel()));

        CollectionModel<HistoricoPontuacao> resource = CollectionModel.of(historicos,
                linkTo(methodOn(HistoricoPontuacaoController.class).listarHistoricoPorUsuario(usuarioId)).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    // Obter histórico de pontuação por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<HistoricoPontuacao>> obterHistorico(@PathVariable Long id) {
        HistoricoPontuacao historico = historicoPontuacaoService.obterHistoricoPorId(id);
        EntityModel<HistoricoPontuacao> resource = EntityModel.of(historico,
                linkTo(methodOn(HistoricoPontuacaoController.class).obterHistorico(id)).withSelfRel());

        return ResponseEntity.ok(resource);
    }
}
