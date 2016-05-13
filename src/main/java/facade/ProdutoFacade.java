/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Produto;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Transacional;

/**
 *
 * @author ricardo
 */
@Transacional
public class ProdutoFacade extends AbstractFacade<Produto> implements Serializable {

    @Inject
    private EntityManager em;

    public ProdutoFacade() {
        super(Produto.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public List<Produto> produtoAutoComplete(String nome) {
        Query q = em.createQuery("FROM Produto AS p WHERE LOWER(p.nome) LIKE('%" + nome.toLowerCase() + "%')");
        return q.getResultList();
    }
}
