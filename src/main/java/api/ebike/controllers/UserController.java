package api.ebike.controllers;

import api.ebike.entities.Usuario;
import api.ebike.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/novo")
    public Usuario criar(Usuario user){
        return usuarioService.create(user);
    }
    @GetMapping("/todos")
    public List<Usuario> buscarTodos(){
        return usuarioService.readyAll();
    }
    @GetMapping("/{id}")
    public Usuario buscarUm(@PathVariable("id") Long id) throws Exception {
        return usuarioService.readyOne(id);
    }


}
