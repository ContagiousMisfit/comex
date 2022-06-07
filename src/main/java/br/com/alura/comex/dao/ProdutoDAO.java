package br.com.alura.comex.dao;

import br.com.alura.comex.model.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public List<Produto> listarTodos() {
        String queryJPQL = "SELECT produto FROM Produto produto";
        return em.createQuery(queryJPQL, Produto.class)
                .getResultList();
    }

    public List<Produto> listarIndisponiveis() {
        String queryJPQL = "SELECT produto FROM Produto produto WHERE produto.quantidadeEmEstoque = :quantidadeEmEstoque";
        return em.createQuery(queryJPQL, Produto.class)
                .setParameter("quantidadeEmEstoque", 0)
                .getResultList();
    }

    public List<Produto> getProdutosMaisVendidos() {

        String jpql = "SELECT produto " +
                "FROM ItemDePedido item " +
                "JOIN item.produto produto " +
                "WHERE (item.quantidade > 3) " +
                "ORDER BY item.quantidade DESC ";

        return em.createQuery(jpql, Produto.class)
                .getResultList();
    }

    public List<Produto> getProdutosNuncaVendidos() {

        String jpql = "SELECT produto " +
                "FROM ItemDePedido item " +
                "JOIN item.produto produto " +
                "WHERE item.pedido is null ";

        return em.createQuery(jpql, Produto.class)
                .getResultList();

    }
}
