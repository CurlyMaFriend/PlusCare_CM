package pedro.gouveia.pluscare_cm;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapterTarefa extends FragmentStateAdapter {

    public FragmentAdapterTarefa(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new FragmentTarefasIncompletas();
            case 2:
                return new FragmentTarefasPorFazer();
            default:
                return new FragmentTarefasCompletas();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
