package controller;

import model.ValidacaoChecklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ValidacaoChecklistService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/validacoes-checklist")
public class ValidacaoChecklistController {

    @Autowired
    private ValidacaoChecklistService validacaoChecklistService;

    // Validar checklist de um usuário
    @PostMapping("/usuario/{usuarioId}/consulta/{consultaId}")
    public ResponseEntity<EntityModel<ValidacaoChecklist>> validarChecklist(@PathVariable Long usuarioId, @PathVariable Long consultaId, @RequestBody ValidacaoChecklist validacao) {
        ValidacaoChecklist novaValidacao = validacaoChecklistService.validarChecklist(usuarioId, consultaId, validacao);
        EntityModel<ValidacaoChecklist> resource = EntityModel.of(novaValidacao,
                linkTo(methodOn(ValidacaoChecklistController.class).obterValidacao(novaValidacao.getId())).withSelfRel(),
                linkTo(methodOn(ValidacaoChecklistController.class).listarValidacoesPorUsuario(usuarioId)).withRel("validacoes-usuario"));

        return ResponseEntity.created(linkTo(methodOn(ValidacaoChecklistController.class).obterValidacao(novaValidacao.getId())).toUri()).body(resource);
    }

    // Obter validação por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ValidacaoChecklist>> obterValidacao(@PathVariable Long id) {
        ValidacaoChecklist validacao = validacaoChecklistService.obterValidacaoPorId(id);
        EntityModel<ValidacaoChecklist> resource = EntityModel.of(validacao,
                linkTo(methodOn(ValidacaoChecklistController.class).obterValidacao(id)).withSelfRel(),
                linkTo(methodOn(ValidacaoChecklistController.class).listarTodasValidacoes()).withRel("todas-validacoes"));

        return ResponseEntity.ok(resource);
    }

    // Listar todas as validações de checklist para um usuário específico
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<EntityModel<ValidacaoChecklist>>> listarValidacoesPorUsuario(@PathVariable Long usuarioId) {
        List<ValidacaoChecklist> validacoes = validacaoChecklistService.listarValidacoesPorUsuario(usuarioId);

        List<EntityModel<ValidacaoChecklist>> validacoesModel = validacoes.stream()
                .map(validacao -> EntityModel.of(validacao,
                        linkTo(methodOn(ValidacaoChecklistController.class).obterValidacao(validacao.getId())).withSelfRel(),
                        linkTo(methodOn(ValidacaoChecklistController.class).listarValidacoesPorUsuario(usuarioId)).withRel("validacoes-usuario")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<ValidacaoChecklist>> resource = CollectionModel.of(validacoesModel,
                linkTo(methodOn(ValidacaoChecklistController.class).listarValidacoesPorUsuario(usuarioId)).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    // Listar todas as validações de checklist (geral)
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ValidacaoChecklist>>> listarTodasValidacoes() {
        List<ValidacaoChecklist> validacoes = validacaoChecklistService.listarTodasValidacoes();

        List<EntityModel<ValidacaoChecklist>> validacoesModel = validacoes.stream()
                .map(validacao -> EntityModel.of(validacao,
                        linkTo(methodOn(ValidacaoChecklistController.class).obterValidacao(validacao.getId())).withSelfRel(),
                        linkTo(methodOn(ValidacaoChecklistController.class).listarTodasValidacoes()).withRel("todas-validacoes")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<ValidacaoChecklist>> resource = CollectionModel.of(validacoesModel,
                linkTo(methodOn(ValidacaoChecklistController.class).listarTodasValidacoes()).withSelfRel());

        return ResponseEntity.ok(resource);
    }
}
