/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author ricardo
 */
public enum VendaTipo {

    ORCAMENTO("Orçamento"),
    VENDA("Venda");

    private final String descricao;

    private VendaTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
