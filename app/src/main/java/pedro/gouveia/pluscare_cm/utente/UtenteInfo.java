package pedro.gouveia.pluscare_cm.utente;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import pedro.gouveia.pluscare_cm.FragmentAdapterTarefa;
import pedro.gouveia.pluscare_cm.viewModels.MyViewModel;
import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Tarefa;
import pedro.gouveia.pluscare_cm.classes.Utente;
import pedro.gouveia.pluscare_cm.firebaseManager.FunctionsManager;

public class UtenteInfo extends AppCompatActivity {

    private TabLayout tabLayout;
    private TextView nomePrefUtente, nomeUtente, idadeUtente;
    private FragmentAdapterTarefa fragmentAdapter;
    private ViewPager2 viewPage2;

    private MyViewModel viewModel;
    private FunctionsManager functionsManager;

    private Utente utente;
    private ArrayList<Tarefa> tarefasCompletas, tarefasPorFazer, tarefasFeitas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utente_info_layout);

        tarefasCompletas = new ArrayList<>();
        tarefasPorFazer = new ArrayList<>();
        tarefasFeitas = new ArrayList<>();

        tabLayout = findViewById(R.id.tabLayout2);
        viewPage2 = findViewById(R.id.viewPager2);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        functionsManager = new FunctionsManager(this, PreferenceManager.getDefaultSharedPreferences(getApplicationContext()), viewModel);

        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapterTarefa(fm, getLifecycle());
        viewPage2.setAdapter(fragmentAdapter);
        setLoading();

        nomeUtente = findViewById(R.id.nomeUtente);
        nomePrefUtente = findViewById(R.id.nomePrefUtente);
        idadeUtente = findViewById(R.id.idadeUtente);

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.Completas)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.PorFazer)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.Incompletas)));

        utente = (Utente) getIntent().getSerializableExtra("utente");

        setLoading();

        viewModel.getTarefasUtenteComp().observe(this, tarefasArray ->{
            Log.d("teste", "Entrou no observer tarefas utente completas com: " + tarefasArray.size());

            tarefasCompletas = tarefasArray;

            fragmentAdapter.isLoading(false);
            fragmentAdapter.setTarefas(tarefasCompletas);
            viewPage2.setCurrentItem(1);
            viewPage2.setCurrentItem(0);
        });

        if(utente != null){
            functionsManager.getTarefasUtente(utente.getId(), "completa");
            Log.d("teste", "Depois do functionsManager get tarefas utente");
            nomeUtente.setText(utente.getNome());
            nomePrefUtente.setText(utente.getNomePreferencia());

            String idade = "Nao determinado";

            try {
                Date d = new SimpleDateFormat("dd/MM/yyyy").parse(utente.getDataNascimento());
                Date today = Calendar.getInstance().getTime();
                long diffInMillies = Math.abs(today.getTime() - d.getTime());

                long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                idade = days/365 + "";

            } catch (ParseException e) {
                e.printStackTrace();
            }

            idadeUtente.setText(idade);
        }

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

    @Override
    protected void onStart() {
        super.onStart();


    }

    private void setLoading(){
        fragmentAdapter.isLoading(true);
    }


}
