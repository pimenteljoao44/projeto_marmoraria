/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Usuario;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import persistencia.Transacional;

/**
 *
 * @author ricardo
 */
@Transacional
public class UsuarioFacade extends AbstractFacade<Usuario> implements Serializable{

    @Inject
    private EntityManager em;
    
    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
    
}
