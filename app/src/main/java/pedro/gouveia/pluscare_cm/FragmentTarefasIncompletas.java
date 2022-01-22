package pedro.gouveia.pluscare_cm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTarefasIncompletas extends Fragment {

    private TextView descricaoIncompleta, detalhesIncompleta, dataInseridoIncompleta, dataInicialIncompleta, dataFinalIncompleta, causas, justificacoes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarefas_incompletas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        descricaoIncompleta = view.findViewById(R.id.descricaoIncompleta);
        detalhesIncompleta = view.findViewById(R.id.detalhesIncompleta);
        dataInseridoIncompleta = view.findViewById(R.id.dataInseridoIncompleta);
        dataInicialIncompleta = view.findViewById(R.id.dataInicialIncompleta);
        dataFinalIncompleta = view.findViewById(R.id.dataFinalIncompleta);
        causas = view.findViewById(R.id.causas);
        justificacoes = view.findViewById(R.id.justificacoes);


    }
}