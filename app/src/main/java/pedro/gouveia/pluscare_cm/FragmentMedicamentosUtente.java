package pedro.gouveia.pluscare_cm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import pedro.gouveia.pluscare_cm.classes.Medicamento;
import pedro.gouveia.pluscare_cm.classes.MedicamentoUtente;

public class FragmentMedicamentosUtente extends Fragment {

    private ArrayList<MedicamentoUtente> medicamentoUtentes;
    private LinearLayout containerMedicamentoUtentes;

    FragmentMedicamentosUtente(ArrayList<MedicamentoUtente> aMedicamentoUtentes){
        medicamentoUtentes = aMedicamentoUtentes;

        if(medicamentoUtentes == null){
            medicamentoUtentes = new ArrayList<>();
        }
    }

    public void setMedicamentoUtentes(ArrayList<MedicamentoUtente> medicamentoUtente) {
        this.medicamentoUtentes = medicamentoUtente;
        containerMedicamentoUtentes.removeAllViews();
        for (MedicamentoUtente medicamentoUt: medicamentoUtentes){
            addCardMedicamentoUtentes(medicamentoUt);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicamentos_utentes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        containerMedicamentoUtentes = view.findViewById(R.id.medicamentosDetalhesContainer);
        containerMedicamentoUtentes.removeAllViews();
        for (MedicamentoUtente medicamentoUtente: medicamentoUtentes){
            addCardMedicamentoUtentes(medicamentoUtente);
        }

    }

    private void addCardMedicamentoUtentes(MedicamentoUtente medicamentoUtente){

        Log.d("teste", "Entrou on card utilizador");
        View view = getLayoutInflater().inflate(R.layout.utente_medicamento_card, null);

        TextView txtNomeMedicamentoUtente = view.findViewById(R.id.nome_utente);
        TextView txtDoseMedicamento = view.findViewById(R.id.doseTomaUtente);

        Button btnMedicamento = view.findViewById(R.id.btn_details_utente_medicamento);

        txtNomeMedicamentoUtente.setText(medicamentoUtente.getMedicamento_nome());
        txtDoseMedicamento.setText(medicamentoUtente.getDose());

        /*btnMedicamento.setOnClickListener(v -> {
            Log.d("teste", medicamentoUtente.toString());
            medicamentoDetails(medicamentoUtente);
        });*/

        containerMedicamentoUtentes.addView(view);
    }

}