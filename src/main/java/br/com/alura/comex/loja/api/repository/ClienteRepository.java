package br.com.alura.comex.loja.api.repository;

import br.com.alura.comex.loja.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCpf(String cpf);

}
