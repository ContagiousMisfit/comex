package br.com.alura.comex.main;

import br.com.alura.comex.dao.PedidoDAO;
import br.com.alura.comex.util.JPAUtil;
import br.com.alura.comex.vo.RelatorioMontantePorCategoriaVo;

import javax.persistence.EntityManager;
import java.util.List;

public class MainPedidoDao {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        popularBancoDeDados();
        PedidoDAO pedidoDAO = new PedidoDAO(em);
        List<RelatorioMontantePorCategoriaVo> relatorioMontantePorCategoria = pedidoDAO.getRelatorioMontantePorCategoria();
        relatorioMontantePorCategoria.forEach(System.out::println);

    }

    private static void popularBancoDeDados() {

    }

}
