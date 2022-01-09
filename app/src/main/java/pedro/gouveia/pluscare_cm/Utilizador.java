package pedro.gouveia.pluscare_cm;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class Utilizador {

    private String email, nome, morada, id;
    private Date data_nascimento;
    private int tipo;

    Utilizador(String aEmail, String aNome, String aMorada, Date aDataNascimento) {
        id = "0";
        nome = aNome;
        morada = aMorada;
        email = aEmail;
        tipo = -1;
        data_nascimento = aDataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", morada='" + morada + '\'' +
                ", data_nascimento=" + data_nascimento +
                ", tipo=" + tipo +
                ", id=" + id +
                '}';
    }
}