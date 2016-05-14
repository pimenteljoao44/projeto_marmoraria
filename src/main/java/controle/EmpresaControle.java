/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Empresa;
import entidades.Grupo;
import facade.EmpresaFacade;
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
public class EmpresaControle implements Serializable {

    private Empresa empresa;
    @Inject
    private EmpresaFacade empresaFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(empresaFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        empresa = new Empresa();
    }

    public String salvar() {
        try {
            empresaFacade.salvar(empresa);
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String excluir(Empresa e) {
        empresaFacade.excluir(e);
        return "list?faces-redirect=true";
    }

    public List<Empresa> getListagem() {
        return empresaFacade.listar();
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
