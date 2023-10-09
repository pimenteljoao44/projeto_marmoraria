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
    private String servicoDescricao;

    @Column(name = "valor_servico")
    private BigDecimal servicoValor;

    @Column(name = "item_quantidade", nullable = false)
    private BigDecimal servicoQuantidade = BigDecimal.ZERO;

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

    public String getServicoDescricao() {
        return servicoDescricao;
    }

    public void setServicoDescricao(String servicoDescricao) {
        this.servicoDescricao = servicoDescricao;
    }

    public BigDecimal getServicoValor() {
        return servicoValor;
    }

    public void setServicoValor(BigDecimal servicoValor) {
        this.servicoValor = servicoValor;
    }

    public BigDecimal getServicoQuantidade() {
        return servicoQuantidade;
    }

    public void setServicoQuantidade(BigDecimal servicoQuantidade) {
        this.servicoQuantidade = servicoQuantidade;
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


}
