package pedro.gouveia.pluscare_cm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import pedro.gouveia.pluscare_cm.classes.Utente;
import pedro.gouveia.pluscare_cm.firebaseManager.FunctionsManager;

public class UtentesListaActivity extends AppCompatActivity {

    private FragmentAdapterUtente fragmentAdapter;
    private LinearLayout containerUtentes;
    private MyViewModel viewModel;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private ScrollView utentesScroll;
    private FrameLayout utentesFrame;

    private FunctionsManager functionsManager;
    private SharedPreferences sharedPreferences;

    private ArrayList<Utente> utentes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_utentes_layout);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        utentes = new ArrayList<>();

        containerUtentes = findViewById(R.id.utentes_container);
        utentesScroll = findViewById(R.id.utentesScroll);
        utentesFrame = findViewById(R.id.utentesFrame);

        utentesScroll.setVisibility(View.GONE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        functionsManager = new FunctionsManager(this, sharedPreferences, viewModel);

        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapterUtente(fm, getLifecycle());

        viewModel.getUtentes().observe(this, item ->{

            if(item == null){
                Toast.makeText(this, "No users", Toast.LENGTH_LONG);
            } else if (item.size() > 0) {
                utentesFrame.setVisibility(View.GONE);
                utentesScroll.setVisibility(View.VISIBLE);
                utentes = item;
                for (Utente ut : utentes) {
                    addCardUtente(ut);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        replaceFragment(new FragmentLoading());
        functionsManager.getUtentes();

        for (Utente ut : utentes) {
            addCardUtente(ut);
        }
    }

    public void replaceFragment(Fragment frag){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.utentesFrame, frag);
        ft.commit();
    }

    private void addCardUtente(Utente utente){

        Log.d("teste", "Entrou on card utente");
        View view = getLayoutInflater().inflate(R.layout.utente_card, null);

        TextView txtNomeUtente = view.findViewById(R.id.nome_utente);
        Button btnDetailsUtente = view.findViewById(R.id.btn_details_utente);

        txtNomeUtente.setText(utente.getNome());

        btnDetailsUtente.setOnClickListener(v -> {
            Log.d("teste", utente.toString());
        });

        containerUtentes.addView(view);
    }
}
