package br.com.alura.comex.main;

import br.com.alura.comex.dao.PedidoDAO;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;

public class MainPedidoDao {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        popularBancoDeDados();
        PedidoDAO pedidoDAO = new PedidoDAO(em);
        pedidoDAO.getRelatorioMontantePorCategoria();

    }

    private static void popularBancoDeDados() {

    }

}
