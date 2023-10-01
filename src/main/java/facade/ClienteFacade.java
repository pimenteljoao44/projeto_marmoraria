package facade;



import entidades.Cliente;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class ClienteFacade extends AbstractFacade<Cliente> implements Serializable {

    @Inject
    private EntityManager em;

    public ClienteFacade() {super(Cliente.class);
    }

    public List<Cliente> pesquisaCliente(String nome){
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.pessoa.nome LIKE :nome");
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    public List<Cliente> ClienteAutoComplete(String nome) {
        Query q = em.createQuery("FROM Cliente AS c WHERE LOWER(c.pessoa.nome) LIKE('%" + nome.toLowerCase() + "%')");
        return q.getResultList();
    }


    @Override
    protected EntityManager getEm() {return em;}
}