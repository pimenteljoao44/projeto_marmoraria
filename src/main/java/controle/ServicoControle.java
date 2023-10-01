package controle;

import converter.ConverterGenerico;
import entidades.Servico;
import facade.ServicoFacade;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ServicoControle implements Serializable {

    private Servico servico;
    @Inject
    private ServicoFacade servicoFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(servicoFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        servico = new Servico();
    }

    public String salvar() {
        try {
            servicoFacade.salvar(servico);
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String excluir(Servico s) {
        servicoFacade.excluir(s);
        return "list?faces-redirect=true";
    }

    public List<Servico> getListagem() {
        return servicoFacade.listar();
    }

    public List<Servico> servicoAutoComplete(String nome) {
        return servicoFacade.servicoAutoComplete(nome);
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}

