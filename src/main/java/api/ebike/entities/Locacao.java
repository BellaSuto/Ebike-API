package api.ebike.entities;

import api.ebike.dto.Pagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "locacao")
public class Locacao implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Transient
    LocalDateTime agora = LocalDateTime.now();
    @Transient
    LocalDateTime termino = agora.plusHours(6);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_final")
    private LocalDateTime dataFinal;
    @Enumerated(EnumType.STRING)
    private Pagamento pagameto;
    @ManyToOne(fetch = FetchType.EAGER)
    private Bicicleta bikes;

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = agora;
    }

    public void setDataFinal(LocalDateTime dataFinal) {
        this.dataFinal = termino;
    }

}
