package lab04.Service;

import lab04.Alugueis;
import lab04.Pagamento;
import lab04.Locacao;
import lab04.repository.AlugueisRepository;
import lab04.repository.PagamentoRepository;
import lab04.repository.LocacaoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final LocacaoRepository locacaoRepository;
    private final AlugueisRepository alugueisRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, LocacaoRepository locacaoRepository, AlugueisRepository alugueisRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.locacaoRepository = locacaoRepository;
        this.alugueisRepository = alugueisRepository;
    }

    public void registrarPagamento(Integer idLocacao, LocalDate dataPagamento) {
        // Buscar a locação pelo ID
        Locacao locacao = locacaoRepository.buscaPorId(idLocacao);
        List<Alugueis> alugueis = locacao.getAlugueis();
        Pagamento pagamento = locacao.getPagamento();

        BigDecimal valorAluguel = pagamento.getValorAluguel();
        Alugueis aluguel = alugueis.get(0);
        LocalDate dataVencimento = aluguel.getDataVencimento();

        BigDecimal valorComMulta = calcularValorComMulta(valorAluguel, dataPagamento, dataVencimento);


        aluguel.setDataPagamento(dataPagamento);
        aluguel.setValorPago(valorComMulta);

        alugueisRepository.atualizar(aluguel);
    }

    private BigDecimal calcularValorComMulta(BigDecimal valorAluguel, LocalDate dataPagamento, LocalDate dataVencimento) {
        if (dataPagamento.isBefore(dataVencimento) || dataPagamento.isEqual(dataVencimento)) {
            return valorAluguel;
        }

        long diasDeAtraso = dataPagamento.toEpochDay() - dataVencimento.toEpochDay();
        BigDecimal percentualMulta = new BigDecimal("0.0033").multiply(BigDecimal.valueOf(diasDeAtraso));
        percentualMulta = percentualMulta.min(new BigDecimal("0.20")); // Limite de 20%

        BigDecimal valorMulta = valorAluguel.multiply(percentualMulta);
        return valorAluguel.add(valorMulta);
    }

    private BigDecimal calcularPercentualMulta(LocalDate dataPagamento, LocalDate dataVencimento) {
        if (dataPagamento.isBefore(dataVencimento) || dataPagamento.isEqual(dataVencimento)) {
            return BigDecimal.ZERO;
        }

        long diasDeAtraso = dataPagamento.toEpochDay() - dataVencimento.toEpochDay();
        BigDecimal percentualMulta = new BigDecimal("0.0033").multiply(BigDecimal.valueOf(diasDeAtraso));
        return percentualMulta.min(new BigDecimal("0.20")); // Limite de 20%
    }
}
