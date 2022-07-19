package br.com.alura.comex.loja.api.rest;

import br.com.alura.comex.loja.PedidoGeradoEvent;
import br.com.alura.comex.loja.StreamConfig;
import br.com.alura.comex.loja.api.model.DetalhesDoPedidoDto;
import br.com.alura.comex.loja.api.model.PedidoDto;
import br.com.alura.comex.loja.api.model.form.cadastro.PedidoForm;
import br.com.alura.comex.loja.api.repository.ClienteRepository;
import br.com.alura.comex.loja.api.repository.PedidoRepository;
import br.com.alura.comex.loja.api.repository.ProdutoRepository;
import br.com.alura.comex.loja.domain.Pedido;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final StreamConfig.PedidoGeradoSource pedidoGeradoSource;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listarTodos() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("data").descending().and(Sort.by("cliente").ascending()));
        Page<Pedido> pedidos = pedidoRepository.findAll(pageable);
        Page<PedidoDto> pedidoDtos = PedidoDto.converterPagina(pedidos);
        return ResponseEntity.ok().body(pedidoDtos);
    }


    @GetMapping("/{id}")
    @PreAuthorize(value = "authentication.principal.username.equals(#userId)")
    public ResponseEntity<DetalhesDoPedidoDto> detalhar(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent())
            return ResponseEntity.ok(new DetalhesDoPedidoDto(pedido.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesDoPedidoDto> cadastrar(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriBuilder) {
        Pedido pedido = form.converter(clienteRepository, produtoRepository);
        pedidoRepository.save(pedido);

        PedidoGeradoEvent event = new PedidoGeradoEvent(pedido.getId());
        Message<PedidoGeradoEvent> message = MessageBuilder.withPayload(event).build();

        pedidoGeradoSource.pedidoGerado().send(message);

        URI uri = uriBuilder.path("api/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesDoPedidoDto(pedido));
    }


}
