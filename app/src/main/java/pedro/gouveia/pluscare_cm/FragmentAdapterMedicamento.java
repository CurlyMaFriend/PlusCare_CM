package pedro.gouveia.pluscare_cm;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapterMedicamento extends FragmentStateAdapter {

    public FragmentAdapterMedicamento(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new FragmentMedicamentosPorFazer();
            case 2:
                return new FragmentMedicamentosIncompletos();
            default:
                return new FragmentMedicamentosCompletos();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
