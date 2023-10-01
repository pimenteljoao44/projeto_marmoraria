package controle;

import converter.ConverterGenerico;
import entidades.*;
import facade.ClienteFacade;
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
public class ClienteControle implements Serializable {

    private Cliente cliente;
    @Inject
    private ClienteFacade clienteFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(clienteFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        cliente = new Cliente();
    }

    public void criarCliente(){
        cliente = new Cliente();
    }

    public String salvar() {
        try {
            clienteFacade.salvar(cliente);
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public List<Cliente> clienteAutoComplete(String nome) {
        return clienteFacade.ClienteAutoComplete(nome);
    }

    public String excluir(Cliente c) {
        clienteFacade.excluir(c);
        return "list?faces-redirect=true";
    }

    public List<Cliente> getListagem() {
        return clienteFacade.listar();
    }

    public NivelAcesso[] getNiveisAcesso(){
        return NivelAcesso.values();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteFacade getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(ClienteFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    public ConverterGenerico getConverterGenerico() {
        return converterGenerico;
    }

    public void setConverterGenerico(ConverterGenerico converterGenerico) {
        this.converterGenerico = converterGenerico;
    }
}