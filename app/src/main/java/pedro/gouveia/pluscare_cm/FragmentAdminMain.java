package pedro.gouveia.pluscare_cm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class FragmentAdminMain extends Fragment {

    private ImageButton listarTarefas, listarMedicamentos, listarNotificacoes, listarHigienes, listarOcorrencias, listarUtentes;
    private TextView totalTarefas, totalMedicamentos, totalOcorrencias, totalHigiene;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listarTarefas = view.findViewById(R.id.buttonListarTarefasDashboard);
        listarMedicamentos = view.findViewById(R.id.buttonListarMedicamentosDashboard);
        listarHigienes = view.findViewById(R.id.buttonListarHigieneDashboard);
        listarOcorrencias = view.findViewById(R.id.buttonListarOcorrenciasDashboard);
        listarUtentes = view.findViewById(R.id.buttonListarUtentesDashboard);
        listarNotificacoes = view.findViewById(R.id.buttonListarNotificacoesDashboard);

        totalTarefas = view.findViewById(R.id.totalTarefasDashboard);
        totalMedicamentos = view.findViewById(R.id.totalMedicamentosDashboard);
        totalHigiene = view.findViewById(R.id.totalHigieneDashboard);
        totalOcorrencias = view.findViewById(R.id.totalOcorrenciasDashboard);



    }


}