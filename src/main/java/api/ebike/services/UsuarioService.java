package api.ebike.services;

import api.ebike.client.CepResponse;
import api.ebike.entities.Endereco;
import api.ebike.entities.Usuario;
import api.ebike.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    BCryptPasswordEncoder criptografia;
    @Autowired
    private CepService cepService;

    @CrossOrigin
    @Transactional
    public Usuario createUser(Usuario user) throws Exception {
        CepServiceImpl buscarCep = new CepServiceImpl();
        usuarioRepository.save(user);
        String cepUser = user.getEndereco().getCep();
        CepResponse cep = cepService.buscarCep(cepUser);

        user.setEndereco(new Endereco(cep.getCep(), cep.getLogradouro(), cep.getBairro(),  cep.getLocalidade(),  cep.getUf()));

        if (user.getSenha() != null) {
            user.setSenha(criptografia.encode(user.getSenha()));
            return usuarioRepository.save(user);
        } else throw new Exception("Senha obrigatória!");
    }

    public List<Usuario> readAll(){
        return usuarioRepository.findAll();
    }
    public Usuario readOne(Long id) throws Exception{
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
