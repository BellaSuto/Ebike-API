package api.ebike.controllers;

import api.ebike.entities.Bicicleta;
import api.ebike.exceptions.BicicletaNaoEncontradaException;
import api.ebike.services.BicicletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/v1/bike")
public class BikeController {
    @Autowired
    private BicicletaService bicicletaService;


    @PostMapping("/new")
    public ResponseEntity<Bicicleta>criar(@RequestBody Bicicleta bike) throws Exception {
        try { if (bike != null) {
            var bikeCriada = bicicletaService.create(bike);
            return ResponseEntity.status(HttpStatus.CREATED).body(bikeCriada);
        }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return null;
    }

    @GetMapping()
    public List<Bicicleta> buscarTodos() {
        return bicicletaService.readAll();
    }

    @GetMapping("/{id}")
    public Bicicleta buscarUm(@PathVariable("id") Long id) throws Exception {
        try {
            return bicicletaService.readOne(id);
        }catch (BicicletaNaoEncontradaException e){
            throw new BicicletaNaoEncontradaException();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bicicleta> atualizarUBicicleta(@PathVariable Long id, @RequestBody Bicicleta bike) throws Exception {
        try {
            Long bikeEncontrada = bicicletaService.readOne(id).getId();
            bike.setId(bikeEncontrada);
            Bicicleta updatedBike = bicicletaService.update(bike);
            return ResponseEntity.ok(updatedBike);
        } catch (BicicletaNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            Long bikeEncontrada = bicicletaService.readOne(id).getId();
            bicicletaService.delete(bikeEncontrada);

        } catch (BicicletaNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bicicleta não encontrada.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Bicicleta excluído com sucesso!");
    }
    
}
