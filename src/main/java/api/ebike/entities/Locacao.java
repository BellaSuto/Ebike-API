package api.ebike.entities;

import api.ebike.dto.Pagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public Locacao(LocalDateTime agora, LocalDateTime termino, Long id, LocalDateTime dataInicio, LocalDateTime dataFinal, Pagamento pagameto, Bicicleta bikes) {
        this.agora = agora;
        this.termino = termino;
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.pagameto = pagameto;
        this.bikes = bikes;
    }

    public Locacao(Bicicleta bikes) {
        this.bikes = bikes;
    }

    public Locacao() {

    }

    public Locacao(Long id) {
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = agora;
    }

    public void setDataFinal(LocalDateTime dataFinal) {
        this.dataFinal = termino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Locacao locacao = (Locacao) o;

        if (!Objects.equals(id, locacao.id)) return false;
        return Objects.equals(bikes, locacao.bikes);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bikes != null ? bikes.hashCode() : 0);
        return result;
    }
}
