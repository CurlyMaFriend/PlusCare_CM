package pedro.gouveia.pluscare_cm.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executors;

import pedro.gouveia.pluscare_cm.FragmentLoading;
import pedro.gouveia.pluscare_cm.MyViewModel;
import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.admin.AdminMainActivity;
import pedro.gouveia.pluscare_cm.auxiliar.AuxiliarMainActivity;
import pedro.gouveia.pluscare_cm.classes.Utilizador;
import pedro.gouveia.pluscare_cm.firebaseManager.AuthManager;
import pedro.gouveia.pluscare_cm.firebaseManager.FunctionsManager;

public class MainActivity extends AppCompatActivity {

    private AuthManager authManager;
    private FunctionsManager functionsManager;
    private MyViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private FirebaseUser user;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        authManager = new AuthManager(viewModel, sharedPreferences);
        functionsManager = new FunctionsManager(this, sharedPreferences, viewModel);

        //switchActivities();

        viewModel.getUsers().observe(this, item ->{

            if(item == null){
                Toast.makeText(this, "No users", Toast.LENGTH_LONG);
            } else {
                //switchActivities(MainActivity.class);

                Executors.newSingleThreadExecutor().execute( () -> {
                    functionsManager.addUtilizador(
                            new Utilizador(
                                    "email.test.test@gmail.com",
                                    "password",
                                    "Some name",
                                    "Morada do gajo",
                                    "24/12/1990"
                            ));
                });
                Log.d("teste", "Users: " + item.toString());
            }
        });

       /* viewModel.getUtentes().observe(this, item ->{

            if(item == null){
                Toast.makeText(this, "No utentes", Toast.LENGTH_LONG);
            } else {
                //switchActivities(MainActivity.class);

                Executors.newSingleThreadExecutor().execute( () -> {

                    functionsManager.addUtente(
                            new Utente(
                                    "Mr Toni",
                                    "Toni",
                                    "Travessa do encostado n3",
                                    "12/12/1966",
                                    "Viuvo",
                                    "Basico",
                                    "carpinteiro",
                                    "portuguesa",
                                    154,
                                    87654321,
                                    987123654,
                                    31245697801L,
                                    123645789
                            ));
                });
                Log.d("teste", "Utentes: " + item.toString());
            }
        });*/

        viewModel.getUserTokenReady().observe(this, item ->{

            String userType = sharedPreferences.getString("user_type", "-1");

            if(userType.equals("0")){
                switchActivities(AdminMainActivity.class);
            } else if(userType.equals("-1")){
                Log.d("teste", "Type -1");
            } else {
                switchActivities(AuxiliarMainActivity.class);
            }

            Log.d("teste", "Bem vindo " + user.getDisplayName().substring(0, user.getDisplayName().indexOf('|')-1));
            Log.d("teste", "Sessao ja iniciada: " + user.getEmail());

        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        replaceFragment(new FragmentLoading());

        user = authManager.getUserLogin();

        if(user == null){
            switchActivities(LoginActivity.class);
        } /*else {

            String userType = sharedPreferences.getString("user_type", "-1");

            if(userType.equals("0")){
                switchActivities(AdminMainActivity.class);
            } else if(userType.equals("-1")){
                Log.d("teste", "Type -1");
            } else {
                switchActivities(AuxiliarMainActivity.class);
            }

            Log.d("teste", "Bem vindo " + user.getDisplayName().substring(0, user.getDisplayName().indexOf('|')-1));
            Log.d("teste", "Sessao ja iniciada: " + user.getEmail());
        }*/
    }

    private void switchActivities(Class c) {
        Intent switchActivityIntent = new Intent(getBaseContext(), c);
        startActivity(switchActivityIntent);
    }

    public void replaceFragment(Fragment frag){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, frag);
        ft.commit();
    }
}