package controller;

import model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Registro de novo usuário
    @PostMapping("/registrar")
    public ResponseEntity<EntityModel<Usuario>> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.registrarUsuario(usuario);
        EntityModel<Usuario> resource = EntityModel.of(novoUsuario,
                linkTo(methodOn(UsuarioController.class).obterUsuario(novoUsuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listarUsuarios()).withRel("usuarios"));

        return ResponseEntity.created(linkTo(methodOn(UsuarioController.class).obterUsuario(novoUsuario.getId())).toUri()).body(resource);
    }

    // Autenticação de usuário
    @PostMapping("/autenticar")
    public ResponseEntity<EntityModel<Usuario>> autenticarUsuario(@RequestBody Usuario credenciais) {
        Usuario usuarioAutenticado = usuarioService.autenticarUsuario(credenciais.getEmail(), credenciais.getSenha());
        if (usuarioAutenticado == null) {
            return ResponseEntity.status(401).build(); // Unauthorized caso as credenciais sejam inválidas
        }

        EntityModel<Usuario> resource = EntityModel.of(usuarioAutenticado,
                linkTo(methodOn(UsuarioController.class).obterUsuario(usuarioAutenticado.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listarUsuarios()).withRel("usuarios"));

        return ResponseEntity.ok(resource);
    }

    // Obter usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> obterUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.obterUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o usuário não existir
        }

        EntityModel<Usuario> resource = EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).obterUsuario(id)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listarUsuarios()).withRel("usuarios"));

        return ResponseEntity.ok(resource);
    }

    // Listar todos os usuários (apenas para fins administrativos)
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodosUsuarios();

        List<EntityModel<Usuario>> usuarioModels = usuarios.stream()
                .map(usuario -> EntityModel.of(usuario,
                        linkTo(methodOn(UsuarioController.class).obterUsuario(usuario.getId())).withSelfRel(),
                        linkTo(methodOn(UsuarioController.class).listarUsuarios()).withRel("usuarios")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Usuario>> resource = CollectionModel.of(usuarioModels,
                linkTo(methodOn(UsuarioController.class).listarUsuarios()).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    // Atualizar informações do usuário
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuario = usuarioService.obterUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o usuário não existir
        }

        Usuario usuarioAtualizadoDb = usuarioService.atualizarUsuario(id, usuarioAtualizado);
        EntityModel<Usuario> resource = EntityModel.of(usuarioAtualizadoDb,
                linkTo(methodOn(UsuarioController.class).obterUsuario(id)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listarUsuarios()).withRel("usuarios"));

        return ResponseEntity.ok(resource);
    }

    // Deletar usuário (opcional)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.obterUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o usuário não existir
        }

        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}