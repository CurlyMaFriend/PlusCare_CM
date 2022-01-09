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

public class FragmentOcorrenciaCriar extends Fragment {

    private EditText titulo, descricao, date;
    private Button buttonCreateOcorrencia, buttonCancelOcorrencia;
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

        titulo = viewFrag.findViewById(R.id.tituloOcorrencia);
        descricao = viewFrag.findViewById(R.id.detalhesOcorrencia);
        date = viewFrag.findViewById(R.id.dateOcorrencia);

        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        buttonCreateOcorrencia = viewFrag.findViewById(R.id.buttonCriarHigiene);
        buttonCancelOcorrencia = viewFrag.findViewById(R.id.buttonCancelarCriarHigiene);

        buttonCreateOcorrencia.setOnClickListener(view -> {
            new Ocorrencia (titulo.getText().toString(), descricao.getText().toString(), date1);
        });

        buttonCancelOcorrencia.setOnClickListener(view -> {

        });

    }

}