package pedro.gouveia.pluscare_cm.classes;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class Utilizador {

    private String email, nome, morada, id, password;
    private String data_nascimento;
    private int tipo;

    public Utilizador(String aEmail, String aPassword, String aNome, String aMorada, String aDataNascimento) {
        id = "0";
        nome = aNome;
        morada = aMorada;
        email = aEmail;
        tipo = 1;
        data_nascimento = aDataNascimento;
        password = aPassword;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return data_nascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.data_nascimento = dataNascimento;
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