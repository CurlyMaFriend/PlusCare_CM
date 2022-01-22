package pedro.gouveia.pluscare_cm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentTarefaCriar extends Fragment {

    private EditText titulo, descricao, date, idAndar, idFunc, idUtente, estado;
    private Button buttonCreateTarefa, buttonCancelTarefa;
    private Date date1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.nova_ocorrencia_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View viewFrag, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(viewFrag, savedInstanceState);

        titulo = viewFrag.findViewById(R.id.tituloTarefa);
        descricao = viewFrag.findViewById(R.id.detalhesTarefa);
        idAndar = viewFrag.findViewById(R.id.idAndarTarefa);
        idFunc = viewFrag.findViewById(R.id.idFuncTarefa);
        idUtente = viewFrag.findViewById(R.id.idUtente);
        estado = viewFrag.findViewById(R.id.estadoTarefa);

        date = viewFrag.findViewById(R.id.dateOcorrencia);

        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        buttonCreateTarefa = viewFrag.findViewById(R.id.buttonCriarTarefa);
        buttonCancelTarefa = viewFrag.findViewById(R.id.buttonCancelarTarefa);

        buttonCreateTarefa.setOnClickListener(view -> {
            new Tarefa (idFunc.getText().toString(), idAndar.getText().toString(), titulo.getText().toString(),descricao.getText().toString(), estado.getText().toString(), idUtente.getText().toString(), date1);
        });

        buttonCancelTarefa.setOnClickListener(view -> {

        });

    }
}