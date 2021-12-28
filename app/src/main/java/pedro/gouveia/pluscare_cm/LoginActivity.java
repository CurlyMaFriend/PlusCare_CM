package pedro.gouveia.pluscare_cm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText inputUsername, inputPassword;
    private TextView createAccount;
    private String username, password;
    private Button login;

    private ArrayList<Utilizador> users = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);

        // Get input text
    inputUsername = findViewById(R.id.usernameText);
    inputPassword = findViewById(R.id.passwordText);
    createAccount = findViewById(R.id.createAccount);
    login = findViewById(R.id.buttonLogin);

        login.setOnClickListener(view -> {
            username = inputUsername.getText().toString();
            password = inputPassword.getText().toString();
            //Efetuar o login
            for(Utilizador s: users){
                if(s.getUsername().equals(username)){
                    if(s.getPassword().equals(password)){
                        Toast.makeText(this,getString(R.string.loginSuccessfully),Toast.LENGTH_SHORT);
                        switchActivities(AuxiliarMainActivity.class);
                    }
                }
            }
            Toast.makeText(this,getString(R.string.errorOnLogin),Toast.LENGTH_SHORT);
        });

        createAccount.setOnClickListener(view -> {
            Log.d("teste","entrou");
            switchActivities(NovaContaActivity.class);
        });

    }

    private void switchActivities(Class i) {
        Intent switchActivityIntent = new Intent(this, i);
        startActivity(switchActivityIntent);
    }

}
