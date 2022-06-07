import br.com.alura.comex.dao.ClienteDAO;
import br.com.alura.comex.dao.PedidoDAO;
import br.com.alura.comex.dao.ProdutoDAO;
import br.com.alura.comex.dto.PedidoDTO;
import br.com.alura.comex.main.MainClienteDao;
import br.com.alura.comex.model.*;
import br.com.alura.comex.model.utils.*;
import br.com.alura.comex.processador.ProcessadorDeCSV;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        popularBancoDeDadosComCSV();
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDAO clienteDao = new ClienteDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        clienteDao.getRelatorioPedidosPorCliente();
        pedidoDAO.getRelatorioMontantePorCategoria();
        produtoDAO.getProdutosMaisVendidos();
        clienteDao.getRelatorioClientesMaisLucrativos();
        produtoDAO.getProdutosNuncaVendidos();

    }

    private static void popularBancoDeDadosComCSV() throws FileNotFoundException, URISyntaxException {
        EntityManager em = JPAUtil.getEntityManager();
        ProcessadorDeCSV processador = new ProcessadorDeCSV();
        List<PedidoDTO> pedidoDTOList = processador.lerRegistros();

        List<Categoria> categoriaList = List.of();
        List<Produto> produtoList = List.of();
        List<Cliente> clienteList = List.of();
        List<ItemDePedido> itemDePedidoList = List.of();
        List<Pedido> pedidoList = List.of();

        pedidoDTOList.forEach(pedido -> {
            categoriaList.add(new CategoriaBuilder()
                    .comNome(pedido.getCategoria())
                    .build());
            produtoList.add(new ProdutoBuilder()
                    .comNome(pedido.getProduto())
                    .build());
            clienteList.add(new ClienteBuilder()
                    .comNome(pedido.getCliente())
                    .build());
            itemDePedidoList.add(new ItemDePedidoBuilder()
                    .comPrecoUnitario(pedido.getPrecoUnitario())
                    .comQuantidade(pedido.getQuantidade())
                    .build());
            pedidoList.add(new PedidoBuilder()
                    .comData(pedido.getData())
                    .comListaDePedidos(itemDePedidoList)
                    .build());
        });

    }

}
