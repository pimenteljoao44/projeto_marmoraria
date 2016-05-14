/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Grupo;
import entidades.NivelAcesso;
import entidades.Usuario;
import facade.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ricardo
 */
@Named
@ViewScoped
public class UsuarioControle implements Serializable {

    private Usuario usuario;
    @Inject
    private UsuarioFacade usuarioFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(usuarioFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        usuario = new Usuario();
    }

    public String salvar() {
        try {
            usuarioFacade.salvar(usuario);
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String excluir(Usuario u) {
        usuarioFacade.excluir(u);
        return "list?faces-redirect=true";
    }

    public List<Usuario> getListagem() {
        return usuarioFacade.listar();
    }
    
    public NivelAcesso[] getNiveisAcesso(){
        return NivelAcesso.values();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
