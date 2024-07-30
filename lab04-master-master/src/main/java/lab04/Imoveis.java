package lab04;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
public @Data class Imoveis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imovel")
    private Integer idImovel;

    @OneToOne(mappedBy = "imovel", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private Localizacao localizacao;

    @OneToOne(mappedBy = "imovel", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private CaracteristicasImovel caracteristicas;

    @ManyToOne (optional = false)
    @JoinColumn(name = "id_proprietario")
    private Cliente cliente;

    @ManyToOne (optional = false)
    @JoinColumn(name = "id_tipo_imovel")
    private TipoImovel tipoImovel;

    @OneToMany(mappedBy = "imoveis")
    private List<Locacao> locacao;

    @OneToMany(mappedBy = "imovel")
    private List<ServicosImovel> servicos;

    @OneToMany(mappedBy = "imovel")
    private List<Observacao> observacoes;

}

