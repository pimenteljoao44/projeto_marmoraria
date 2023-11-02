/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Pessoa;
import entidades.Produto;
import facade.ProdutoFacade;
import java.io.Serializable;
import java.util.List;
import utils.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.RollbackException;

/**
 *
 * @author ricardo
 */
@Named
@ViewScoped
public class ProdutoControle implements Serializable {

    private Produto produto;
    @Inject
    private ProdutoFacade produtoFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(produtoFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        produto = new Produto();
    }

    public String salvar() {
        try {
            produtoFacade.salvar(produto);
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String excluir(Produto p) {
        try {
            produtoFacade.excluir(p);
            return "list?faces-redirect=true";
        } catch (RollbackException e) {
           JsfUtil.addErrorMessage("Não é possivel excluir o produto " +p.getNome()+
                   " pois ele está relacionado com uma venda");
            return null;
        }
    }

    public List<Produto> getListagem() {
        return produtoFacade.listar();
    }

    public List<Produto> produtoAutoComplete(String nome) {
        return produtoFacade.produtoAutoComplete(nome);
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
