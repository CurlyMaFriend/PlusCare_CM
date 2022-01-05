package pedro.gouveia.pluscare_cm;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapterHigiene extends FragmentStateAdapter {

    public FragmentAdapterHigiene(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new FragmentHigienesPorFazer();
            case 2:
                return new FragmentHigienesIncompletas();
            default:
                return new FragmentHigienesCompletas();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
