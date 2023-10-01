package facade;

import entidades.Servico;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;

public class ServicoFacade extends AbstractFacade<Servico> implements Serializable {

    @Inject
    private EntityManager em;

    public ServicoFacade() {super(Servico.class);
    }

    @Override
    protected EntityManager getEm() {return em;}
}
