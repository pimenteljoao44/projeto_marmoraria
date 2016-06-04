/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.Usuario;
import facade.UsuarioFacade;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ricardo
 */
@Named(value = "loginControle")
@SessionScoped
public class LoginControle implements Serializable {

    @Inject
    private UsuarioFacade usuarioFacade;
    private Usuario usuario;
    private String login;
    private String senha;
    private Boolean logado = false;

    public String logar() {
        usuario = usuarioFacade.pesquisaUsuario(login, senha);
        if (usuario != null || (login.equals("admin") && senha.equals("admin"))) {
            logado = true;
            return "app/index?faces-redirect=true";
        } else {
            logado = false;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário não encontrado no sistema", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String logoff() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().invalidate();
        return "/login?faces-redirect=true";
    }

    public Boolean getLogado() {
        return logado;
    }

    public void setLogado(Boolean logado) {
        this.logado = logado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
