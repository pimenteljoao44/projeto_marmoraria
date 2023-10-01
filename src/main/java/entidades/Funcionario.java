package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Integer id;

    @Column(name = "func_cargo")
    private String cargo;

    @Column(name = "func_salario")
    private BigDecimal salario;

    @Column(name = "func_ctps")
    private String ctps;

    @OneToMany(mappedBy = "funcionario")
    private List<Servico> servicos;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
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
        Funcionario that = (Funcionario) o;
        return Objects.equals(id, that.id) && Objects.equals(cargo, that.cargo) && Objects.equals(salario, that.salario) && Objects.equals(ctps, that.ctps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cargo, salario, ctps);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", ctps='" + ctps + '\'' +
                '}';
    }
}
