package pedro.gouveia.pluscare_cm;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapterOcorrencias extends FragmentStateAdapter {
    public FragmentAdapterOcorrencias(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new FragmentOcorrenciasArquivadas();
            default:
                return new FragmentOcorrenciasNovas();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
