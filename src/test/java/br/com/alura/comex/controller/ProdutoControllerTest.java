package br.com.alura.comex.controller;

import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void deveriaAdicionarProdutos() throws Exception {

        URI uri = new URI("/produtos");

        JSONObject json = criarObjetoJson();
        String request = json.toString();

        mockMvc
                .perform(post(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

    }

    @Test
    public void deveriaListarTodosOsProdutos() throws Exception {

        URI uri = new URI("/produtos");

        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").isNotEmpty())
                .andExpect(jsonPath("$.content[0].nome").value("Guzheng"));
    }

    @Test
    public void deveriaListarOProdutoPorId() throws Exception {

        int idProduto = 33;
        URI uri = new URI("/produtos/"+idProduto);

        JSONObject json = criarObjetoJson();
        String request = json.toString();

        mockMvc
                .perform(get(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Violino Stradivarius"));
    }

    @Test
    public void deveriaAtualizarProduto() throws Exception {

        int idProduto = 3;
        URI uri = new URI("/produtos/"+idProduto);

        JSONObject json = criarObjetoJson();
        json.put("quantidadeEmEstoque", 3);
        json.put("precoUnitario", 75000.00);
        String request = json.toString();

        mockMvc
                .perform(put(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantidadeEmEstoque").value(3))
                .andExpect(jsonPath("$.precoUnitario").value(75000.00));

    }

    private JSONObject criarObjetoJson() throws JSONException {
        return new JSONObject()
                .put("nome","Guzheng")
                .put("descricao", "Instrumento musical antigo, uma espécie de cítara chinesa")
                .put("precoUnitario",70000.00)
                .put("quantidadeEmEstoque", 5)
                .put("idCategoria", 10);
    }

}
