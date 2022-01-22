package pedro.gouveia.pluscare_cm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class MedicamentosInfoActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private FragmentAdapterMedicamento fragmentAdapter;
    private ViewPager2 viewPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicamentos_info_layout);

        tabLayout = findViewById(R.id.tabLayout4);
        viewPage = findViewById(R.id.viewPager24);

        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapterMedicamento(fm, getLifecycle());
        viewPage.setAdapter(fragmentAdapter);

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.Completas)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.PorFazer)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.Incompletas)));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }

}
