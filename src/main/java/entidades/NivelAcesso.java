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
public enum NivelAcesso {
    ADMIN("Administrador"),
    USER("Usuário");

    private final String descricao;

    private NivelAcesso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
