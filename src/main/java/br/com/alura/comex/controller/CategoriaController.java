package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.form.atualizacao.AtualizarCategoriaForm;
import br.com.alura.comex.controller.form.cadastro.CategoriaForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
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
    public ResponseEntity<CategoriaDto> listarPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent())
            return ResponseEntity.ok(new CategoriaDto(categoria.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarCategoriaForm form) {
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

}
