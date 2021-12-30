package pedro.gouveia.pluscare_cm;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminMainActivity extends AppCompatActivity {

    private TextView numTarefa, numMedicamento, numOcorrencia, numHigiene;
    private Button buttonTarefas, buttonMedicamentos, buttonOcorrencias, buttonHigiene;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_layout);

        numTarefa = findViewById(R.id.numTarefas);
        numMedicamento = findViewById(R.id.numMedicamentos);
        numOcorrencia = findViewById(R.id.numOcorrencias);
        numHigiene = findViewById(R.id.numHigiene);

        buttonTarefas = findViewById(R.id.buttonListTarefas);
        buttonMedicamentos = findViewById(R.id.buttonListMedicamentos);
        buttonHigiene = findViewById(R.id.buttonListTarefas);
        buttonOcorrencias = findViewById(R.id.buttonListOcorrencias);

        buttonTarefas.setOnClickListener(view -> {
            //Mostrar lista de tarefas

        });

        buttonMedicamentos.setOnClickListener(view -> {
            //Mostrar lista de Medicamentos

        });

        buttonTarefas.setOnClickListener(view -> {
            //Mostrar lista de Tarefas

        });

        buttonOcorrencias.setOnClickListener(view -> {
            //Mostrar lista de Ocorrencias

        });

    }

}
