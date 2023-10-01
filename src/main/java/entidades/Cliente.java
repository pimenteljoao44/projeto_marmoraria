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

    @Column(name = "cli_nome")
    private String cliNome;

    @Column(name = "cli_cred")
    private BigDecimal credito;

    @Column(name = "cli_contato")
    private String contato;

    @Column(name = "cli_endereco")
    private String endereco;

    @OneToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @OneToOne
    private PessoaFisica pessoaFisica = new PessoaFisica();

    @OneToOne
    private PessoaJuridica pessoaJuridica;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCliNome() {
        return cliNome;
    }

    public void setCliNome(String cliNome) {
        this.cliNome = cliNome;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
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
