package pedro.gouveia.pluscare_cm.utente;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pedro.gouveia.pluscare_cm.FragmentLoading;
import pedro.gouveia.pluscare_cm.admin.AdminMainActivity;
import pedro.gouveia.pluscare_cm.viewModels.MyViewModel;
import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Utilizador;
import pedro.gouveia.pluscare_cm.firebaseManager.FunctionsManager;

public class UtilizadoresListaActivity extends AppCompatActivity {

    private LinearLayout containerUtilizadores;
    private MyViewModel viewModel;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private ScrollView utilizadoresScroll;
    private FrameLayout utilizadoresFrame;

    private FunctionsManager functionsManager;
    private SharedPreferences sharedPreferences;

    private ArrayList<Utilizador> utilizadores;

    private FloatingActionButton fab;
    private Toolbar toolbarListUtilizadores;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_utilizadores_layout);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        toolbarListUtilizadores = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarListUtilizadores);

        utilizadores = new ArrayList<>();

        containerUtilizadores = findViewById(R.id.utilizadores_container);
        utilizadoresScroll = findViewById(R.id.utilizadoresScroll);
        utilizadoresFrame = findViewById(R.id.utilizadoresFrame);

        utilizadoresScroll.setVisibility(View.GONE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        functionsManager = new FunctionsManager(this, sharedPreferences, viewModel);

        FragmentManager fm = getSupportFragmentManager();

        fab = findViewById(R.id.floatingActionButton);

        fab.setVisibility(View.VISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("teste","clicou no botao fab");
                utilizadoresFrame.setVisibility(View.VISIBLE);
                utilizadoresScroll.setVisibility(View.GONE);
                fab.setVisibility(View.GONE);
                replaceFragment(new FragmentUtilizadorCriar(functionsManager));
            }

        });

        viewModel.getUsers().observe(this, item ->{

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
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        replaceFragment(new FragmentLoading());
        functionsManager.getUsers();
    }

    public void replaceFragment(Fragment frag){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.utilizadoresFrame, frag);
        ft.commit();
    }

    private void addCardUtilizador(Utilizador utilizador){

        Log.d("teste", "Entrou on card utilizador teste");
        View view = getLayoutInflater().inflate(R.layout.utilizador_card, null);

        TextView txtNomeUtilizador = view.findViewById(R.id.nomeUtilizador);
        TextView txtFuncaoUtilizador = view.findViewById(R.id.funcaoUtilizador);
        Button btnDetailsUtilizador = view.findViewById(R.id.btn_details_utilizador);

        Log.d("teste", "Utilizador nome: " + utilizador.getNome());
        txtNomeUtilizador.setText(utilizador.getNome());
        switch(utilizador.getTipo()){
            case 0:
                txtFuncaoUtilizador.setText(getString(R.string.Admin));
                break;
            case 1:
                txtFuncaoUtilizador.setText(getString(R.string.Enfermeira));
                break;
            case 2:
                txtFuncaoUtilizador.setText(R.string.Auxiliar);
                break;
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_menu, menu);

        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.goBack){
            Log.d("teste", "Goback pressed");
            goBack();
        }
        return true;
    }

    private void goBack(){
        Intent i = new Intent(getBaseContext(), AdminMainActivity.class);
        startActivity(i);
    }
}
