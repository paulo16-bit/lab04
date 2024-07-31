package lab04.Testes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lab04.*;
import lab04.repository.*;

import java.util.List;

public class TesteImoveis {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab04");
        EntityManager manager = factory.createEntityManager();

        ClienteRepository clienteRepository = new ClienteRepository(manager);
        TipoImovelRepository tipoImovelRepository = new TipoImovelRepository(manager);
        ImovelRepository imovelRepository = new ImovelRepository(manager);

        //Adiciona um Cliente e um Tipo de Imovel
        Cliente cliente = new Cliente();
        cliente.setNome("Pedro");
        cliente.setCpf("12345678910");
        cliente.setEmail("pedro@gmail.com");
        cliente.setTelefone("98912345679");
        clienteRepository.inserir(cliente);

        TipoImovel tipoImovel = new TipoImovel();
        tipoImovel.setDescricao("Apartamento");
        tipoImovelRepository.inserir(tipoImovel);

        //Insere um imovel associado ao cliente e tipo de imovel criado anteriormente
        Imoveis imovel = new Imoveis();
        imovel.setCliente(cliente);
        imovel.setTipoImovel(tipoImovel);
        imovelRepository.inserir(imovel);

        //Cria outro tipo imóvel e atualiza o tipo de imóvel do imóvel criado
        TipoImovel tipoImovel2 = new TipoImovel();
        tipoImovel2.setDescricao("Casa");
        tipoImovelRepository.inserir(tipoImovel2);

        Imoveis Atualizaimovel = imovelRepository.buscaPorId(1);
        imovel.setTipoImovel(tipoImovel2);
        imovelRepository.atualizar(Atualizaimovel);

        //Deleta o imovel com id=1 usando a função que busca pelo id
        imovelRepository.deletar(imovelRepository.buscaPorId(1));

        List<Imoveis> imoveis = imovelRepository.listarTodos();
        for(Imoveis i: imoveis){
            System.out.println(i.getIdImovel());
        }
    }
}
