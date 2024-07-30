package lab04.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lab04.ServicosImovel;

import java.util.List;

public class ServCliRepository {
    private final EntityManager manager;

    public ServCliRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void inserir(ServicosImovel servico) {
        manager.getTransaction().begin();
        manager.persist(servico);
        manager.getTransaction().commit();
    }

    public ServicosImovel buscaPorId(Integer id) {
        return manager.find(ServicosImovel.class, id);
    }

    public ServicosImovel deletar(ServicosImovel servico) {
        manager.getTransaction().begin();
        manager.remove(servico);
        manager.getTransaction().commit();
        return servico;
    }

    public void atualizar(ServicosImovel servico) {
        manager.getTransaction().begin();
        manager.merge(servico);
        manager.getTransaction().commit();
    }

    public List<ServicosImovel> listarPorImovel(Integer idImovel) {
        TypedQuery<ServicosImovel> query = manager.createQuery("SELECT s FROM ServicosImovel s WHERE s.imovel.idImovel = :idImovel", ServicosImovel.class);
        query.setParameter("idImovel", idImovel);
        return query.getResultList();
    }

    public List<ServicosImovel> listarPorLocacao(Integer idLocacao) {
        TypedQuery<ServicosImovel> query = manager.createQuery("SELECT s FROM ServicosImovel s WHERE s.locacao.idLocacao = :idLocacao", ServicosImovel.class);
        query.setParameter("idLocacao", idLocacao);
        return query.getResultList();
    }

    public List<ServicosImovel> mostrarTodos() {
        TypedQuery<ServicosImovel> query = manager.createQuery("SELECT s FROM ServicosImovel s", ServicosImovel.class);
        return query.getResultList();
    }
}
