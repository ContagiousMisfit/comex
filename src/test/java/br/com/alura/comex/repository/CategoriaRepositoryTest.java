package br.com.alura.comex.repository;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.model.utils.CategoriaBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void deveriaRetornar2Registros() {

        Categoria categoria1 =
                new CategoriaBuilder()
                        .comNome("Eletrônicos")
                        .comStatus(StatusCategoria.ATIVA)
                        .build();

        Categoria categoria2 =
                new CategoriaBuilder()
                        .comNome("Geek")
                        .comStatus(StatusCategoria.ATIVA)
                        .build();

        categoriaRepository.save(categoria1);
        categoriaRepository.save(categoria2);

        List<Categoria> resultado = categoriaRepository.findAll();

        assertThat(resultado)
                .extracting(Categoria::getNome, Categoria::getStatus)
                .contains(
                        tuple("Eletrônicos", StatusCategoria.ATIVA),
                        tuple("Geek", StatusCategoria.ATIVA)
                );

    }


}
