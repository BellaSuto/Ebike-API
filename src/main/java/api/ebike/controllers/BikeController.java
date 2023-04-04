package api.ebike.controllers;

import api.ebike.entities.Bicicleta;
import api.ebike.services.BicicletaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        this.bicicletaService.delete(id);

    }
}
