package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.PedidoDto;
import br.com.alura.comex.controller.form.atualizacao.AtualizarPedidoForm;
import br.com.alura.comex.controller.form.cadastro.ClienteForm;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PedidoDto> listarTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return PedidoDto.converter(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> pesquisarPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent())
            return ResponseEntity.ok(new PedidoDto(pedido.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid ClienteForm clienteForm, UriComponentsBuilder uriBuilder) {
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PedidoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarPedidoForm form) {
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

}
