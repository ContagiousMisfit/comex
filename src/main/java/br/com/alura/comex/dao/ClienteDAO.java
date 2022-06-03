package br.com.alura.comex.dao;

import br.com.alura.comex.model.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDAO {

    private EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public Cliente buscarPorId(int id) {
        return em.find(Cliente.class, id);
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public void atualizar(Cliente cliente) {
        this.em.merge(cliente);
    }

    public void remover(Cliente cliente) {
        cliente = em.merge(cliente);
        this.em.remove(cliente);
    }

    public List<Cliente> listarTodos() {
        String queryJPQL = "SELECT cliente FROM Cliente cliente";
        return em.createQuery(queryJPQL, Cliente.class).getResultList();
    }

    public Cliente listarPorNome(String nome) {
        String queryJPQL = "SELECT cliente FROM Cliente cliente WHERE cliente.nome = :nome";
        return em.createQuery(queryJPQL, Cliente.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

}
