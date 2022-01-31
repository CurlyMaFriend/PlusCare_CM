package pedro.gouveia.pluscare_cm.classes;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;

public class Utilizador implements Serializable {

    private String email, nome, morada, id, password, andar;
    private int tipo;

    public Utilizador(String aEmail, String aPassword, String aNome, String aMorada) {
        id = "0";
        nome = aNome;
        morada = aMorada;
        email = aEmail;
        tipo = 2;
        password = aPassword;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }



    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", morada='" + morada + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", andar='" + andar + '\'' +
                ", tipo=" + tipo +
                '}';
    }

}