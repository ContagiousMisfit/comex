package br.com.alura.comex.main;

import br.com.alura.comex.dao.CategoriaDAO;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.model.utils.CategoriaBuilder;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;

public class MainCategoriaDao {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        popularBancoDeDados();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        categoriaDAO.listarTodas();
        categoriaDAO.listarInativas();
    }

    private static void popularBancoDeDados() {
        EntityManager em = JPAUtil.getEntityManager();

        Categoria categoria1 = new CategoriaBuilder()
                .comNome("JOGOS")
                .comStatus(StatusCategoria.ATIVA)
                .build();

        Categoria categoria2 = new CategoriaBuilder()
                .comNome("LIVROS")
                .comStatus(StatusCategoria.ATIVA)
                .build();

        Categoria categoria3 = new CategoriaBuilder()
                .comNome("VESTUÁRIO")
                .comStatus(StatusCategoria.ATIVA)
                .build();

        CategoriaDAO categoriaDao = new CategoriaDAO(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(categoria1);
        categoriaDao.cadastrar(categoria2);
        categoriaDao.cadastrar(categoria3);

        categoria3.setNome("MÚSICA");
        categoria3.setStatus(StatusCategoria.INATIVA);

        categoriaDao.atualizar(categoria3);

        em.getTransaction().commit();
        em.close();
    }

}
