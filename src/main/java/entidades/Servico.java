package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "servico")
public class Servico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servico_id")
    private Integer id;

    @Column(name = "descricao_servico")
    private String descricao;

    @Column(name = "valor_servico")
    private BigDecimal valor;

    @Column(name = "item_quantidade", nullable = false)
    private BigDecimal quantidade = BigDecimal.ZERO;

    @ManyToMany(mappedBy = "servicos")
    private List<OrdemDeServico> ordemsDeServicos;
    @ManyToOne()
    private Funcionario funcionario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<OrdemDeServico> getOrdemsDeServicos() {
        return ordemsDeServicos;
    }

    public void setOrdemsDeServicos(List<OrdemDeServico> ordemsDeServicos) {
        this.ordemsDeServicos = ordemsDeServicos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return Objects.equals(id, servico.id) && Objects.equals(descricao, servico.descricao) && Objects.equals(valor, servico.valor) && Objects.equals(ordemsDeServicos, servico.ordemsDeServicos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, valor, ordemsDeServicos);
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", odemsDeServicos=" + ordemsDeServicos +
                '}';
    }
}
