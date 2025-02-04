package br.com.alura.comex.loja.api.rest;

import br.com.alura.comex.loja.api.model.CategoriaDto;
import br.com.alura.comex.loja.api.model.form.atualizacao.AtualizarStatusCategoriaForm;
import br.com.alura.comex.loja.api.model.form.cadastro.CategoriaForm;
import br.com.alura.comex.loja.api.model.projections.PedidosPorCategoriaProjection;
import br.com.alura.comex.loja.api.repository.CategoriaRepository;
import br.com.alura.comex.loja.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<CategoriaDto> listarTodos() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return CategoriaDto.converter(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> pesquisarPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent())
            return ResponseEntity.ok(new CategoriaDto(categoria.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDePedidosPorCategoria", allEntries = true)
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
        Categoria categoria = form.converter();
        categoriaRepository.save(categoria);

        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    @GetMapping("/pedidos")
    @Cacheable(value = "listaDePedidosPorCategoria")
    public List<PedidosPorCategoriaProjection> listarPedidosPorCategoria() {
        List<PedidosPorCategoriaProjection> listaDePedidos = categoriaRepository.findPedidosPorCategoria();
        return listaDePedidos;
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizarStatusCategoria(@PathVariable Long id, @Valid AtualizarStatusCategoriaForm form) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            Categoria categoria = form.atualizarStatus(id, categoriaRepository);
            return ResponseEntity.ok(new CategoriaDto(categoria));
        }
        return ResponseEntity.notFound().build();
    }

}
