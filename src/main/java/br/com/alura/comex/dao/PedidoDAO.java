package br.com.alura.comex.dao;

import br.com.alura.comex.vo.RelatorioMontantePorCategoriaVo;
import br.com.alura.comex.vo.RelatorioProdutosMaisVendidosVo;
import br.com.alura.comex.vo.RelatorioProdutosNuncaVendidosVo;
import br.com.alura.comex.vo.RelatorioQuantidadePedidosPorClienteVo;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoDAO {

    private EntityManager em;

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    public List<RelatorioMontantePorCategoriaVo> getRelatorioMontantePorCategoria() {

        String jpql = "SELECT new br.com.alura.comex.vo.RelatorioMontantePorCategoriaVo(" +
                "categoria.nome, " +
                "SUM(listaDeItens.quantidade)) " +
                "FROM ItemDePedido listaDeItens " +
                "JOIN listaDeItens.produto produto " +
                "JOIN produto.categoria categoria " +
                "GROUP BY categoria.nome";

        return em.createQuery(jpql, RelatorioMontantePorCategoriaVo.class)
                .getResultList();
    }

}

