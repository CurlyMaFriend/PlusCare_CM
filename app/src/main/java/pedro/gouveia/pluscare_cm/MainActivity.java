package pedro.gouveia.pluscare_cm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pedro.gouveia.pluscare_cm.firebaseManager.AuthManager;
import pedro.gouveia.pluscare_cm.firebaseManager.FunctionsManager;

public class MainActivity extends AppCompatActivity {

    private AuthManager authManager;
    private FunctionsManager functionsManager;
    private MyViewModel viewModel;
    SharedPreferences sharedPreferences;

    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);

        authManager = new AuthManager(viewModel, sharedPreferences);
        functionsManager = new FunctionsManager(this, sharedPreferences, viewModel);

        dbHandler = new DBHandler(this, viewModel);
        /*
        dbHandler.addMedicamento(new Medicamento("teste","teste","teste"));
        dbHandler.addHigiene( new Higiene("teste","teste"));
        dbHandler.addTarefa(new Tarefa("teste","teste","teste","teste","teste","asd",new Date()));
        dbHandler.addUtilizador(new Utilizador("teste@teste","teste","teste",new Date()));
        dbHandler.addOcorrencia(new Ocorrencia("teste","teste",new Date()));
        dbHandler.addUtente(new Utente("teste","teste","teste",new Date(), "teste", "teste", "teste","teste",100,1,1, 100, 100000000));
        */
        //switchActivities();

        viewModel.getUsers().observe(this, item ->{

            if(item == null){
                Toast.makeText(this, "No users", Toast.LENGTH_LONG);
            } else {
                //switchActivities(MainActivity.class);

                Log.d("teste", "Users: " + item.toString());
            }
        });

        viewModel.getUtentes().observe(this, item ->{

            if(item == null){
                Toast.makeText(this, "No utentes", Toast.LENGTH_LONG);
            } else {
                //switchActivities(MainActivity.class);

                Log.d("teste", "Utentes: " + item.toString());
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = authManager.getUserLogin();

        if(user == null){
            switchActivities(LoginActivity.class);
           /*if(viewModel.isNetworkAvailable(this)){
                switchActivities(LoginActivity.class);
            } else {

            }*/
        } else {

            Executors.newSingleThreadExecutor().execute( () -> {
                try {
                    Thread.sleep(5000);
                    Log.d("teste", "Token do shared pref: " + sharedPreferences.getString("user_token", "none"));
                    functionsManager.getUsers();
                    functionsManager.getUtentes();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Toast.makeText(this, "Bem vindo " + user.getDisplayName(), Toast.LENGTH_LONG);
            Log.d("teste", "Sessao ja iniciada: " + user.getEmail());
        }
    }

    private void switchActivities(Class c) {
        Intent switchActivityIntent = new Intent(getBaseContext(), c);
        startActivity(switchActivityIntent);
    }

}