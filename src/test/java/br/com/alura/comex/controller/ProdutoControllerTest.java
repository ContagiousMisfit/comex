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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
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
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));

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
