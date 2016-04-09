/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Pessoa;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import persistencia.Transacional;

/**
 *
 * @author ricardo
 */
@Transacional
public class PessoaFacade extends AbstractFacade<Pessoa> implements Serializable{

    @Inject
    private EntityManager em;
    
    public PessoaFacade() {
        super(Pessoa.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
    
}
