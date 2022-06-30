package br.com.alura.comex.loja.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveriaAdicionarPedidos() throws Exception {

        URI uri = new URI("/clientes");

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
    public void deveriaListarTodosOsClientes() throws Exception {

        URI uri = new URI("/clientes");
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").isNotEmpty())
                .andExpect(jsonPath("$.content[0].nome").value("Alexandre"))
                .andExpect(jsonPath("$.content[1].nome").value("Bruna"));

    }

    @Test
    public void deveriaListarClientePorId() throws Exception {

        int idCliente = 20;
        URI uri = new URI("/clientes/"+idCliente);

        JSONObject json = criarObjetoJson();
        String request = json.toString();

        mockMvc
                .perform(get(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Gabriel"));
    }

    @Test
    public void deveriaRetornar404PorIdNaoExistente() throws Exception {

        int idCliente = 99999;
        URI uri = new URI("/clientes/"+idCliente);

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
        return new JSONObject()
                .put("nome","Camilla")
                .put("cpf", "12589465")
                .put("telefone","119865481")
                .put("rua", "Times Square Avenue")
                .put("numero", "10036")
                .put("bairro", "Manhattan")
                .put("cidade", "New York")
                .put("estado", "New York");
    }

}