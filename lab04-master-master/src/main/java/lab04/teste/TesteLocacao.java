package lab04;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lab04.repository.*;
import java.time.LocalDate;

import java.util.List;

public class TesteLocacao {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab04");
        EntityManager manager = factory.createEntityManager();
        LocacaoRepository locacaoRepository = new LocacaoRepository(manager);
        ClienteRepository clienteRepository = new ClienteRepository(manager);
        ImovelRepository imovelRepository = new ImovelRepository(manager);

        //Insere uma locação
        Locacao locacao = new Locacao();
        locacao.setDataInicio(LocalDate.now());
        locacao.setDataFim(LocalDate.now().plusMonths(12));
        locacao.setAtivo(true);
        locacao.setCliente(clienteRepository.buscaPorId(1)); //cliente existente
        locacao.setImoveis(imovelRepository.buscaPorId(2)); //;imovel existente
        locacaoRepository.inserir(locacao);

        // Atualiza a locação
        Locacao locacaoAtualizada = locacaoRepository.buscaPorId(locacao.getIdLocacao());
        locacaoAtualizada.setAtivo(false);
        locacaoRepository.atualizar(locacaoAtualizada);

        // Listar todas as Locações
        List<Locacao> locacoes = locacaoRepository.listarTodas();
        for (Locacao loc : locacoes) {
            System.out.println(loc.getIdLocacao() + " - " + loc.getDataInicio() + " - " + loc.getDataFim() + " - " + loc.isAtivo());
        }

        /*// Deletar a Locação
        locacaoRepository.deletar(locacaoRepository.buscaPorId(locacao.getIdLocacao()));

        // Listar todas as Locações após exclusão
        locacoes = locacaoRepository.listarTodas();
        for (Locacao loc : locacoes) {
            System.out.println(loc.getIdLocacao() + " - " + loc.getDataInicio() + " - " + loc.getDataFim() + " - " + loc.isAtivo());
        }*/

    }

}
