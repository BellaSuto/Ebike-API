package api.ebike.services;

import api.ebike.entities.Bicicleta;
import api.ebike.entities.Usuario;
import api.ebike.repositories.BicicletaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BicicletaService {
    @Autowired
    private BicicletaRepository repository;

    public Bicicleta create(Bicicleta bike){
        return repository.save(bike);
    }

    public List<Bicicleta> readAll(){
        return repository.findAll();
    }
    public Bicicleta readOne(Long id) throws Exception{
        Optional<Bicicleta> bike = this.repository.findById(id);

        if (bike.isEmpty()){
            throw new Exception("Bicileta não encontrado!");
        }
        return bike.get();
    }
    public Bicicleta update(Bicicleta bike){
        Long id = bike.getId();
        Optional<Bicicleta> optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("Bicicleta não encontrado com o ID informado");
        }

        Bicicleta existingBike = optionalUser.get();

        BeanUtils.copyProperties(bike, existingBike);

        return repository.save(existingBike);

    }
    public void delete (Long id){
        repository.findAll();
    }

}
