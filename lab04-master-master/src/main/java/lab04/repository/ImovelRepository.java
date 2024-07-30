package lab04.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lab04.repository.ClienteRepository;
import lab04.Imoveis;

import java.math.BigDecimal;
import java.util.List;

public class ImovelRepository {

    private final EntityManager manager;

    public ImovelRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void inserir(Imoveis imovel) {
        manager.getTransaction().begin();
        manager.persist(imovel);
        manager.getTransaction().commit();
    }

    public Imoveis buscaPorId(Integer idImovel) {
        String jpql = "SELECT i FROM Imoveis i WHERE i.idImovel = :idImovel";
        TypedQuery<Imoveis> query = manager.createQuery(jpql, Imoveis.class);
        query.setParameter("idImovel", idImovel);

        List<Imoveis> resultados = query.getResultList();
        if (resultados.isEmpty()) {
            return null;
        } else {
            return resultados.get(0);
        }
    }

    public void atualizar(Imoveis imovel) {
        manager.getTransaction().begin();
        manager.merge(imovel);
        manager.getTransaction().commit();
    }

    public Imoveis deletar(Imoveis imovel) {
        manager.getTransaction().begin();
        manager.remove(imovel);
        manager.getTransaction().commit();
        return imovel;
    }

    public List<Imoveis> listarPorValorAluguel(BigDecimal valorMaximo) {
        TypedQuery<Imoveis> query = manager.createQuery(
                "SELECT i FROM Imoveis i WHERE i.caracteristicas.valorAluguel <= :valorMaximo", Imoveis.class);
        query.setParameter("valorMaximo", valorMaximo);
        return query.getResultList();
    }

    public List<Imoveis> listarTodos() {
        TypedQuery<Imoveis> query = manager.createQuery("SELECT i FROM Imoveis i", Imoveis.class);
        return query.getResultList();
    }
}


