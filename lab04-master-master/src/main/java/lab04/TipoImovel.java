package lab04;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tipo_imovel")
public @Data class TipoImovel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_imovel")
    private Integer idTipoImovel;

    @Column (nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "tipoImovel")
    private List<Imoveis> imoveis;
}
