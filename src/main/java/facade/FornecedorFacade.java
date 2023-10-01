package facade;

import entidades.Fornecedor;
import entidades.Pessoa;
import entidades.Produto;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class FornecedorFacade extends AbstractFacade<Fornecedor> implements Serializable {

    @Inject
    private EntityManager em;

    public FornecedorFacade() {super(Fornecedor.class);
    }

    public List<Fornecedor> pesquisaFornecedor(String nome){
        Query query = em.createQuery("SELECT f FROM Fornecedor f WHERE f.pessoa.nome LIKE :nome");
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    public List<Fornecedor> fornecedorAutoComplete(String nome) {
        Query q = em.createQuery("FROM Fornecedor AS f WHERE LOWER(f.pessoa.nome) LIKE('%" + nome.toLowerCase() + "%')");
        return q.getResultList();
    }

    public List<Fornecedor> obterTodos() {
        try {
            Query query = em.createQuery("SELECT f FROM Fornecedor f");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected EntityManager getEm() {return em;}
}
