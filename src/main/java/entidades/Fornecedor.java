package entidades;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fornecedor_id")
    private Integer id;

    @Column(name = "nome_fornecedor")
    private String nome;
    @Column(name = "contato_fornecedor")
    private String contato;

    @OneToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @OneToOne
    private PessoaFisica pessoaFisica;

    @OneToOne
    private PessoaJuridica pessoaJuridica;


    @OneToMany(mappedBy ="fornecedor" )
    private List<Produto> produtos;

    public Fornecedor() {
        pessoaFisica = new PessoaFisica();
        pessoaJuridica = new PessoaJuridica();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor that = (Fornecedor) o;
        return Objects.equals(id, that.id) && Objects.equals(contato, that.contato) && Objects.equals(pessoa, that.pessoa) && Objects.equals(produtos, that.produtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contato, pessoa, produtos);
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "id=" + id +
                ", contato='" + contato + '\'' +
                ", pessoa=" + pessoa +
                ", produtos=" + produtos +
                '}';
    }
}