package api.ebike.controllers;

import api.ebike.entities.Locacao;
import api.ebike.services.LocacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/v1/locacao")
public class LocacaoController {
    private static final Logger logger = LoggerFactory.getLogger(LocacaoController.class);
    @Autowired
    private LocacaoService service;
   /* @PostMapping
    public Locacao alugar(Locacao locacao){
        if (locacao.getPagameto() != null){
            return service.alugar(locacao);
        }
        logger.info("Received request to say aluguel");
         "Aluguel realizado com sucesso!";
    }*/
    @PostMapping
    public ResponseEntity<String> postExemplo(@RequestBody Locacao locacao) {
        Locacao exemplo = service.alugar(locacao);
        // salvar objeto exemplo no banco de dados ou realizar outras operações
        String mensagem = "Aluguel realizado com sucesso!" +exemplo.getId();
        return ResponseEntity.ok(mensagem);
    }
}
