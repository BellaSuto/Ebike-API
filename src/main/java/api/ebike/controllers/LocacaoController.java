package api.ebike.controllers;

import api.ebike.entities.Locacao;
import api.ebike.services.BicicletaService;
import api.ebike.services.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/v1")
public class LocacaoController {

    @Autowired
    private LocacaoService service;
    @Autowired
    BicicletaService bicicletaService;

    @PostMapping("/location")
    public ResponseEntity<String> alugar(@RequestBody Locacao locacao) throws Exception {
        List<Locacao> lista = service.alugueisAtivos();

        Long bike = locacao.getBikes().getId();

        boolean contemBike = lista.stream().anyMatch(objeto -> objeto.getBikes().getId().equals(bike));

        if (contemBike) {
            String mensagem = "Essa bicicleta ja está alugada!";
            return ResponseEntity.badRequest().body(mensagem);
        } else {
            Locacao aluguel = service.alugar(locacao);
            String mensagem = "Aluguel realizado com sucesso!" + " id: " + aluguel.getId();
            return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
        }

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/locations")
    public List<Locacao> ativos() {
        return service.alugueisAtivos();
    }
}
