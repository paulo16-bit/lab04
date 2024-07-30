package lab04.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lab04.Alugueis;
import lab04.Locacao;

import java.math.BigDecimal;
import java.util.List;

public class AlugueisRepository {

    private final EntityManager manager;

    public AlugueisRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void inserir(Alugueis aluguel) {
        manager.getTransaction().begin();
        manager.persist(aluguel);
        manager.getTransaction().commit();
    }

    public Alugueis buscaPorId(Integer id) {
        return manager.find(Alugueis.class, id);
    }

    public void atualizar(Alugueis aluguel) {
        manager.getTransaction().begin();
        manager.merge(aluguel);
        manager.getTransaction().commit();
    }

    public List<Alugueis> listarPorInquilino(String nomeInquilino) {
        TypedQuery<Alugueis> query = manager.createQuery(
                "SELECT a FROM Alugueis a WHERE a.locacao.cliente.nome = :nomeInquilino", Alugueis.class);
        query.setParameter("nomeInquilino", nomeInquilino);
        return query.getResultList();
    }

    public List<Alugueis> listarPorLocacao(Integer idLocacao) {
        TypedQuery<Alugueis> query = manager.createQuery(
                "SELECT a FROM Alugueis a WHERE a.locacao.idLocacao = :idLocacao", Alugueis.class);
        query.setParameter("idLocacao", idLocacao);
        return query.getResultList();
    }
    public List<Alugueis> listarPorLimitePreco(BigDecimal limitePreco) {
        TypedQuery<Alugueis> query = manager.createQuery(
                "SELECT a FROM Alugueis a WHERE a.locacao.imoveis.caracteristicas.valorAluguel <= :limitePreco", Alugueis.class);
        query.setParameter("limitePreco", limitePreco);
        return query.getResultList();
    }


    public List<Alugueis> listarAtrasados() {
        TypedQuery<Alugueis> query = manager.createQuery(
                "SELECT a FROM Alugueis a WHERE a.dataPagamento IS NOT NULL AND a.dataPagamento > a.dataVencimento", Alugueis.class);
        return query.getResultList();
    }
}
