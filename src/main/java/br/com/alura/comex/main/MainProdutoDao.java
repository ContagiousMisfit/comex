package br.com.alura.comex.main;

import br.com.alura.comex.dao.ProdutoDAO;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.Status;
import br.com.alura.comex.model.utils.ProdutoBuilder;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class MainProdutoDao {

    public void main(String[] args) {

        Produto produto1 = new ProdutoBuilder()
                .comNome("Cyberpunk 2077")
                .comCategoria(new Categoria("JOGOS", Status.ATIVA))
                .comDescricao("Jogo de um futuro distópico")
                .comPrecoUnitario(new BigDecimal("220.00"))
                .quantidadeEmEstoque(3)
                .build();

        Produto produto2 = new ProdutoBuilder()
                .comNome("Violino")
                .comCategoria(new Categoria("MÚSICA", Status.ATIVA))
                .comDescricao("Instrumento musical de cordas")
                .comPrecoUnitario(new BigDecimal("3000.00"))
                .quantidadeEmEstoque(1)
                .build();

        Produto produto3 = new ProdutoBuilder()
                .comNome("Flauta doce")
                .comCategoria(new Categoria("MÚSICA", Status.ATIVA))
                .comDescricao("Instrumento musical de sopro")
                .comPrecoUnitario(new BigDecimal("250.00"))
                .quantidadeEmEstoque(0)
                .build();

        Produto produto4 = new ProdutoBuilder()
                .comNome("Subida ao Monte Carmelo")
                .comCategoria(new Categoria("LIVROS", Status.ATIVA))
                .comDescricao("Livro de espiritualidade de São João da Cruz")
                .comPrecoUnitario(new BigDecimal("75.00"))
                .quantidadeEmEstoque(0)
                .build();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        produtoDAO.cadastrar(produto1);
        produtoDAO.cadastrar(produto2);
        produtoDAO.cadastrar(produto3);
        produtoDAO.cadastrar(produto4);

        produtoDAO.listarIndisponiveis();

    }

}
