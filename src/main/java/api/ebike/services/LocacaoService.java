package api.ebike.services;

import api.ebike.entities.Locacao;
import api.ebike.repositories.LocacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocacaoService {
    @Autowired
    private LocacaoRepository repository;

    public Locacao alugar(Locacao locacao) throws Exception{
        if (locacao.getPagameto() != null) {
            return repository.save(locacao);
        }else throw new Exception("Pagamento obrigatório");
    }

    public List<Locacao> alugueisAtivos(){
        return repository.findAll();
    }
}
