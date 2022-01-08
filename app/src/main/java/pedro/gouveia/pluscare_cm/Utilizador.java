package pedro.gouveia.pluscare_cm;

public class Utilizador {

    private String email, password, role;
    private int tipo, id;

    Utilizador(int aId, String aUsername, String aPassword){
        id = aId;
        email = aUsername;
        password = aPassword;
        role = "TBD";
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
