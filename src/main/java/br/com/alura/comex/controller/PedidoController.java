package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.ClienteDto;
import br.com.alura.comex.controller.dto.DetalhesDoPedidoDto;
import br.com.alura.comex.controller.dto.PedidoDto;
import br.com.alura.comex.controller.form.atualizacao.AtualizarPedidoForm;
import br.com.alura.comex.controller.form.cadastro.PedidoForm;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.repository.PedidoRepository;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listarTodos() {
        Pageable pageable = PageRequest.of(0, 5,Sort.by("data").descending().and(Sort.by("cliente").ascending()));
        Page<Pedido> pedidos = pedidoRepository.findAll(pageable);
        Page<PedidoDto> pedidoDtos = PedidoDto.converterPagina(pedidos);
        return ResponseEntity.ok().body(pedidoDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoPedidoDto> detalhar(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent())
            return ResponseEntity.ok(new DetalhesDoPedidoDto(pedido.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesDoPedidoDto> cadastrar(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriBuilder) {
        Pedido pedido = form.converter();
        pedidoRepository.save(pedido);

        URI uri = uriBuilder.path("api/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesDoPedidoDto(pedido));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesDoPedidoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarPedidoForm form) {
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

}
