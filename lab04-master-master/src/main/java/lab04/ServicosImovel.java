package lab04;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "servicos_imovel")
public @Data class ServicosImovel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico")
    private Integer idServico;

    @Column(name = "data_servico")
    private LocalDate dataServico;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "id_imovel", nullable = false)
    private Imoveis imovel;

    @ManyToOne
    @JoinColumn(name = "id_profissional", nullable = false)
    private Profissionais profissional;

    @ManyToOne
    @JoinColumn(name = "id_locacao", nullable = false)
    private Locacao locacao;

    @OneToMany(mappedBy = "servicosImoveis")
    private List<Observacao> observacoes;
}
