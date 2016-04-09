/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.Pessoa;
import entidades.PessoaFisica;
import entidades.PessoaJuridica;
import facade.PessoaFacade;
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
public class PessoaControle implements Serializable{
    @Inject
    private PessoaFacade pessoaFacade;
    private Pessoa pessoa;
    private String tipoPessoa;
    
    public void novo(){
        tipoPessoa="PF";
        criaPessoa();
    }
    
    public void criaPessoa(){
        if(tipoPessoa.equals("PF")){
            pessoa = new PessoaFisica();
        }else{
            pessoa = new PessoaJuridica();
        }
    }
    
    public String salvar(){
        pessoaFacade.salvar(pessoa);
        return "list?faces-redirect=true";
    }
    
    public String excluir(Pessoa p){
        pessoaFacade.excluir(p);
        return "list?faces-redirect=true";
    }
    
    public List<Pessoa> getListagem(){
        return pessoaFacade.listar();
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
