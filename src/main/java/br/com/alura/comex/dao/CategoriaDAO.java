package br.com.alura.comex.dao;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Status;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDAO {

    private EntityManager em;

    public CategoriaDAO(EntityManager em) {
        this.em = em;
    }

    public Categoria buscarPorId(int id) {
        return em.find(Categoria.class, id);
    }

    public void cadastrar(Categoria categoria) {
        this.em.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
        this.em.merge(categoria);
    }

    public List<Categoria> listarTodas() {
        String queryJPQL = "SELECT categoria FROM Categoria categoria";
        return em.createQuery(queryJPQL, Categoria.class).getResultList();
    }

    public List<Categoria> listarInativas() {
        String queryJPQL = "SELECT categoria FROM Categoria categoria WHERE categoria.status = :status";
        return em.createQuery(queryJPQL, Categoria.class)
                .setParameter("status", Status.INATIVA)
                .getResultList();
    }

}
