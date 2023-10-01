package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ordem_de_servico")
public class OrdemDeServico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "os_id")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_criacao_os")
    private Date dataCriacao;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_finalizacao_os")
    private Date dataFinalizacao;
    @Column(name = "os_status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "cli_id")
    private Cliente cliente;

    @Column(name = "os_val_total")
    private BigDecimal valorTotal;

    @ManyToMany
    @JoinTable(
            name = "ordem_servico_servico",
            joinColumns = @JoinColumn(name = "os_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<Servico> servicos;

    @OneToMany(mappedBy = "ordemDeServico")
    private List<Produto> produtos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(Date dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdemDeServico that = (OrdemDeServico) o;
        return Objects.equals(id, that.id) && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(dataFinalizacao, that.dataFinalizacao) && Objects.equals(status, that.status) && Objects.equals(cliente, that.cliente) && Objects.equals(valorTotal, that.valorTotal) && Objects.equals(servicos, that.servicos) && Objects.equals(produtos, that.produtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCriacao, dataFinalizacao, status, cliente, valorTotal, servicos, produtos);
    }

    @Override
    public String toString() {
        return "OdemDeServico{" +
                "id=" + id +
                ", dataCriacao=" + dataCriacao +
                ", dataFinalizacao=" + dataFinalizacao +
                ", status='" + status + '\'' +
                ", cliente=" + cliente +
                ", valorTotal=" + valorTotal +
                ", servicos=" + servicos +
                ", produtos=" + produtos +
                '}';
    }
}
