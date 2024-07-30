package lab04.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lab04.Locacao;
import lab04.TipoImovel;

import java.util.List;

public class LocacaoRepository {
    private final EntityManager manager;

    public LocacaoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void inserir(Locacao locacao) {
        if (!imovelDisponivel(locacao.getImoveis().getIdImovel())) {
            throw new IllegalArgumentException("O imóvel já está alugado.");
        }
        manager.getTransaction().begin();
        manager.persist(locacao);
        manager.getTransaction().commit();
    }

    public Locacao buscaPorId(Integer id) {
        TypedQuery<Locacao> query = manager.createQuery(
                "SELECT l FROM Locacao l JOIN FETCH l.imoveis WHERE l.idLocacao = :id", Locacao.class);
        query.setParameter("id", id);

        return query.getResultStream().findFirst().orElse(null);
    }


    public void atualizar(Locacao locacao) {
        manager.getTransaction().begin();
        manager.merge(locacao);
        manager.getTransaction().commit();
    }

    public void deletar(Locacao locacao) {
        manager.getTransaction().begin();
        manager.remove(locacao);
        manager.getTransaction().commit();
    }

    public List<Locacao> listarPorCliente(String nomeCliente) {
        TypedQuery<Locacao> query = manager.createQuery(
                "SELECT l FROM Locacao l WHERE l.cliente.nome = :nomeCliente", Locacao.class);
        query.setParameter("nomeCliente", nomeCliente);
        return query.getResultList();
    }

    public List<Locacao> listarTodas() {
        TypedQuery<Locacao> query = manager.createQuery("SELECT l FROM Locacao l", Locacao.class);
        return query.getResultList();
    }

    public boolean imovelDisponivel(Integer idImovel) {
        TypedQuery<Long> query = manager.createQuery(
                "SELECT COUNT(l) FROM Locacao l WHERE l.imoveis.idImovel = :idImovel AND l.ativo = true", Long.class);
        query.setParameter("idImovel", idImovel);
        return query.getSingleResult() == 0;
    }

}
