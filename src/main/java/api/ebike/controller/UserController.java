package api.ebike.controller;

import api.ebike.entities.Usuario;
import api.ebike.services.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    private final UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/novo")
    public Usuario create(Usuario user){
        return usuarioService.create(user);
    }
    @GetMapping("/{id}")
    public Usuario buscarUm(Long id) throws Exception {
        return usuarioService.readyOne(id);
    }


}
