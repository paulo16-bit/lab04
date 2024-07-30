package lab04.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lab04.Cliente;

import java.util.List;

public class ClienteRepository {
    private final EntityManager manager;

    public ClienteRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void inserir(Cliente cliente) {
        if (!existeCpf(cliente.getCpf())) {
            manager.getTransaction().begin();
            try {
                manager.persist(cliente);
                manager.getTransaction().commit();
            } catch (Exception e) {
                manager.getTransaction().rollback();
                throw e;
            }
        } else {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
    }

    public Cliente buscaPorId(Integer id) {
        return manager.find(Cliente.class, id);
    }

    public Cliente deletar(Cliente cliente) {
        manager.getTransaction().begin();
        manager.remove(cliente);
        manager.getTransaction().commit();
        return cliente;
    }

    public boolean existeCpf(String cpf) {
        TypedQuery<Long> query = manager.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.cpf = :cpf", Long.class);
        query.setParameter("cpf", cpf);
        return query.getSingleResult() > 0;
    }

    public void atualizar(Cliente cliente) {
        manager.getTransaction().begin();
        manager.merge(cliente);
        manager.getTransaction().commit();
    }

    public List<Cliente> mostrarTodos() {
        TypedQuery<Cliente> query = manager.createQuery("select c from Cliente c", Cliente.class);
        return query.getResultList();
    }

}
