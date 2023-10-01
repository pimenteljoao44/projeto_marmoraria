package controle;

import converter.ConverterGenerico;
import entidades.Fornecedor;
import entidades.Pessoa;
import entidades.PessoaFisica;
import entidades.PessoaJuridica;
import facade.FornecedorFacade;
import facade.PessoaFacade;
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
public class FornecedorControle implements Serializable {

    @Inject
    private FornecedorFacade fornecedorFacade;
    private Fornecedor fornecedor;
    private String tipoPessoa;

    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(fornecedorFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        tipoPessoa = "PF";
        criaFornecedor();
    }

    public void criaFornecedor() {
        if (tipoPessoa.equals("PF")) {
            fornecedor = new Fornecedor();
        } else {
            fornecedor = new Fornecedor();        }
    }

    public void carregaFornecedor(Fornecedor f) {
        if (f.getPessoa() instanceof PessoaFisica) {
            tipoPessoa = "PF";
        } else {
            tipoPessoa = "PJ";
        }
        fornecedor = f;
    }

    public String salvar() {
        try {
            fornecedorFacade.salvar(fornecedor);
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String excluir(Fornecedor f) {
        fornecedorFacade.excluir(f);
        return "list?faces-redirect=true";
    }

    public List<Fornecedor> getListagem() {
        return fornecedorFacade.listar();
    }

    public List<Fornecedor> fornecedorAutoComplete(String nome) {
        return fornecedorFacade.fornecedorAutoComplete(nome);
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

}