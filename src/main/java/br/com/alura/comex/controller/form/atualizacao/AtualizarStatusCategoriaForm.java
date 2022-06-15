package br.com.alura.comex.controller.form.atualizacao;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.repository.CategoriaRepository;

public class AtualizarStatusCategoriaForm {
    public Categoria atualizarStatus(Long id, CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.getReferenceById(id);
        if (categoria.getStatus().equals(StatusCategoria.ATIVA)) {
            categoria.setStatus(StatusCategoria.INATIVA);
            return categoria;
        }
        categoria.setStatus(StatusCategoria.ATIVA);
        return categoria;
    }
}
