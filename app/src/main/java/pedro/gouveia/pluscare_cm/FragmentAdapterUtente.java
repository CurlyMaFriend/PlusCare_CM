package pedro.gouveia.pluscare_cm;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapterUtente extends FragmentStateAdapter {

    public FragmentAdapterUtente(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
            switch (position) {
                case 1:
                    return new FragmentUtenteDadosSaude();
                case 2:
                    return new FragmentUtenteDadosCuidados();
                default:
                    return new FragmentUtenteDados();
            }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
