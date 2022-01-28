package pedro.gouveia.pluscare_cm.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import pedro.gouveia.pluscare_cm.MedicamentosListaActivity;
import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.utente.UtentesListaActivity;

public class FragmentAdminMain extends Fragment implements View.OnClickListener {

    private View view;
    private ImageButton listarTarefas, listarMedicamentos, listarNotificacoes, listarHigienes, listarOcorrencias, listarUtentes;
    private TextView totalTarefas, totalMedicamentos, totalOcorrencias, totalHigiene;
    private AdminStats adminStats;
    private AdminMainActivity activityInstance;

    public FragmentAdminMain(AdminStats as, AdminMainActivity activityInstance) {
        adminStats = as;
        this.activityInstance = activityInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_admin_main, container, false);
        return view;
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
//        totalMedicamentos = view.findViewById(R.id.totalMedicamentosDashboard);
//        totalHigiene = view.findViewById(R.id.totalHigieneDashboard);
        totalOcorrencias = view.findViewById(R.id.totalOcorrenciasDashboard);

        totalTarefas.setText(adminStats.getTarefas()+"");
        totalOcorrencias.setText(adminStats.getOcorrencias()+"");

        listarUtentes.setOnClickListener(this);
        listarMedicamentos.setOnClickListener(this);
        listarNotificacoes.setOnClickListener(this);
        listarOcorrencias.setOnClickListener(this);
        listarHigienes.setOnClickListener(this);
        listarTarefas.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonListarUtentesDashboard:
                activityInstance.switchActivities(UtentesListaActivity.class);
                break;
            case R.id.buttonListarMedicamentosDashboard:
                Log.d("botaoClick","clicou no botao medicine");
                activityInstance.switchActivities(MedicamentosListaActivity.class);
                break;
        }
    }
}