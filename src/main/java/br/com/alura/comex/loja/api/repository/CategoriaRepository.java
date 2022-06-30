package br.com.alura.comex.loja.api.repository;

import br.com.alura.comex.loja.api.model.projections.PedidosPorCategoriaProjection;
import br.com.alura.comex.loja.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "SELECT categoria.nome AS nomeCategoria, SUM(item.quantidade) AS quantidadeProdutosVendidos, SUM((item.preco_unitario * item.quantidade)) AS montante "
            + "FROM pedidos "
            + "JOIN itens_de_pedido item "
            + "JOIN produtos produto "
            + "JOIN categorias categoria "
            + "WHERE pedidos.id = item.pedido_id AND item.produto_id = produto.id AND produto.categoria_id = categoria.id "
            + "GROUP BY categoria.id, pedidos.id, item.id", nativeQuery = true)
    List<PedidosPorCategoriaProjection> findPedidosPorCategoria();
}


