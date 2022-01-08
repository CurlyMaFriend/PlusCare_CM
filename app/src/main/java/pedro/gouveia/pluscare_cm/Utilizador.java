package pedro.gouveia.pluscare_cm;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class Utilizador {

    private String email, nome, morada, id;
    private String data_nascimento;
    private int tipo;

    Utilizador(String aUsername, String aNome, String aMorada, String aDataNascimento) {
        id = "0";
        nome = aNome;
        morada = aMorada;
        email = aUsername;
        tipo = -1;
        data_nascimento = aDataNascimento;
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