package br.com.alura.comex.main;

import br.com.alura.comex.dao.CategoriaDAO;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;

public class MainCategoriaDao {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        Categoria categoria1 = new Categoria("JOGOS", StatusCategoria.ATIVA);
        Categoria categoria2 = new Categoria("LIVROS", StatusCategoria.ATIVA);
        Categoria categoria3 = new Categoria("VESTUÁRIO", StatusCategoria.ATIVA);

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
