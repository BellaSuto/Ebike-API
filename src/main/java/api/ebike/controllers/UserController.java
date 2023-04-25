package api.ebike.controllers;

import api.ebike.entities.Usuario;
import api.ebike.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
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
        Usuario userCriado;
        if (user.getUsername() != null) {
            userCriado = usuarioService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userCriado);
        } else {
            System.out.println("Body da requisição está incompleto");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
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
        try {
            user.setId(id);
            Usuario updatedUser = usuarioService.update(user);
            return ResponseEntity.ok(updatedUser);
        } catch (NullPointerException e) {
            System.out.println("Requisição vazia ou incompleta");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (EntityNotFoundException e) {
            System.out.println("Usuário não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            Long userEncontrado = usuarioService.readOne(id).getId();
            usuarioService.delete(userEncontrado);
            return ResponseEntity.ok("Usuário excluído com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com id: " + id + "não encontrado.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
