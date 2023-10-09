package controle;

import converter.ConverterGenerico;
import entidades.*;
import facade.OrdemDeServicoFacade;
import facade.VendaFacade;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class OrdemDeServicoControle implements Serializable {

    private OrdemDeServico ordemDeServico;

    @Inject
    private OrdemDeServicoFacade ordemDeServicoFacade;
    private ConverterGenerico converterGenerico;
    private Produto produto = new Produto();

    private Servico servico = new Servico();

    public void addItem() {
        try {
            produto = new Produto();
            ordemDeServico.addItem(produto);
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage message
                    = new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void addServico() {
        try {
            ordemDeServico.addItem(produto);
            produto = new Produto();
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage message
                    = new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void removeItem(Produto i) {
        ordemDeServico.removeItem(i);
    }

    public void removerServico(Servico i){
        ordemDeServico.removerServico(i);
    }

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(ordemDeServicoFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        ordemDeServico = new OrdemDeServico();
        produto = new Produto();
    }

    public String salvar() {
        try {
            ordemDeServicoFacade.salvar(ordemDeServico);
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String excluir(OrdemDeServico o) {
        ordemDeServicoFacade.excluir(o);
        return "list?faces-redirect=true";
    }

    public List<OrdemDeServico> getListagem() {
        return ordemDeServicoFacade.listar();
    }

    public OrdemDeServico getOrdemDeServico() {
        return ordemDeServico;
    }

    public void setOrdemDeServico(OrdemDeServico ordemDeServico) {
        this.ordemDeServico = ordemDeServico;
    }

    public OrdemDeServicoFacade getOrdemDeServicoFacade() {
        return ordemDeServicoFacade;
    }

    public void setOrdemDeServicoFacade(OrdemDeServicoFacade ordemDeServicoFacade) {
        this.ordemDeServicoFacade = ordemDeServicoFacade;
    }

    public ConverterGenerico getConverterGenerico() {
        return converterGenerico;
    }

    public void setConverterGenerico(ConverterGenerico converterGenerico) {
        this.converterGenerico = converterGenerico;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
