package pedro.gouveia.pluscare_cm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

public class FragmentTarefasCompletas extends Fragment {

    private TextView descricao, detalhes, comentarios,dataInserida, dataInicial, dataFim;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarefas_completas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInserida = view.findViewById(R.id.dataInserida);
        dataInicial = view.findViewById(R.id.dataInicial);
        dataFim = view.findViewById(R.id.dataFinal);

        descricao = view.findViewById(R.id.descricao);
        detalhes = view.findViewById(R.id.detalhes);
        comentarios = view.findViewById(R.id.comentarios);

    }
}