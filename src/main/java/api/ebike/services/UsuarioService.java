package api.ebike.services;

import api.ebike.entities.Usuario;
import api.ebike.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    BCryptPasswordEncoder criptografia;

    public void create(Usuario user) throws Exception {
        user.setSenha(criptografarSenha(user));
        usuarioRepository.save(user);
    }

    private String criptografarSenha(Usuario user) throws Exception {
        if(user.getSenha() == "")
            return new BCryptPasswordEncoder().encode(user.getSenha());
        if (user.getSenha() == null)
            throw new Exception("Senha obrigatória!");
        return usuarioRepository.findById(user.getId()).get()
                .getSenha();    }

    public List<Usuario> readyAll(){
        return usuarioRepository.findAll();
    }
    public Usuario readyOne(Long id) throws Exception{
        Optional<Usuario> user = this.usuarioRepository.findById(id);

        if (user.isEmpty()){
            throw new Exception("Usuário não encontrado!");
        }
        return user.get();
    }
    public void delete (Long id){
        usuarioRepository.deleteById(id);
    }
}
