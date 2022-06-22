package br.com.alura.comex.controller;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.model.utils.CategoriaBuilder;
import br.com.alura.comex.model.utils.ClienteBuilder;
import br.com.alura.comex.model.utils.EnderecoBuilder;
import br.com.alura.comex.model.utils.ProdutoBuilder;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("testes")
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private String token;

    @BeforeAll
    public void prepararCenario() throws Exception {
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

        categoriaRepository.save(categoria1);
        categoriaRepository.save(categoria2);
        clienteRepository.save(cliente);
        produtoRepository.save(produto1);
        produtoRepository.save(produto2);

        URI uri = new URI("/auth");
        String json = "{\"email\":\"cliente@email.com\",\"senha\":\"123456\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
    }

    public String concederTokenParaAutenticacaoDeUsuarioExistente() throws Exception {
        String email = "cliente@email.com";
        String senha = "123456";

        String requestBody = "{\"email\":\"" + email + "\", \"senha\":\"" + senha + "\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth")
                        .content(requestBody))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"access_token\": \"", "");
        return token = response.replace("\"}", "");

    }

    @Test
    public void deveriaAdicionar3Produtos() throws Exception {

        token = concederTokenParaAutenticacaoDeUsuarioExistente();

        URI uri = new URI("/api/pedidos");

        String json = "{\"nome\": \"Guzheng\", \"descricao\": \"Instrumento musical antigo, uma espécie de cítara chinesa\", \"precoUnitario\": 70000.00, \"quantidadeEmEstoque\": 5, \"idCategoria\": 1}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer" + token)
                )
                .andExpect(status()
                        .is(201));

        List<Produto> produtoList = produtoRepository.findAll();
        assertThat(produtoList).hasSize(3);
    }


}
