package api.ebike.client;

import api.ebike.services.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@PreAuthorize("hasAuthority('ADMIN', 'USER')")
@RestController
@RequestMapping("/cep")
public class CepController {
    @Autowired
    private CepService cepService;
    @CrossOrigin
    @GetMapping("/endereco")
    public ResponseEntity<CepResponse> getEndereco(
            @RequestParam("cep") String cep) throws Exception{

        CepResponse retorno = cepService.buscarCep(cep);
        return new ResponseEntity<CepResponse>(retorno, HttpStatus.OK);
    }
}
