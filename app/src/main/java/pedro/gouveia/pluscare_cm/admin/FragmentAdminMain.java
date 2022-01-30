package pedro.gouveia.pluscare_cm.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import pedro.gouveia.pluscare_cm.HigieneListaActivity;
import pedro.gouveia.pluscare_cm.MedicamentosListaActivity;
import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.utente.UtentesListaActivity;
import pedro.gouveia.pluscare_cm.utente.UtilizadoresListaActivity;

public class FragmentAdminMain extends Fragment implements View.OnClickListener {

    private View view;
    private Button listarTarefas, listarOcorrencias;
    private ImageButton listarMedicamentos, listarUtilizadores, listarHigienes, listarUtentes;
    private TextView totalTarefas, totalMedicamentos, totalOcorrencias, totalHigiene;
    private LinearLayout layoutListarUtilizadoresDashboard, layoutListarUtentesDashboard, layoutListarHigieneDashboard, layoutListarMedicamentosDashboard;
    private AdminStats adminStats;
    private AdminMainActivity activityInstance;

    FragmentAdminMain(AdminStats as, AdminMainActivity activityInstance) {
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
        listarUtilizadores = view.findViewById(R.id.buttonListarUtilizadoresDashboard);
        layoutListarUtilizadoresDashboard = view.findViewById(R.id.layoutListarUtilizadoresDashboard);
        layoutListarUtentesDashboard = view.findViewById(R.id.layoutListarUtentesDashboard);
        layoutListarHigieneDashboard = view.findViewById(R.id.layoutListarHigieneDashboard);
        layoutListarMedicamentosDashboard = view.findViewById(R.id.layoutListarMedicamentosDashboard);

        totalTarefas = view.findViewById(R.id.totalTarefasDashboard);
//       totalMedicamentos = view.findViewById(R.id.totalMedicamentosDashboard);
//        totalHigiene = view.findViewById(R.id.totalHigieneDashboard);
        totalOcorrencias = view.findViewById(R.id.totalOcorrenciasDashboard);

        totalTarefas.setText(adminStats.getTarefas()+"");
        totalOcorrencias.setText(adminStats.getOcorrencias()+"");

        listarUtentes.setOnClickListener(this);
        listarMedicamentos.setOnClickListener(this);
        listarUtilizadores.setOnClickListener(this);
        listarOcorrencias.setOnClickListener(this);
        listarHigienes.setOnClickListener(this);
        listarTarefas.setOnClickListener(this);
        layoutListarUtilizadoresDashboard.setOnClickListener(this);
        layoutListarUtentesDashboard.setOnClickListener(this);
        layoutListarHigieneDashboard.setOnClickListener(this);
        layoutListarMedicamentosDashboard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layoutListarUtentesDashboard:
            case R.id.buttonListarUtentesDashboard:
                activityInstance.switchActivities(UtentesListaActivity.class);
                break;
            case R.id.layoutListarMedicamentosDashboard:
            case R.id.buttonListarMedicamentosDashboard:
                Log.d("botaoClick","clicou no botao medicine");
                activityInstance.switchActivities(MedicamentosListaActivity.class);
                break;
            case R.id.buttonListarUtilizadoresDashboard:
            case R.id.layoutListarUtilizadoresDashboard:
                Log.d("botaoClick","clicou botao utilizadores");
                activityInstance.switchActivities(UtilizadoresListaActivity.class);
                break;
            case R.id.layoutListarHigieneDashboard:
            case R.id.buttonListarHigieneDashboard:
                Log.d("botaoClick","clicou botao higiene");
                activityInstance.switchActivities(HigieneListaActivity.class);
                break;
        }
    }
}