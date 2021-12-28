package pedro.gouveia.pluscare_cm;

public class Utilizador {

    private String username, password;
    private int tipo;

    Utilizador(String aUsername, String aPassword){
        username = aUsername;
        password = aPassword;
        tipo = -1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
