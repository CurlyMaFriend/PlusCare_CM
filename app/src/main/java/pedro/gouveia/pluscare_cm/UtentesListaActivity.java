package pedro.gouveia.pluscare_cm;

import android.os.Bundle;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class UtentesListaActivity extends AppCompatActivity {

    private FragmentAdapterUtente fragmentAdapter;
    private ScrollView scrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_utentes_layout);

        scrollView = findViewById(R.id.scrollView);

        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapterUtente(fm, getLifecycle());

    }
}
