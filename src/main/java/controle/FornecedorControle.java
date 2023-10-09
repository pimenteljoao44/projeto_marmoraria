package controle;

import converter.ConverterGenerico;
import entidades.*;
import facade.FornecedorFacade;
import facade.PessoaFacade;
import facade.ProdutoFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
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
    @Inject
    private ProdutoFacade produtoFacade;
    private Fornecedor fornecedor = new Fornecedor();
    private String tipoPessoa;

    private Produto produtoSelecionado;

    private ConverterGenerico converterGenerico;

    @PostConstruct
    public void init() {
        produtoSelecionado = new Produto();
        tipoPessoa = "PF";
        criaFornecedor();
    }

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
            produtoSelecionado = new Produto();
            fornecedor.setPessoa(new PessoaFisica());
            fornecedor.setPessoaFisica(new PessoaFisica());
        } else if (tipoPessoa.equals("PJ")) {
            fornecedor = new Fornecedor();
            produtoSelecionado = new Produto();
            fornecedor.setPessoa(new PessoaJuridica());
            fornecedor.setPessoaJuridica(new PessoaJuridica());

        }
    }

    public void carregaFornecedor(Fornecedor f) {
        if (f.getPessoa() instanceof PessoaFisica) {
            tipoPessoa = "PF";
        } else {
            tipoPessoa = "PJ";
        }
        fornecedor = f;
    }

    public void adicionarProduto() {
        BigDecimal quantidade = produtoSelecionado.getQuantidade();
        if (produtoSelecionado != null && quantidade.compareTo(BigDecimal.ZERO) > 0) {

            if (fornecedor.getProdutos() == null) {
                fornecedor.setProdutos(new ArrayList<Produto>());
            }

            boolean produtoExistente = false;
            for (Produto produto : fornecedor.getProdutos()) {
                if (produto.getId().equals(produtoSelecionado.getId())) {
                    produtoExistente = true;
                    break;
                }
            }

            if (!produtoExistente) {
                if (produtoSelecionado == null) {
                    produtoSelecionado = new Produto();
                }

                produtoSelecionado.setId(produtoSelecionado.getId());
                produtoSelecionado.setNome(produtoSelecionado.getNome());
                produtoSelecionado.setPreco(produtoSelecionado.getPreco());
                produtoSelecionado.setQuantidade(quantidade);

                fornecedor.getProdutos().add(produtoSelecionado);
            }
        }
        quantidade = BigDecimal.ZERO;
    }

    public void removerProduto(Produto produto) {
        if (produto != null && fornecedor.getProdutos() != null) {
            Iterator<Produto> iterator = fornecedor.getProdutos().iterator();
            while (iterator.hasNext()) {
                Produto produtoLista = iterator.next();
                if (produtoLista.getId().equals(produto.getId())) {
                    iterator.remove();
                    break;
                }
            }
        }
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

    public List<Produto> produtoAutoComplete(String nome) {
        return produtoFacade.produtoAutoComplete(nome);
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

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }
}