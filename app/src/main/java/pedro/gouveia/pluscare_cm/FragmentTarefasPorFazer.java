package pedro.gouveia.pluscare_cm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTarefasPorFazer extends Fragment {

    private TextView descricaoPorFazer, detalhesPorFazer, dataInseridaPorFazer, dataInicialPorFazer, dataFimPorFazer, tempoRestantePorFazer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarefas_por_fazer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        descricaoPorFazer = view.findViewById(R.id.descricaoPorFazer);
        detalhesPorFazer = view.findViewById(R.id.detalhesPorFazer);
        dataInseridaPorFazer = view.findViewById(R.id.dataInseridaPorFazer);
        dataInicialPorFazer = view.findViewById(R.id.dataInicialPorFazer);
        dataFimPorFazer = view.findViewById(R.id.dataInicialPorFazer);
        tempoRestantePorFazer = view.findViewById(R.id.tempoRestantePorFazer);

    }
}