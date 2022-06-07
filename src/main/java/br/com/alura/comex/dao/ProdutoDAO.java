package br.com.alura.comex.dao;

import br.com.alura.comex.model.Produto;
import br.com.alura.comex.vo.RelatorioProdutosMaisVendidosVo;
import br.com.alura.comex.vo.RelatorioProdutosNuncaVendidosVo;

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

    public List<RelatorioProdutosMaisVendidosVo> getProdutosMaisVendidos() {

        String jpql = "SELECT new br.com.alura.comex.vo.RelatorioProdutosMaisVendidosVo(produto) " +
                "FROM ItemDePedido item " +
                "JOIN item.produto produto " +
                "WHERE (item.quantidade > 3) " +
                "GROUP BY item.quantidade " +
                "ORDER BY item.quantidade DESC ";

        return em.createQuery(jpql, RelatorioProdutosMaisVendidosVo.class)
                .getResultList();
    }
    public List<RelatorioProdutosNuncaVendidosVo> getProdutosNuncaVendidos() {

        String jpql = "SELECT new br.com.alura.comex.vo.RelatorioProdutosNuncaVendidosVo(produto) " +
                "FROM ItemDePedido item " +
                "JOIN item.produto produto " +
                "WHERE item.quantidade > 3 " +
                "GROUP BY item.quantidade DESC";

        return em.createQuery(jpql, RelatorioProdutosNuncaVendidosVo.class)
                .getResultList();

    }
}
