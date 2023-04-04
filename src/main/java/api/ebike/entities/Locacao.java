package api.ebike.entities;

import api.ebike.dto.Pagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Setter
@Getter
@Entity
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inicio")
    private Date inicio;
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
    @Column(name = "data_final")
    private Date dataFinal;
    @Enumerated(EnumType.STRING)
    private Pagamento pagameto;
    @ManyToOne(fetch = FetchType.EAGER)
    private Bicicleta bikes;
}
