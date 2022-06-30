package br.com.alura.comex.loja.api.repository;

import br.com.alura.comex.loja.domain.ItemDePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDePedidoRepository extends JpaRepository<ItemDePedido, Long> {
}
