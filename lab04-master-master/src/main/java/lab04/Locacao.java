package lab04;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public @Data class Locacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locacao")
    private Integer idLocacao;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(nullable = false)
    private boolean ativo;

    @OneToOne(mappedBy = "locacao", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private Pagamento pagamento;

    @ManyToOne (optional = false)
    @JoinColumn(name = "id_inquilino", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "locacao")
    private List<Alugueis>  alugueis;


    @ManyToOne (optional = false)
    @JoinColumn(name = "id_imovel", nullable = false)
    private Imoveis imoveis;

    @OneToMany(mappedBy = "locacao")
    private List<Observacao> observacoes;

    @Override
    public String toString() {
        return "Locacao{" +
                "idLocacao=" + idLocacao +
                ", dataInicio=" + dataInicio +
                // Não inclua a referência bidirecional para Pagamento aqui
                '}';
    }
}

