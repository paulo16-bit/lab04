package lab04;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;


@Entity
@Table(name = "caracteristicas_imovel")
public @Data class CaracteristicasImovel {

    @Id
    @OneToOne
    @JoinColumn(name = "id_imovel")
    private Imoveis imovel;

    private Integer metragem;

    @Column(nullable = true)
    private int dormitorios;

    @Column(nullable = true)
    private int banheiros;

    @Column(nullable = true)
    private int suites;

    @Column(name = "vagas_garagem")
    private int vagasGaragem;

    @Column(name = "valor_aluguel_sugerido")
    private BigDecimal valorAluguel;

}
