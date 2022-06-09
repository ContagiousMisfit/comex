package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.ProdutoDto;
import br.com.alura.comex.controller.form.atualizacao.AtualizarProdutoForm;
import br.com.alura.comex.controller.form.cadastro.ProdutoForm;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<ProdutoDto> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        return ProdutoDto.converter(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> pesquisarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent())
            return ResponseEntity.ok(new ProdutoDto(produto.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Produto> atualizar(@RequestBody @Valid AtualizarProdutoForm form) {
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

}
