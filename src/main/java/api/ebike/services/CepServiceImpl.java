package api.ebike.services;

import api.ebike.client.CepResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
@Service
@Transactional
public class CepServiceImpl implements CepService {
    @Override
    public CepResponse buscarCep(String CEP) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String URLAPI = "https://viacep.com.br/ws/"+CEP+"/json/";
        CepResponse response = restTemplate.getForObject(URLAPI, CepResponse.class);

        if (response == null || response.getCep() == null) {
            throw new RuntimeException("Erro ao consultar API de CEP");
        }

        return response;
    }
}
