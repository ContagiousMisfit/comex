package br.com.alura.comex.loja.api.repository;

import br.com.alura.comex.loja.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
