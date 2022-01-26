package pedro.gouveia.pluscare_cm.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import pedro.gouveia.pluscare_cm.MyViewModel;
import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Utilizador;
import pedro.gouveia.pluscare_cm.firebaseManager.AuthManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private MyViewModel viewModel;
    private AuthManager authManager;
    private EditText inputUsername, inputPassword;
    private TextView createAccount;
    private String username, password;
    private Button login;
    private ConstraintLayout loadingScreen, loginScreen;

    private ArrayList<Utilizador> users = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        authManager = new AuthManager(viewModel, this.getPreferences(Context.MODE_PRIVATE));

        // Get input text
        inputUsername = findViewById(R.id.usernameText);
        inputPassword = findViewById(R.id.passwordText);
        createAccount = findViewById(R.id.createAccount);
        login = findViewById(R.id.buttonLogin);

        loadingScreen = findViewById(R.id.loadingScreen);
        loginScreen = findViewById(R.id.loginScreen);

        login.setOnClickListener(this);
        createAccount.setOnClickListener(this);

        viewModel.getCurrentUser().observe(this, item ->{

            if(item == null){
                loadingScreen.setVisibility(View.GONE);
                loginScreen.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Credenciais incorretas", Toast.LENGTH_LONG);
            } else {
                switchActivities(MainActivity.class);
                Toast.makeText(this, "Login efetuado com sucesso", Toast.LENGTH_LONG);
            }
        });

    }

    private void switchActivities(Class i) {
        Intent switchActivityIntent = new Intent(this, i);
        startActivity(switchActivityIntent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonLogin:{
                username = inputUsername.getText().toString();
                password = inputPassword.getText().toString();
                //Efetuar o login
                authManager.signInUser(username, password);
                loadingScreen.setVisibility(View.VISIBLE);
                loginScreen.setVisibility(View.INVISIBLE);
                break;
            }
        }
    }
}
