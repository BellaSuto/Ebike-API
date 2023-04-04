package api.ebike.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bicicletas")
public class Bicicleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String cor;
    @Column(name = "valor_aluguel", nullable = false)
    private BigDecimal valorAluguel;
    private String marca;
    private String modelo;
    @OneToMany(mappedBy = "bikes")
    private List<Locacao> locacao= new ArrayList<>();

}
