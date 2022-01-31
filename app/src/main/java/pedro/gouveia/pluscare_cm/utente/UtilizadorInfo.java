package pedro.gouveia.pluscare_cm.utente;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import pedro.gouveia.pluscare_cm.FragmentAdapterTarefa;
import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Tarefa;
import pedro.gouveia.pluscare_cm.classes.Utente;
import pedro.gouveia.pluscare_cm.classes.Utilizador;

public class UtilizadorInfo extends AppCompatActivity {

    private TextView andar, nomeUtilizador, idadeUtilizador, email, funcao;
    private ImageButton imageButton;
    private FragmentAdapterTarefa fragmentAdapter;
    private Utilizador utilizador;
    private Toolbar toolbarListUtilizadores;
    private LinearLayout listaTarefas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utilizador_info_layout);

        listaTarefas = findViewById(R.id.listaTarefas);

        toolbarListUtilizadores = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarListUtilizadores);

        nomeUtilizador = findViewById(R.id.nomeUtilizador);
        andar = findViewById(R.id.andarUtilizadorInfo);
        email = findViewById(R.id.emailUtilizadorInfo);
        funcao = findViewById(R.id.funcaoUtilizador);

        imageButton = findViewById(R.id.imageButton);

        utilizador = (Utilizador) getIntent().getSerializableExtra("utilizador");

        if(utilizador != null){
            nomeUtilizador.setText(utilizador.getNome());
            andar.setText(utilizador.getAndar());
        }

        imageButton.setOnClickListener(view -> {

        });



    }

    private void addCardTarefa(Tarefa tarefa){

        Log.d("teste", "Entrou on card utente");
        View view = getLayoutInflater().inflate(R.layout.task_card_2, null);

        TextView txtTituloTarefa = view.findViewById(R.id.txtTaskName);
        TextView txtTaskType = view.findViewById(R.id.txtTaskType);
        TextView txtTaskInicio = view.findViewById(R.id.txtTaskTime2);
        TextView txtTaskFim = view.findViewById(R.id.txtTaskTime3);

        txtTituloTarefa .setText(tarefa.getTitulo());
        txtTaskType.setText(tarefa.getTipo());
        txtTaskInicio.setText(tarefa.getDataInicio());
        txtTaskFim.setText(tarefa.getDataFim());

        listaTarefas.addView(view);
    }

    private void setTarefas(ArrayList<Tarefa> aTarefas){
        listaTarefas.removeAllViews();
        for (Tarefa ta : aTarefas) {
            addCardTarefa(ta);
        }
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
        Intent i = new Intent(getBaseContext(), UtilizadoresListaActivity.class);
        startActivity(i);
    }


}
