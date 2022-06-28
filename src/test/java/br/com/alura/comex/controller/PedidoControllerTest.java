package br.com.alura.comex.controller;

import org.json.JSONArray;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveriaAdicionarPedidos() throws Exception {

        URI uri = new URI("/pedidos");

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
    public void deveriaListarTodosOsPedidos() throws Exception {

        URI uri = new URI("/pedidos");
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").isNotEmpty())
                .andExpect(jsonPath("$.content[0].idCliente").value(15))
                .andExpect(jsonPath("$.content[0].nomeCliente").value("Isabella"));

    }

    @Test
    public void deveriaListarOPedidoPorId() throws Exception {

        int idPedido = 25;
        URI uri = new URI("/pedidos/"+idPedido);

        JSONObject json = criarObjetoJson();
        String request = json.toString();

        mockMvc
                .perform(get(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeCliente").value("Dara"));
    }

    @Test
    public void deveriaRetornar404PorIdNaoExistente() throws Exception {

        int idPedido = 99999;
        URI uri = new URI("/pedidos/"+idPedido);

        JSONObject json = criarObjetoJson();
        String request = json.toString();

        mockMvc
                .perform(get(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());

    }

    private JSONObject criarObjetoJson() throws JSONException {

        JSONObject item1 = new JSONObject()
                .put("idProduto", 3)
                .put("quantidadeVendida", 2);

        JSONObject item2 = new JSONObject()
                .put("idProduto", 33)
                .put("quantidadeVendida", 1);

        JSONArray listaDeItens = new JSONArray();
        listaDeItens.put(item1);
        listaDeItens.put(item2);

        JSONObject objeto = new JSONObject();
        objeto.put("idCliente", 15);
        objeto.put("listaDeItens", listaDeItens);

        return objeto;

    }

}
