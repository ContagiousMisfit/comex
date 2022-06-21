package br.com.alura.comex.controller;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.model.utils.CategoriaBuilder;
import br.com.alura.comex.model.utils.ClienteBuilder;
import br.com.alura.comex.model.utils.EnderecoBuilder;
import br.com.alura.comex.model.utils.ProdutoBuilder;
import br.com.alura.comex.repository.ProdutoRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("testes")
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ProdutoRepository produtoRepository;

    @BeforeAll
    public void prepararCenario() throws URISyntaxException {
        Categoria categoria1 =
                new CategoriaBuilder()
                        .comNome("Instrumentos Musicais")
                        .comStatus(StatusCategoria.ATIVA)
                        .build();

        Categoria categoria2 =
                new CategoriaBuilder()
                        .comNome("Livros")
                        .comStatus(StatusCategoria.ATIVA)
                        .build();

        Produto produto1 = new ProdutoBuilder()
                .comCategoria(categoria1)
                .comNome("Violino Eagle")
                .comDescricao("O violino é um instrumento musical, classificado como Instrumento de cordas ou cordofone. Foi inventado por Gasparo de Salò.")
                .comPrecoUnitario(new BigDecimal("3500.00"))
                .build();

        Produto produto2 = new ProdutoBuilder()
                .comCategoria(categoria2)
                .comNome("O Pequeno Príncipe")
                .comDescricao("Tu te tornas eternamente responsável por aquilo que cativas")
                .comPrecoUnitario(new BigDecimal("40.00"))
                .build();

        Cliente cliente = new ClienteBuilder()
                .comNome("Corporação musical Santa Cecília")
                .comCpf("123456789")
                .comTelefone("1189756482")
                .comEndereco(new EnderecoBuilder()
                        .comRua("Rua da Paz")
                        .comBairro("Carmelo")
                        .comCidade("São Paulo")
                        .comComplemento("Casa")
                        .comEstado("São Paulo")
                        .comNumero("733")
                        .build())
                .build();

        testEntityManager.persist(categoria1);
        testEntityManager.persist(categoria2);
        testEntityManager.persist(cliente);
        testEntityManager.persist(produto1);
        testEntityManager.persist(produto2);
    }
    @Test
    public void deveriaAdicionar3Produtos() throws Exception {

        URI uri = new URI("/pedidos");

        String json = "{\n" +
                "    \"nome\": \"Guzheng\",\n" +
                "    \"descricao\": \"Instrumento musical antigo, uma espécie de cítara chinesa\",\n" +
                "    \"precoUnitario\": 70000.00,\n" +
                "    \"quantidadeEmEstoque\": 5,\n" +
                "    \"idCategoria\": 1\n" +
                "    }\n";

        mockMvc
                .perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));

        List<Produto> produtoList = produtoRepository.findAll();
        assertThat(produtoList).hasSize(3);
    }


}
