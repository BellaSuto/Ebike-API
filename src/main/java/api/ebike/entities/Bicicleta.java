package api.ebike.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bicicletas")
public class Bicicleta implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
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
    private List<Locacao> locacao = new ArrayList<>();

    public Bicicleta(Long id, String cor, BigDecimal valorAluguel, String marca, String modelo, List<Locacao> locacao) {
        this.id = id;
        this.cor = cor;
        this.valorAluguel = valorAluguel;
        this.marca = marca;
        this.modelo = modelo;
        this.locacao = locacao;
    }

    public Bicicleta(Long id) {
        this.id = id;
    }


}
