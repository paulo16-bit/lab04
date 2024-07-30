package lab04.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lab04.TipoImovel;

import java.util.List;

public class TipoImovelRepository {
    private final EntityManager manager;

    public TipoImovelRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void inserir(TipoImovel tipoImovel) {
        manager.getTransaction().begin();
        manager.persist(tipoImovel);
        manager.getTransaction().commit();
    }

    public TipoImovel buscaPorId(Integer id) {
        return manager.find(TipoImovel.class, id);
    }

    public void atualizar(TipoImovel tipoImovel) {
        manager.getTransaction().begin();
        manager.merge(tipoImovel);
        manager.getTransaction().commit();
    }

    public void deletar(TipoImovel tipoImovel) {
        manager.getTransaction().begin();
        manager.remove(tipoImovel);
        manager.getTransaction().commit();
    }

    public List<TipoImovel> mostrarTodos() {
        TypedQuery<TipoImovel> query = manager.createQuery("SELECT t FROM TipoImovel t", TipoImovel.class);
        return query.getResultList();
    }
}
