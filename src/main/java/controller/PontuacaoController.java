package controller;

import model.Pontuacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/pontuacoes")
public class PontuacaoController {

    @Autowired
    private PontuacaoService pontuacaoService;

    // Obter pontuações de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<Pontuacao>> listarPontuacoesPorUsuario(@PathVariable Long usuarioId) {
        List<Pontuacao> pontuacoes = pontuacaoService.listarPontuacoesPorUsuario(usuarioId);
        pontuacoes.forEach(p -> p.add(linkTo(methodOn(PontuacaoController.class).obterPontuacao(p.getId())).withSelfRel()));

        CollectionModel<Pontuacao> resource = CollectionModel.of(pontuacoes,
                linkTo(methodOn(PontuacaoController.class).listarPontuacoesPorUsuario(usuarioId)).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    // Obter pontuação por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pontuacao>> obterPontuacao(@PathVariable Long id) {
        Pontuacao pontuacao = pontuacaoService.obterPontuacaoPorId(id);
        EntityModel<Pontuacao> resource = EntityModel.of(pontuacao,
                linkTo(methodOn(PontuacaoController.class).obterPontuacao(id)).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    // Registrar nova pontuação (por exemplo, após uma consulta preventiva)
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<EntityModel<Pontuacao>> registrarPontuacao(@PathVariable Long usuarioId, @RequestBody Pontuacao pontuacao) {
        Pontuacao novaPontuacao = pontuacaoService.registrarPontuacao(usuarioId, pontuacao);
        EntityModel<Pontuacao> resource = EntityModel.of(novaPontuacao,
                linkTo(methodOn(PontuacaoController.class).obterPontuacao(novaPontuacao.getId())).withSelfRel(),
                linkTo(methodOn(PontuacaoController.class).listarPontuacoesPorUsuario(usuarioId)).withRel("pontuacoes"));

        return ResponseEntity.created(linkTo(methodOn(PontuacaoController.class).obterPontuacao(novaPontuacao.getId())).toUri()).body(resource);
    }
}
