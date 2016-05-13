/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Pessoa;
import entidades.PessoaFisica;
import entidades.PessoaJuridica;
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
public class PessoaControle implements Serializable {

    @Inject
    private PessoaFacade pessoaFacade;
    private Pessoa pessoa;
    private String tipoPessoa;

    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(pessoaFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        tipoPessoa = "PF";
        criaPessoa();
    }

    public void criaPessoa() {
        if (tipoPessoa.equals("PF")) {
            pessoa = new PessoaFisica();
        } else {
            pessoa = new PessoaJuridica();
        }
    }

    public void carregaPessoa(Pessoa p) {
        if (p instanceof PessoaFisica) {
            tipoPessoa = "PF";
        } else {
            tipoPessoa = "PJ";
        }
        pessoa = p;
    }

    public String salvar() {
        try {
            pessoaFacade.salvar(pessoa);
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String excluir(Pessoa p) {
        pessoaFacade.excluir(p);
        return "list?faces-redirect=true";
    }

    public List<Pessoa> getListagem() {
        return pessoaFacade.listar();
    }

    public List<Pessoa> pessoaAutoComplete(String nome) {
        return pessoaFacade.pessoaAutoComplete(nome);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

}
