/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.OrdemDeServico;
import entidades.Venda;
import persistencia.Transacional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 *
 * @author ricardo
 */
@Transacional
public class OrdemDeServicoFacade extends AbstractFacade<OrdemDeServico> implements Serializable{

    @Inject
    private EntityManager em;

    public OrdemDeServicoFacade() {
        super(OrdemDeServico.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
    
}