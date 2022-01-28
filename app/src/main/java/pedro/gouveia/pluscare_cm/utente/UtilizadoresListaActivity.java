package pedro.gouveia.pluscare_cm.utente;

import android.content.Intent;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import pedro.gouveia.pluscare_cm.FragmentAdapterUtente;
import pedro.gouveia.pluscare_cm.MyViewModel;
import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Utilizador;
import pedro.gouveia.pluscare_cm.firebaseManager.FunctionsManager;

public class UtilizadoresListaActivity extends AppCompatActivity {

    private FragmentAdapterUtente fragmentAdapter;
    private LinearLayout containerUtilizadores;
    private MyViewModel viewModel;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private ScrollView utilizadoresScroll;
    private FrameLayout utilizadoresFrame;

    private FunctionsManager functionsManager;
    private SharedPreferences sharedPreferences;

    private ArrayList<Utilizador> utilizadores;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_utilizadores_layout);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        utilizadores = new ArrayList<>();

        containerUtilizadores = findViewById(R.id.utilizadores_container);
        utilizadoresScroll = findViewById(R.id.utilizadoresScroll);
        utilizadoresFrame = findViewById(R.id.utilizadoresFrame);

        //utilizadoresScroll.setVisibility(View.GONE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        utilizadores.add(new Utilizador("teste","teste","teste","teste","teste"));
        utilizadores.add(new Utilizador("teste","teste","teste","teste","teste"));
        utilizadores.add(new Utilizador("teste","teste","teste","teste","teste"));



        //functionsManager = new FunctionsManager(this, sharedPreferences, viewModel);

        //FragmentManager fm = getSupportFragmentManager();
        //fragmentAdapter = new FragmentAdapterUtilizador(fm, getLifecycle());

        /*viewModel.getUsers().observe(this, item ->{

            if(item == null){
                Toast.makeText(this, "No users", Toast.LENGTH_LONG);
            } else if (item.size() > 0) {
                utilizadoresFrame.setVisibility(View.GONE);
                utilizadoresScroll.setVisibility(View.VISIBLE);
                utilizadores = item;
                containerUtilizadores.removeAllViews();
                for (Utilizador ut : utilizadores) {
                    addCardUtilizador(ut);
                }
            }
        });*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        //replaceFragment(new FragmentLoading());
        //functionsManager.getUsers();

        containerUtilizadores.removeAllViews();
        for (Utilizador ut : utilizadores) {
            addCardUtilizador(ut);
        }
    }

    public void replaceFragment(Fragment frag){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.utentesFrame, frag);
        ft.commit();
    }

    private void addCardUtilizador(Utilizador utilizador){

        Log.d("teste", "Entrou on card utilizador");
        View view = getLayoutInflater().inflate(R.layout.utilizador_card, null);

        TextView txtNomeUtilizador = view.findViewById(R.id.nome_utente);
        Button btnDetailsUtilizador = view.findViewById(R.id.btn_details_utilizador);

        txtNomeUtilizador.setText(utilizador.getUsername());

        btnDetailsUtilizador.setOnClickListener(v -> {
            Log.d("teste", utilizador.toString());
            userDetails(utilizador);
        });

        containerUtilizadores.addView(view);
    }

    private void userDetails(Utilizador aUtilizador){
        Log.d("teste", "chegou");
        Intent i = new Intent(getBaseContext(), UtilizadorInfo.class);
        i.putExtra("utilizador", aUtilizador);
        startActivity(i);
    }
}
