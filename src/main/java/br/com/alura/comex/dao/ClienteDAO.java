package br.com.alura.comex.dao;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.vo.RelatorioQuantidadePedidosPorClienteVo;

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

    public List<Cliente> listarPorNome(String nome) {
        String queryJPQL = "SELECT cliente FROM Cliente cliente WHERE cliente.nome = :nome";
        return em.createQuery(queryJPQL, Cliente.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<RelatorioQuantidadePedidosPorClienteVo> getRelatorioPedidosPorCliente() {

        String jpql = "SELECT new br.com.alura.comex.vo.RelatorioQuantidadePedidosPorClienteVo(" +
                "cliente.nome, " +
                "COUNT(pedido.cliente)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.cliente cliente " +
                "GROUP BY cliente.nome ";

        return em.createQuery(jpql, RelatorioQuantidadePedidosPorClienteVo.class)
                .getResultList();
    }

    public List<Cliente> getRelatorioClientesMaisLucrativos() {

        String jpql = "SELECT cliente " +
                "FROM Cliente cliente " +
                "JOIN cliente.listaDePedidos pedidos " +
                "ORDER BY pedidos.valorTotal DESC";

        return em.createQuery(jpql, Cliente.class)
                .setMaxResults(3)
                .getResultList();
    }

}
