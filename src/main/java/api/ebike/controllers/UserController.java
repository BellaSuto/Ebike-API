package api.ebike.controllers;

import api.ebike.entities.Usuario;
import api.ebike.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    BCryptPasswordEncoder criptografia;

    @PostMapping("/novo")
    public void criar(Usuario user) throws Exception {

        String senhaCriptografada = criptografia.encode(user.getPassword());
        user.setSenha(senhaCriptografada);

        usuarioService.create(user);
    }
    @GetMapping("/todos")
    public List<Usuario> buscarTodos(){
        return usuarioService.readyAll();
    }
    @GetMapping("/{id}")
    public Usuario buscarUm(@PathVariable("id") Long id) throws Exception {
        return usuarioService.readyOne(id);
    }
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        this.usuarioService.delete(id);

    }
}
