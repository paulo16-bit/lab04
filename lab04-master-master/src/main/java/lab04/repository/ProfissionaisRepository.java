package lab04.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lab04.Profissionais;

import java.util.List;

public class ProfissionaisRepository {
    private final EntityManager manager;

    public ProfissionaisRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void inserir(Profissionais profissional) {
        manager.getTransaction().begin();
        manager.persist(profissional);
        manager.getTransaction().commit();
    }

    public Profissionais buscaPorId(Integer id) {
        return manager.find(Profissionais.class, id);
    }

    public Profissionais deletar(Profissionais profissional) {
        manager.getTransaction().begin();
        manager.remove(profissional);
        manager.getTransaction().commit();
        return profissional;
    }

    public void atualizar(Profissionais profissional) {
        manager.getTransaction().begin();
        manager.merge(profissional);
        manager.getTransaction().commit();
    }

    public List<Profissionais> mostrarTodos() {
        TypedQuery<Profissionais> query = manager.createQuery("SELECT p FROM Profissionais p", Profissionais.class);
        return query.getResultList();
    }

}
