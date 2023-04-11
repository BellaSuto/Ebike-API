package api.ebike.controllers;

import api.ebike.entities.Usuario;
import api.ebike.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/new")
    public ResponseEntity<Usuario> criar(@RequestBody Usuario user) throws Exception {
        Usuario usuario = usuarioService.createUser(user);

        return ResponseEntity.ok(usuario);
    }

    @GetMapping()
    public List<Usuario> buscarTodos() {
        return usuarioService.readAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarUm(@PathVariable("id") Long id) throws Exception {
        return usuarioService.readOne(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario user) {
        user.setId(id);
        Usuario updatedUser = usuarioService.update(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if (usuarioService.delete(id)){
            return ResponseEntity.ok("Usuário excluído com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

    }
}
