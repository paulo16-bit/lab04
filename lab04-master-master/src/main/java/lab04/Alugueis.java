package lab04;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public @Data class Alugueis {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluguel")
    private Integer idAluguel;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @ManyToOne
    @JoinColumn(name = "id_locacao", nullable = false)
    private Locacao locacao;

    @OneToMany(mappedBy = "alugueis")
    private List<Observacao> observacoes;
}
