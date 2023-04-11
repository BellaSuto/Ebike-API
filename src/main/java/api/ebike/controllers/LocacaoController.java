package api.ebike.controllers;

import api.ebike.entities.Locacao;
import api.ebike.services.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/v1")
public class LocacaoController {
    //private static final Logger logger = LoggerFactory.getLogger(LocacaoController.class);
    @Autowired
    private LocacaoService service;

    @PostMapping("/location")
    public ResponseEntity<String> alugar(@RequestBody Locacao locacao) throws Exception{
        Locacao aluguel = service.alugar(locacao);
        String mensagem = "Aluguel realizado com sucesso!" +" id: "+aluguel.getId();

        return ResponseEntity.ok(mensagem);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/locations")
    public List<Locacao> ativos() {
        return service.alugueisAtivos();
    }
}
