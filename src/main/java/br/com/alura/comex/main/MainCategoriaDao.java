package br.com.alura.comex.main;

import br.com.alura.comex.dao.CategoriaDAO;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Status;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;


public class MainCategoriaDao {

    public void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        Categoria categoria1 = new Categoria("JOGOS", Status.ATIVA);
        Categoria categoria2 = new Categoria("LIVROS", Status.ATIVA);
        Categoria categoria3 = new Categoria("VESTUÁRIO", Status.ATIVA);

        CategoriaDAO categoriaDao = new CategoriaDAO(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(categoria1);
        categoriaDao.cadastrar(categoria2);
        categoriaDao.cadastrar(categoria3);
        em.getTransaction().commit();

        categoria3.setNome("MÚSICA");
        categoriaDao.atualizar(categoria3);

        categoria3.setStatus(Status.INATIVA);
        categoriaDao.atualizar(categoria3);

        em.close();
    }

}
