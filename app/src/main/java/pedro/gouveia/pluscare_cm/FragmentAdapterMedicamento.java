package pedro.gouveia.pluscare_cm;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

import pedro.gouveia.pluscare_cm.classes.Medicamento;
import pedro.gouveia.pluscare_cm.classes.MedicamentoUtente;
import pedro.gouveia.pluscare_cm.classes.Tarefa;

public class FragmentAdapterMedicamento extends FragmentStateAdapter {

    private ArrayList<MedicamentoUtente> medicamentos;
    private boolean loading;
    private FragmentMedicamentosUtente fragmentMedicamentosUtente;

    public FragmentAdapterMedicamento(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);

        fragmentMedicamentosUtente = new FragmentMedicamentosUtente(null);

    }

    public void setMedicamentoUtentes(ArrayList<MedicamentoUtente> aMedicamentoUtentes){
        medicamentos = aMedicamentoUtentes;
        fragmentMedicamentosUtente.setMedicamentoUtentes(medicamentos);

        Log.d("teste", "Entrou no set Medicamentos Utentes com: " + aMedicamentoUtentes.size());
    }

    public void isLoading(boolean load) { loading = load; }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return fragmentMedicamentosUtente;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
