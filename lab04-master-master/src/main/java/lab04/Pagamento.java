package lab04;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public @Data class Pagamento {

    @Id
    @OneToOne
    @JoinColumn(name = "id_locacao")
    private Locacao locacao;

    @Column(name = "percentual_multa")
    private BigDecimal percentualMulta;

    @Column(name = "valor_aluguel", nullable = false)
    private BigDecimal valorAluguel;

    @Column(name = "dia_vencimento", nullable = false)
    private int diaVencimento;

    @Override
    public String toString() {
        return "Pagamento{" +
                "idLocação=" + locacao.getIdLocacao() +
                ", percentualMulta=" + percentualMulta +
                ", valorAluguel=" + valorAluguel +
                ", diaVencimento=" + diaVencimento +
                '}';
    }

}
