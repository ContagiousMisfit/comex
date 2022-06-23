package br.com.alura.comex.repository;

import br.com.alura.comex.controller.dto.projections.PedidosPorCategoriaProjection;
import br.com.alura.comex.model.*;
import br.com.alura.comex.model.utils.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PedidoRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemDePedidoRepository itemDePedidoRepository;

    @Test
    public void deveriaRetornar2Registros() {

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

        ItemDePedido item1 = new ItemDePedidoBuilder()
                .comProduto(produto1)
                .comPrecoUnitario(produto1.getPrecoUnitario())
                .comQuantidade(1)
                .aplicarDesconto()
                .build();

        ItemDePedido item2 = new ItemDePedidoBuilder()
                .comProduto(produto2)
                .comPrecoUnitario(produto2.getPrecoUnitario())
                .comQuantidade(10)
                .aplicarDesconto()
                .build();

        List<ItemDePedido> listaDeItensProduto1 = List.of(item1, item1);
        List<ItemDePedido> listaDeItensProduto2 = List.of(item2);

        Pedido pedido1 = new PedidoBuilder()
                .comCliente(cliente)
                .comDataAtual()
                .comListaDePedidos(listaDeItensProduto1)
                .aplicarDesconto()
                .build();

        Pedido pedido2 = new PedidoBuilder()
                .comCliente(cliente)
                .comDataAtual()
                .comListaDePedidos(listaDeItensProduto2)
                .aplicarDesconto()
                .build();

        item1.setPedido(pedido1);
        item2.setPedido(pedido2);

        categoriaRepository.save(categoria1);
        categoriaRepository.save(categoria2);
        clienteRepository.save(cliente);
        pedidoRepository.save(pedido1);
        pedidoRepository.save(pedido2);
        produtoRepository.save(produto1);
        produtoRepository.save(produto2);
        itemDePedidoRepository.save(item1);
        itemDePedidoRepository.save(item2);

        List<PedidosPorCategoriaProjection> resultado = categoriaRepository.findPedidosPorCategoria();

        assertThat(resultado)
                .hasSize(2)
                .extracting(PedidosPorCategoriaProjection::getNomeCategoria,
                        PedidosPorCategoriaProjection::getQuantidadeProdutosVendidos,
                        PedidosPorCategoriaProjection::getMontante)
                .containsExactly(
                        tuple("Instrumentos Musicais", 1, new BigDecimal("3500.00")),
                        tuple("Livros", 10, new BigDecimal("400.00"))
                );

    }

}
