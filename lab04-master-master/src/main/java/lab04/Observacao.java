package lab04;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
public @Data class Observacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_observacao")
    private Integer idObservacao;

    private String obs;

    @ManyToOne
    @JoinColumn(name = "id_imovel")
    private Imoveis imovel;

    @ManyToOne
    @JoinColumn(name = "id_locacao")
    private Locacao locacao;

    @ManyToOne
    @JoinColumn(name = "id_alugueis")
    private Alugueis alugueis;

    @ManyToOne
    @JoinColumn(name = "id_profissionais")
    private Profissionais profissionais;

    @ManyToOne
    @JoinColumn(name = "id_servicos_imoveis")
    private ServicosImovel servicosImoveis;
}
