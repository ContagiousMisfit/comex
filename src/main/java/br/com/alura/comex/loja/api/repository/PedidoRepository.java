package br.com.alura.comex.loja.api.repository;

import br.com.alura.comex.loja.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
