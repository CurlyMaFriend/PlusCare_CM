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

public class FragmentMedicamentoCriar extends Fragment {

    private EditText nomeMedicamentoNovo, detalhesMedicamento, tipoMedicamento;
    private Button buttonCreateMedicamento, buttonCancelCreateMedicamento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.novo_medicamento_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View viewFrag, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(viewFrag, savedInstanceState);

        nomeMedicamentoNovo = viewFrag.findViewById(R.id.nomeMedicamentoNovo);
        detalhesMedicamento = viewFrag.findViewById(R.id.detalhesMedicamento);
        tipoMedicamento = viewFrag.findViewById(R.id.tipoMedicamento);

        buttonCreateMedicamento = viewFrag.findViewById(R.id.buttonCriarMedicamento);
        buttonCancelCreateMedicamento = viewFrag.findViewById(R.id.buttonCancelarCriarMedicamento);

        buttonCreateMedicamento.setOnClickListener(view -> {
            new Medicamento (nomeMedicamentoNovo.getText().toString(), detalhesMedicamento.getText().toString(), tipoMedicamento.getText().toString());
        });

        buttonCancelCreateMedicamento.setOnClickListener(view -> {

        });

    }
}