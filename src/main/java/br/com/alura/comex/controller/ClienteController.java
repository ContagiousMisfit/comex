package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.ClienteDto;
import br.com.alura.comex.controller.form.atualizacao.AtualizarClienteForm;
import br.com.alura.comex.controller.form.cadastro.ClienteForm;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<ClienteDto> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ClienteDto.converter(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> pesquisarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent())
            return ResponseEntity.ok(new ClienteDto(cliente.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarClienteForm form) {
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

}
