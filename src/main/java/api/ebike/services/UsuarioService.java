package api.ebike.services;

import api.ebike.entities.Usuario;
import api.ebike.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario create(Usuario user){
        return usuarioRepository.save(user);
    }

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
