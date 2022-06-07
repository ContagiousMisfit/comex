package br.com.alura.comex.main;

import br.com.alura.comex.dao.ProdutoDAO;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.model.utils.ProdutoBuilder;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class MainProdutoDao {

    static EntityManager em = JPAUtil.getEntityManager();

    public static void main(String[] args) {

        popularBancoDeDados();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        produtoDAO.listarTodos();
        produtoDAO.listarIndisponiveis();

        List<Produto> listaDeProdutosMaisVendidos = produtoDAO.getProdutosMaisVendidos();
        listaDeProdutosMaisVendidos.forEach(System.out::println);

        List<Produto> listaDeProdutosNuncaVendidos = produtoDAO.getProdutosNuncaVendidos();
        listaDeProdutosNuncaVendidos.forEach(System.out::println);

    }

    private static void popularBancoDeDados() {

        EntityManager em = JPAUtil.getEntityManager();

        Produto produto1 = new ProdutoBuilder()
                .comNome("Cyberpunk 2077")
                .comCategoria(new Categoria("JOGOS", StatusCategoria.ATIVA))
                .comDescricao("Jogo de um futuro distópico")
                .comPrecoUnitario(new BigDecimal("220.00"))
                .quantidadeEmEstoque(3)
                .build();

        Produto produto2 = new ProdutoBuilder()
                .comNome("Violino")
                .comCategoria(new Categoria("MÚSICA", StatusCategoria.ATIVA))
                .comDescricao("Instrumento musical de cordas")
                .comPrecoUnitario(new BigDecimal("3000.00"))
                .quantidadeEmEstoque(1)
                .build();

        Produto produto3 = new ProdutoBuilder()
                .comNome("Flauta doce")
                .comCategoria(new Categoria("MÚSICA", StatusCategoria.ATIVA))
                .comDescricao("Instrumento musical de sopro")
                .comPrecoUnitario(new BigDecimal("250.00"))
                .quantidadeEmEstoque(0)
                .build();

        Produto produto4 = new ProdutoBuilder()
                .comNome("Subida ao Monte Carmelo")
                .comCategoria(new Categoria("LIVROS", StatusCategoria.ATIVA))
                .comDescricao("Livro de espiritualidade de São João da Cruz")
                .comPrecoUnitario(new BigDecimal("75.00"))
                .quantidadeEmEstoque(0)
                .build();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        em.getTransaction().begin();

        produtoDAO.cadastrar(produto1);
        produtoDAO.cadastrar(produto2);
        produtoDAO.cadastrar(produto3);
        produtoDAO.cadastrar(produto4);

        em.getTransaction().commit();
        em.close();

    }

}
