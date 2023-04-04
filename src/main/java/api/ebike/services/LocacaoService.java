package api.ebike.services;

import api.ebike.entities.Locacao;
import api.ebike.repositories.LocacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocacaoService {
    @Autowired
    private LocacaoRepository repository;
    @Transactional
    public Locacao alugar(Locacao locacao){
        return repository.save(locacao);
    }
}
