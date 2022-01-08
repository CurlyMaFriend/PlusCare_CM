package pedro.gouveia.pluscare_cm;

import java.util.Date;

public class Utilizador {

    private String email, nome, morada;
    private Date dataNascimento;
    private int tipo, id;

    Utilizador(String aUsername, String aNome, String aMorada, Date aDataNascimento) {
        id = -1;
        nome = aNome;
        morada = aMorada;
        email = aUsername;
        tipo = -1;
        dataNascimento = aDataNascimento;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }
}