package pedro.gouveia.pluscare_cm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
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

import java.util.ArrayList;

import pedro.gouveia.pluscare_cm.FragmentAdapterUtente;
import pedro.gouveia.pluscare_cm.FragmentLoading;
import pedro.gouveia.pluscare_cm.MyViewModel;
import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Medicamento;
import pedro.gouveia.pluscare_cm.classes.Utilizador;
import pedro.gouveia.pluscare_cm.firebaseManager.FunctionsManager;
import pedro.gouveia.pluscare_cm.utente.UtilizadorInfo;

public class MedicamentosListaActivity extends AppCompatActivity {

    private FragmentAdapterUtente fragmentAdapter;
    private LinearLayout containerMedicamentos;
    private MyViewModel viewModel;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private ScrollView medicamentosScroll;
    private FrameLayout medicamentosFrame;

    private FunctionsManager functionsManager;
    private SharedPreferences sharedPreferences;

    private ArrayList<Medicamento> medicamentos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_medicamento_layout);

        //viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        medicamentos = new ArrayList<>();

        containerMedicamentos = findViewById(R.id.medicamentosLayout);
        medicamentosScroll = findViewById(R.id.medicamentosScroll);
        medicamentosFrame = findViewById(R.id.medicamentosFrame);

        //medicamentosScroll.setVisibility(View.GONE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        medicamentos.add(new Medicamento("teste","teste","teste","teste"));
        medicamentos.add(new Medicamento("teste","teste","teste","teste"));
        medicamentos.add(new Medicamento("teste","teste","teste","teste"));
        medicamentos.add(new Medicamento("teste","teste","teste","teste"));

        //functionsManager = new FunctionsManager(this, sharedPreferences, viewModel);

        //FragmentManager fm = getSupportFragmentManager();
        //fragmentAdapter = new FragmentAdapterUtilizador(fm, getLifecycle());

        /*viewModel.getMedicamentos().observe(this, item ->{

            if(item == null){
                Toast.makeText(this, "No users", Toast.LENGTH_LONG);
            } else if (item.size() > 0) {
                medicamentosFrame.setVisibility(View.GONE);
                medicamentosScroll.setVisibility(View.VISIBLE);
                medicamentos = item;
                containerMedicamentos.removeAllViews();
                for (Medicamento med : medicamentos) {
                    addCardMedicamento(med);
                }
            }
        });*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        //replaceFragment(new FragmentLoading());
        //functionsManager.getUsers();

        containerMedicamentos.removeAllViews();
        for (Medicamento med : medicamentos) {
            addCardMedicamento(med);
        }
    }

    public void replaceFragment(Fragment frag){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.utentesFrame, frag);
        ft.commit();
    }

    private void addCardMedicamento(Medicamento medicamento){

        Log.d("teste", "Entrou on card utilizador");
        View view = getLayoutInflater().inflate(R.layout.medicamento_card, null);

        TextView txtNomeMedicamento = view.findViewById(R.id.nomeMedicamentoCard);
        TextView txtDescricaoMedicamento = view.findViewById(R.id.descricaoMedicamentoCard);
        TextView txtTipo = view.findViewById(R.id.dataTipo);
        TextView txtUtentesConsomem = view.findViewById(R.id.utentesConsomem);

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