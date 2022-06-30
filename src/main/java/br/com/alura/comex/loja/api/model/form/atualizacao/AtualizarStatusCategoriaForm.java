package br.com.alura.comex.loja.api.model.form.atualizacao;

import br.com.alura.comex.loja.api.repository.CategoriaRepository;
import br.com.alura.comex.loja.domain.Categoria;
import br.com.alura.comex.loja.domain.StatusCategoria;

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
