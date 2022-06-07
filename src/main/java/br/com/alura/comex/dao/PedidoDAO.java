package br.com.alura.comex.dao;

import br.com.alura.comex.vo.RelatorioQuantidadePedidosPorClienteVo;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoDAO {

    private EntityManager em;

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    public List<RelatorioQuantidadePedidosPorClienteVo> getRelatorioMontantePorCategoria() {

        String jpql = "SELECT new br.com.alura.comex.vo.RelatorioMontantePorCategoriaVo(" +
                "categoria.nome," +
                "SUM(lista.quantidade))" +
                "FROM Pedido pedido" +
                "JOIN pedido.categoria categoria" +
                "JOIN pedido.listaDePedidos lista" +
                "GROUP BY categoria.nome";

        return em.createQuery(jpql, RelatorioQuantidadePedidosPorClienteVo.class)
                .getResultList();
    }

    public List<RelatorioQuantidadePedidosPorClienteVo> getProdutosMaisVendidos() {

        String jpql = "SELECT new br.com.alura.comex.vo.RelatorioProdutosMaisVendidosVo(produto)" +
                "FROM ItemDePedido item" +
                "JOIN item.produto produto" +
                "WHERE item.quantidade > 3" +
                "GROUP BY item.quantidade DESC";

        return em.createQuery(jpql, RelatorioQuantidadePedidosPorClienteVo.class)
                .getResultList();
    }

}
