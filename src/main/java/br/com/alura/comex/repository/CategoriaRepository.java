package br.com.alura.comex.repository;

import br.com.alura.comex.controller.dto.projections.PedidosPorCategoriaProjection;
import br.com.alura.comex.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "\n" +
            "SELECT categoria.nome, SUM(itens_de_pedido.quantidade), SUM(pedidos.valor_total)\n" +
            "FROM categorias categoria\n" +
            "INNER JOIN produtos \n" +
            "ON produtos.categoria_id = categoria.id\n" +
            "INNER JOIN itens_de_pedido\n" +
            "ON itens_de_pedido.produto_id = produtos.id\n" +
            "INNER JOIN pedidos\n" +
            "ON pedidos.id = itens_de_pedido.pedido_id;\n ", nativeQuery = true)
    List<PedidosPorCategoriaProjection> findPedidosPorCategoria();
}
