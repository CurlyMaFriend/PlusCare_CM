package pedro.gouveia.pluscare_cm.auxiliar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.utente.UtentesListaActivity;

public class AuxiliarMainActivity extends AppCompatActivity {

    private TextView totalTarefasFunc, totalTarefasOcco, nomeFuncionario;
    private LinearLayout tarefas, utentes, perfil, notificacoes;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funcionario_main);

        nomeFuncionario = findViewById(R.id.nomeFuncionario);
        totalTarefasFunc = findViewById(R.id.totalTarefasFunc);
        totalTarefasOcco = findViewById(R.id.totalTarefasOcco);

        tarefas = findViewById(R.id.layoutTarefas);
        utentes = findViewById(R.id.layoutUtentes);
        perfil = findViewById(R.id.layoutPerfil);
        notificacoes = findViewById(R.id.layoutNotificacoes);

        tarefas.setOnClickListener(view -> {

        });

        utentes.setOnClickListener(view -> {
            Intent i = new Intent(getBaseContext(), UtentesListaActivity.class);
            i.putExtra("tipo","funcionario");
            startActivity(i);
        });

        perfil.setOnClickListener(view -> {

        });

        notificacoes.setOnClickListener(view -> {

        });

    }

    public void switchActivities(Class c) {
        Intent switchActivityIntent = new Intent(getBaseContext(), c);
        startActivity(switchActivityIntent);
    }

}
