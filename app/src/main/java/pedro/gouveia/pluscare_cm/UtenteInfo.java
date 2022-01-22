package pedro.gouveia.pluscare_cm;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class UtenteInfo extends AppCompatActivity {

    private TabLayout tabLayout;
    private TextView nomePrefUtente, nomeUtente, idadeUtente;
    private FragmentAdapterTarefa fragmentAdapter;
    private ViewPager2 viewPage2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utente_info_layout);

        tabLayout = findViewById(R.id.tabLayout2);
        viewPage2 = findViewById(R.id.viewPager22);

        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapterTarefa(fm, getLifecycle());
        viewPage2.setAdapter(fragmentAdapter);

        nomeUtente = findViewById(R.id.nomeUtente);
        nomePrefUtente = findViewById(R.id.nomePrefUtente);
        idadeUtente = findViewById(R.id.idadeUtente);

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.Completas)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.PorFazer)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.Incompletas)));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPage2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPage2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }


}
