package api.ebike.controllers;

import api.ebike.entities.Bicicleta;
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
    public Bicicleta criar(@RequestBody Bicicleta bike) throws Exception {
        return bicicletaService.create(bike);
    }

    @GetMapping()
    public List<Bicicleta> buscarTodos() {
        return bicicletaService.readAll();
    }

    @GetMapping("/{id}")
    public Bicicleta buscarUm(@PathVariable("id") Long id) throws Exception {
        return bicicletaService.readOne(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Bicicleta> atualizarUBicicleta(@PathVariable Long id, @RequestBody Bicicleta bike) {
        bike.setId(id);
        Bicicleta updatedBike = bicicletaService.update(bike);
        return ResponseEntity.ok(updatedBike);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if (bicicletaService.delete(id)){
            return ResponseEntity.ok("Bicicleta excluído com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bicicleta não encontrado.");
        }

    }
}
