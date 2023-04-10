package api.ebike.services;

import api.ebike.client.CepResponse;


public interface CepService {
    public CepResponse buscarCep (String cep) throws Exception;
}
