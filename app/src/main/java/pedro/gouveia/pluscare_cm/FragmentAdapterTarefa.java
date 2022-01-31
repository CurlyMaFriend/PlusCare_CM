package pedro.gouveia.pluscare_cm;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

import pedro.gouveia.pluscare_cm.classes.Tarefa;

public class FragmentAdapterTarefa extends FragmentStateAdapter {

    private ArrayList<Tarefa> tarefas;
    private boolean loading;
    private FragmentTarefasCompletas fragmentTarefasCompletas;
    private FragmentTarefasPorFazer fragmentTarefasPorFazer;
    private FragmentTarefasIncompletas fragmentTarefasIncompletas;

    public FragmentAdapterTarefa(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);

        fragmentTarefasCompletas = new FragmentTarefasCompletas(null);
    }

    public void setTarefas(ArrayList<Tarefa> aTarefas){
        tarefas = aTarefas;
        fragmentTarefasCompletas.setTarefas(tarefas);
        fragmentTarefasIncompletas.setTarefas(tarefas);
        fragmentTarefasPorFazer.setTarefas(tarefas);
        Log.d("teste", "Entrou no set tarefas com: " + aTarefas.size());
    }

    public void isLoading(boolean load) {
        loading = load;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return fragmentTarefasCompletas;
            case 1:
                if(loading) return new FragmentLoading();
                else return fragmentTarefasPorFazer;
            default:
                if(loading) return new FragmentLoading();
                else return fragmentTarefasIncompletas;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
