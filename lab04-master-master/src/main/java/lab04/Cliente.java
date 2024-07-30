package lab04;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
public @Data class
Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column (nullable = false)
    private String nome;

    @Column (nullable = false, unique = true)
    private String cpf;

    @Column (nullable =false)
    private String telefone;

    @Column (unique = true)
    private String email;

    @Column(name = "dt_nascimento")
    private LocalDate dataNasc;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Locacao> locacoes;

    @OneToMany(mappedBy = "cliente")
    private List<Imoveis> imoveis;
}
