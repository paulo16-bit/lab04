package lab04;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Entity
public @Data class Profissionais {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profissional")
    private Integer idProfissional;

    @Column (nullable = false)
    private String nome;

    @Column (nullable = false)
    private String profissao;

    @Column (nullable = false)
    private String telefone1;

    private String telefone2;

    @Column(name = "valor_hora")
    private BigDecimal valorHora;

    @OneToMany(mappedBy = "profissional")
    private List<ServicosImovel> servicos;

    @OneToMany(mappedBy = "profissionais")
    private List<Observacao> observacoes;
}
