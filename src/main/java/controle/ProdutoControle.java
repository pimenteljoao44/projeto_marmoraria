/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Grupo;
import entidades.Produto;
import facade.ProdutoFacade;
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
        produtoFacade.salvar(produto);
        return "list?faces-redirect=true";
    }

    public String excluir(Produto p) {
        produtoFacade.excluir(p);
        return "list?faces-redirect=true";
    }

    public List<Produto> getListagem() {
        return produtoFacade.listar();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
