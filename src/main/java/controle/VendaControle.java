/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.ItemVenda;
import entidades.Venda;
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
public class VendaControle implements Serializable {

    private Venda venda;
    @Inject
    private VendaFacade vendaFacade;
    private ConverterGenerico converterGenerico;
    private ItemVenda itemVenda = new ItemVenda();

    public void addItem() {
        try {
            venda.addItem(itemVenda);
            itemVenda = new ItemVenda();
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage message
                    = new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void removeItem(ItemVenda i) {
        venda.removeItem(i);
    }

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(vendaFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        venda = new Venda();
        itemVenda = new ItemVenda();
    }

    public String salvar() {
        try {
            vendaFacade.salvar(venda);
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String excluir(Venda v) {
        vendaFacade.excluir(v);
        return "list?faces-redirect=true";
    }

    public List<Venda> getListagem() {
        return vendaFacade.listar();
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }

}
