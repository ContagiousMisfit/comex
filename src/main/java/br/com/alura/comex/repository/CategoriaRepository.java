package br.com.alura.comex.repository;

import br.com.alura.comex.controller.dto.projections.PedidosPorCategoriaProjection;
import br.com.alura.comex.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "SELECT categoria.nome, SUM(itens_de_pedido.quantidade), SUM(pedidos.valor_total) FROM categorias categoria INNER JOIN produtos ON produtos.categoria_id = categoria.id INNER JOIN itens_de_pedido ON itens_de_pedido.produto_id = produtos.id INNER JOIN pedidos ON pedidos.id = itens_de_pedido.pedido_id;", nativeQuery = true)
    List<PedidosPorCategoriaProjection> findPedidosPorCategoria();
}
