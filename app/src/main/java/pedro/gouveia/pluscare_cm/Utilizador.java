package pedro.gouveia.pluscare_cm;

public class Utilizador {

    private String email, password;
    private int tipo;

    Utilizador(String aUsername, String aPassword){
        email = aUsername;
        password = aPassword;
        tipo = -1;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
