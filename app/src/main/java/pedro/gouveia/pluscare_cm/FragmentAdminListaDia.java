package pedro.gouveia.pluscare_cm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentAdminListaDia extends Fragment {

    private View view;
    private MyViewModel viewModel;
    private TextView numTarefas, totalTarefas, porFazerTarefas,
            numMedicamentos, totalMedicamentos, porFazerMedicamentos,
            numHigiene, totalHigiene, porFazerHigiene,
            numOcorrencias, totalOcorrencias, porFazerOcorrencias;
    private Button listarTarefas, listarMedicamentos, listarHigiene, listarOcorrencias;

    public FragmentAdminListaDia() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_lista, container, false);
        setHasOptionsMenu(true);
        viewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        return view;
    }

    @Nullable
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        numTarefas = view.findViewById(R.id.numTarefas);
        numMedicamentos = view.findViewById(R.id.numMedicamentos);
        numHigiene = view.findViewById(R.id.numHigiene);
        numOcorrencias = view.findViewById(R.id.numOcorrencias);

        totalTarefas = view.findViewById(R.id.totalTarefas);
        totalMedicamentos = view.findViewById(R.id.totalMedicamentos);
        totalHigiene = view.findViewById(R.id.totalHigiene);
        totalOcorrencias = view.findViewById(R.id.totalOcorrencias);

        porFazerTarefas = view.findViewById(R.id.porFazerTarefas);
        porFazerMedicamentos = view.findViewById(R.id.porFazerMedicamentos);
        porFazerHigiene = view.findViewById(R.id.porFazerHigiene);
        porFazerOcorrencias = view.findViewById(R.id.porFazerOcorrencias);

        listarTarefas = view.findViewById(R.id.buttonListTarefas);
        listarMedicamentos = view.findViewById(R.id.buttonListMedicamentos);
        listarHigiene = view.findViewById(R.id.buttonListHigiene);
        listarOcorrencias = view.findViewById(R.id.buttonListOcorrencias);

    }

}