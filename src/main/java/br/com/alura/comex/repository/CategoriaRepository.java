package br.com.alura.comex.repository;

import br.com.alura.comex.controller.dto.projections.PedidosPorCategoriaProjection;
import br.com.alura.comex.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "SELECT categoria.nome AS nomeCategoria, COUNT(item.id) AS quantidadeProdutosVendidos, SUM((item.preco_unitario * item.quantidade)) AS montante "
            + "FROM pedidos "
            + "JOIN itens_de_pedido item "
            + "JOIN produtos produto "
            + "JOIN categorias categoria "
            + "WHERE pedidos.id = item.pedido_id AND item.produto_id = produto.id AND produto.categoria_id = categoria.id "
            + "GROUP BY categoria.id, pedidos.id, item.id", nativeQuery = true)
    List<PedidosPorCategoriaProjection> findPedidosPorCategoria();
}
