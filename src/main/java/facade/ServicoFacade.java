package facade;

import entidades.Produto;
import entidades.Servico;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class ServicoFacade extends AbstractFacade<Servico> implements Serializable {

    @Inject
    private EntityManager em;

    public ServicoFacade() {super(Servico.class);
    }

    public List<Servico> servicoAutoComplete(String nome) {
        Query q = em.createQuery("FROM Servico AS s WHERE LOWER(s.descricao) LIKE('%" + nome.toLowerCase() + "%')");
        return q.getResultList();
    }

    @Override
    protected EntityManager getEm() {return em;}
}
