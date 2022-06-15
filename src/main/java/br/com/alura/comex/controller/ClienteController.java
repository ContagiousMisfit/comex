package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.ClienteDto;
import br.com.alura.comex.controller.form.atualizacao.AtualizarClienteForm;
import br.com.alura.comex.controller.form.cadastro.ClienteForm;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<Page<ClienteDto>> listarTodos() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Cliente> clientes = clienteRepository.findAll(pageable);
        Page<ClienteDto> clienteDtos = ClienteDto.converterPagina(clientes);
        return ResponseEntity.ok().body(clienteDtos);
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
        Cliente cliente = form.converter();
        clienteRepository.save(cliente);

        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDto(cliente));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarClienteForm form) {
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Cliente> optional = clienteRepository.findById(id);
        if (optional.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
