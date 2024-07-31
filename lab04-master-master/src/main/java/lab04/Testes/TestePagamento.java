package lab04.Testes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lab04.Service.PagamentoService;
import lab04.repository.*;

import java.time.LocalDate;

public class TestePagamento {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab04");
        EntityManager manager = factory.createEntityManager();

        
        LocacaoRepository locacaoRepository = new LocacaoRepository(manager);
        AlugueisRepository alugueisRepository = new AlugueisRepository(manager);
        PagamentoRepository pagamentoRepository = new PagamentoRepository(manager);
        PagamentoService pagamentoService = new PagamentoService(pagamentoRepository, locacaoRepository, alugueisRepository);


        // Registrar um pagamento à Locação com id = 1
        LocalDate dataPagamento = LocalDate.of(2024, 7, 21);
        pagamentoService.registrarPagamento(1, dataPagamento);


        manager.close();
        factory.close();
    }
}
