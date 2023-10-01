package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id")
    private Integer id;

    @Column(name = "cli_cred")
    private BigDecimal credito;

    @OneToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(credito, cliente.credito) && Objects.equals(pessoa, cliente.pessoa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, credito, pessoa);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", credito=" + credito +
                ", pessoa=" + pessoa +
                '}';
    }
}
