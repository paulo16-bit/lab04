package lab04;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lab04.repository.*;
import lab04.Service.PagamentoService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Teste {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab04");
        EntityManager manager = factory.createEntityManager();

        ClienteRepository clienteRepository = new ClienteRepository(manager);
        /*Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setCpf("12345678911");
        cliente.setEmail("joao@gmail.com");
        cliente.setTelefone("98912345679");
        clienteRepository.inserir(cliente);*/

        TipoImovelRepository  tipoImovelRepository = new TipoImovelRepository(manager);
        /*TipoImovel tipoImovel = new TipoImovel();
        tipoImovel.setDescricao("Apartamento");
        tipoImovelRepository.inserir(tipoImovel);*/

        ImovelRepository imovelRepository = new ImovelRepository(manager);
        /*Imoveis imovel = new Imoveis();
        imovel.setCliente(clienteRepository.buscaPorId(2));
        imovel.setTipoImovel(tipoImovelRepository.buscaPorId(1));
        imovelRepository.inserir(imovel);*/

        LocacaoRepository locacaoRepository = new LocacaoRepository(manager);
        /*Locacao locacao = new Locacao();
        locacao.setDataInicio(LocalDate.now());
        locacao.setDataFim(LocalDate.now().plusMonths(12));
        locacao.setAtivo(true);
        locacao.setCliente(clienteRepository.buscaPorId(2));
        locacao.setImoveis(imovelRepository.buscaPorId(2));
        locacaoRepository.inserir(locacao);*/

        AlugueisRepository alugueisRepository = new AlugueisRepository(manager);
        /*Alugueis aluguel = new Alugueis();
        aluguel.setDataVencimento(LocalDate.now().plusMonths(1));
        aluguel.setLocacao(locacaoRepository.buscaPorId(1));
        alugueisRepository.inserir(aluguel);*/

        /*PagamentoRepository pagamentoRepository = new PagamentoRepository(manager);
        PagamentoService pagamentoService = new PagamentoService(pagamentoRepository, locacaoRepository, alugueisRepository);
        LocalDate dataPagamento = LocalDate.of(2024, 9, 10); // Data de pagamento
        pagamentoService.registrarPagamento(1, dataPagamento);*/



        manager.close();
        factory.close();
    }
}
