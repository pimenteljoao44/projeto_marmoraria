/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Grupo;
import facade.GrupoFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ricardo
 */
@Named
@ViewScoped
public class GrupoControle implements Serializable {

    private Grupo grupo;
    @Inject
    private GrupoFacade grupoFacade;
    private ConverterGenerico converterGenerico;
    
    public ConverterGenerico converter(){
        if(converterGenerico==null){
            converterGenerico = new ConverterGenerico(grupoFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        grupo = new Grupo();
    }

    public String salvar() {
        grupoFacade.salvar(grupo);
        return "list?faces-redirect=true";
    }

    public String excluir(Grupo g) {
        grupoFacade.excluir(g);
        return "list?faces-redirect=true";
    }

    public List<Grupo> getListagem() {
        return grupoFacade.listar();
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}
