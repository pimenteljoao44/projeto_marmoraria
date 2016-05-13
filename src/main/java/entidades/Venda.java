/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ricardo
 */
@Entity
@Table(name = "venda")
public class Venda implements Serializable, MovimentaEstoque, Validador {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ven_id")
    private Long id;

    @Column(name = "ven_data", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date data = new Date();

    @Column(name = "total", nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "desconto", nullable = false)
    private BigDecimal desconto = BigDecimal.ZERO;

    @Column(name = "ven_tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private VendaTipo vendaTipo = VendaTipo.VENDA;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "venda",
            orphanRemoval = true)
    private List<ItemVenda> itensVenda = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Pessoa cliente;

    public void addItem(ItemVenda item) throws Exception {
        item.setVenda(this);
        if (!itensVenda.contains(item)) {
            item.setPreco(item.getProduto().getPreco());
            itensVenda.add(item);
            calculaTotal();
        } else {
            throw new Exception("O produto "
                    + item.getProduto().getNome()
                    + " já está adicionado na venda");
        }
    }

    @Override
    public void movimenta() throws Exception {
        if (vendaTipo.equals(VendaTipo.VENDA)) {
            for (ItemVenda item : itensVenda) {
                item.getProduto().baixarEstoque(item.getQuantidade());
            }
        }
    }

    @Override
    public void validar() throws Exception {
        if (itensVenda.isEmpty()) {
            throw new Exception("Não é possivel salvar uma venda sem itens");
        }
    }

    public void removeItem(ItemVenda item) {
        itensVenda.remove(item);
        calculaTotal();
        item.getProduto().estornarEstoque(item.getQuantidade());
    }

    public void calculaTotal() {
        if (desconto == null || desconto.compareTo(total) >= 0) {
            desconto = BigDecimal.ZERO;
        }
        total = BigDecimal.ZERO;
        for (ItemVenda iv : itensVenda) {
            total = total.add(iv.getPreco().multiply(iv.getQuantidade()));
        }
        total = total.subtract(desconto);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public VendaTipo getVendaTipo() {
        return vendaTipo;
    }

    public void setVendaTipo(VendaTipo vendaTipo) {
        this.vendaTipo = vendaTipo;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

}
