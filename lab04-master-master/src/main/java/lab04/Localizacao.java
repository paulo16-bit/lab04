package lab04;
import jakarta.persistence.*;
import lombok.Data;

@Entity
public @Data class Localizacao {

    @Id
    @OneToOne
    @JoinColumn(name = "id_imovel")
    private Imoveis imovel;

    @Column (nullable =false)
    private String logradouro;

    @Column (nullable =false)
    private String bairro;

    @Column (nullable =false)
    private String cep;

}
