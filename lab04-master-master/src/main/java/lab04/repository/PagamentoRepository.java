package lab04.repository;

import jakarta.persistence.EntityManager;
import lab04.Pagamento;

public class PagamentoRepository {

    private final EntityManager manager;

    public PagamentoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void inserir(Pagamento pagamento) {
        manager.getTransaction().begin();
        manager.persist(pagamento);
        manager.getTransaction().commit();
    }
    public Pagamento buscaPorId(Integer id) {
        return manager.find(Pagamento.class, id);
    }
    public Pagamento deletar(Pagamento pagamento) {
        manager.getTransaction().begin();
        manager.remove(pagamento);
        manager.getTransaction().commit();
        return pagamento;
    }
    public void atualizar(Pagamento pagamento) {
        manager.getTransaction().begin();
        manager.merge(pagamento);
        manager.getTransaction().commit();
    }
}
