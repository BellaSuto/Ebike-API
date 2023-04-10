package api.ebike.controllers;

import api.ebike.entities.Usuario;
import api.ebike.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Usuario criar(@RequestBody Usuario user) throws Exception {
        return usuarioService.createUser(user);
    }

    @GetMapping()
    public List<Usuario> buscarTodos() {
        return usuarioService.readAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarUm(@PathVariable("id") Long id) throws Exception {
        return usuarioService.readOne(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        this.usuarioService.delete(id);

    }
}
