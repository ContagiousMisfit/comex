package br.com.alura.comex.loja.api.rest;

import br.com.alura.comex.loja.api.model.ProdutoDto;
import br.com.alura.comex.loja.api.model.form.atualizacao.AtualizarProdutoForm;
import br.com.alura.comex.loja.api.model.form.cadastro.ProdutoForm;
import br.com.alura.comex.loja.api.repository.CategoriaRepository;
import br.com.alura.comex.loja.api.repository.ProdutoRepository;
import br.com.alura.comex.loja.domain.Produto;
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
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> listarTodos() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Produto> produtos = produtoRepository.findAll(pageable);
        Page<ProdutoDto> produtosDto = ProdutoDto.converterPagina(produtos);
        return ResponseEntity.ok().body(produtosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> pesquisarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent())
            return ResponseEntity.ok(new ProdutoDto(produto.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {

        Produto produto = form.converter(categoriaRepository);
        produtoRepository.save(produto);

        URI uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDto(produto));

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarProdutoForm form) {
        Optional<Produto> optional = produtoRepository.findById(id);
        if (optional.isPresent()) {
            Produto produto = form.atualizar(id, categoriaRepository, produtoRepository);
            return ResponseEntity.ok(new ProdutoDto(produto));
        }
        return ResponseEntity.notFound().build();
    }

}
