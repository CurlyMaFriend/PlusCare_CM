package pedro.gouveia.pluscare_cm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.Executors;

import pedro.gouveia.pluscare_cm.firebaseManager.FunctionsManager;

public class AdminMainActivity extends AppCompatActivity {

    private TextView numTarefa, numMedicamento, numOcorrencia, numHigiene;
    private Button buttonTarefas, buttonMedicamentos, buttonOcorrencias, buttonHigiene;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private MyViewModel viewModel;
    private FunctionsManager functionsManager;
    private SharedPreferences sharedPreferences;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main_activity);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        functionsManager = new FunctionsManager(this, sharedPreferences, viewModel);

        viewModel.getAdminStats().observe(this, item ->{

            if(item == null){
                Log.d("teste", "ADmin stats, Something went wrong");
            } else {
                replaceFragment(new FragmentAdminMain(item, this));
            }
        });

        replaceFragment(new FragmentLoading());

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(functionsManager.getAdminStats()){
            Log.d("teste", "admin stats true");
        } else {

            Log.d("teste", "admin stats false");
        }
    }

    public void replaceFragment(Fragment frag){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, frag);
        ft.commit();
    }

    public void switchActivities(Class c) {
        Intent switchActivityIntent = new Intent(getBaseContext(), c);
        startActivity(switchActivityIntent);
    }

}
