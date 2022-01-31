package pedro.gouveia.pluscare_cm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pedro.gouveia.pluscare_cm.dialogs.DialogCreateMedicamento;
import pedro.gouveia.pluscare_cm.viewModels.MyViewModel;
import pedro.gouveia.pluscare_cm.classes.Medicamento;
import pedro.gouveia.pluscare_cm.firebaseManager.FunctionsManager;

public class MedicamentosListaActivity extends AppCompatActivity {

    private FragmentAdapterMedicamento fragmentAdapter;
    private LinearLayout containerMedicamentos;
    private MyViewModel viewModel;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private ScrollView medicamentosScroll;
    private FrameLayout medicamentosFrame;

    private FunctionsManager functionsManager;
    private SharedPreferences sharedPreferences;

    private ArrayList<Medicamento> medicamentos;

    private FloatingActionButton fab;

    private DialogCreateMedicamento dCM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_medicamento_layout);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        medicamentos = new ArrayList<>();

        containerMedicamentos = findViewById(R.id.medicamentosLayout);
        medicamentosScroll = findViewById(R.id.medicamentosScroll);
        medicamentosFrame = findViewById(R.id.medicamentosFrame);

        medicamentosScroll.setVisibility(View.GONE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        functionsManager = new FunctionsManager(this, sharedPreferences, viewModel);

        dCM = new DialogCreateMedicamento(this, functionsManager);

        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapterMedicamento(fm, getLifecycle());

        fab = findViewById(R.id.floatingActionButton4);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("teste","clicou no botao fab");
                dCM.show();

                Window window = dCM.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            }

        });

        viewModel.getMedicamentos().observe(this, item ->{

            if(item == null){
                Toast.makeText(this, "No medicamentos", Toast.LENGTH_LONG);
            } else if (item.size() > 0) {
                medicamentosFrame.setVisibility(View.GONE);
                medicamentosScroll.setVisibility(View.VISIBLE);
                medicamentos = item;
                containerMedicamentos.removeAllViews();
                for (Medicamento med : medicamentos) {
                    addCardMedicamento(med);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        replaceFragment(new FragmentLoading());
        functionsManager.getMedicamentos();

    }

    public void replaceFragment(Fragment frag){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.medicamentosFrame, frag);
        ft.commit();
    }

    private void addCardMedicamento(Medicamento medicamento){

        Log.d("teste", "Entrou on card utilizador");
        View view = getLayoutInflater().inflate(R.layout.medicamento_card, null);

        TextView txtNomeMedicamento = view.findViewById(R.id.nomeMedicamentoCard);
        TextView txtDescricaoMedicamento = view.findViewById(R.id.descricaoMedicamentoCard);
        TextView txtTipo = view.findViewById(R.id.dataTipo);

        Button btnMedicamento = view.findViewById(R.id.btn_medicamento);

        txtNomeMedicamento.setText(medicamento.getNome());
        txtDescricaoMedicamento.setText(medicamento.getDescricao());
        txtTipo.setText(medicamento.getTipo());


        btnMedicamento.setOnClickListener(v -> {
            Log.d("teste", medicamento.toString());
            medicamentoDetails(medicamento);
        });

        containerMedicamentos.addView(view);
    }



    private void medicamentoDetails(Medicamento aMedicamento){
        Log.d("teste", "chegou");
        Intent i = new Intent(getBaseContext(), MedicamentosInfoActivity.class);
        i.putExtra("medicamento", aMedicamento);
        startActivity(i);
    }
}
