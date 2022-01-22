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

public class FragmentHigieneCriar extends Fragment {

    private EditText titulo, descricao;
    private Button buttonCreateHigiene, buttonCancelHigiene;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.nova_higiene_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View viewFrag, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(viewFrag, savedInstanceState);

        titulo = viewFrag.findViewById(R.id.tituloHigiene);
        descricao = viewFrag.findViewById(R.id.detalhesHigiene);

        buttonCreateHigiene = viewFrag.findViewById(R.id.buttonCriarHigiene);
        buttonCancelHigiene = viewFrag.findViewById(R.id.buttonCancelarCriarHigiene);

        buttonCreateHigiene.setOnClickListener(view -> {
            new Higiene (titulo.getText().toString(), descricao.getText().toString());
        });

        buttonCancelHigiene.setOnClickListener(view -> {

        });

    }
}