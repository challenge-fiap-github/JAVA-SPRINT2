package controller;

import model.ValidacaoChecklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ValidacaoChecklistService;

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
                linkTo(methodOn(ValidacaoChecklistController.class).obterValidacao(novaValidacao.getId())).withSelfRel());

        return ResponseEntity.created(linkTo(methodOn(ValidacaoChecklistController.class).obterValidacao(novaValidacao.getId())).toUri()).body(resource);
    }

    // Obter validação por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ValidacaoChecklist>> obterValidacao(@PathVariable Long id) {
        ValidacaoChecklist validacao = validacaoChecklistService.obterValidacaoPorId(id);
        EntityModel<ValidacaoChecklist> resource = EntityModel.of(validacao,
                linkTo(methodOn(ValidacaoChecklistController.class).obterValidacao(id)).withSelfRel());

        return ResponseEntity.ok(resource);
    }
}
