package pedro.gouveia.pluscare_cm;

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

import pedro.gouveia.pluscare_cm.classes.Higiene;
import pedro.gouveia.pluscare_cm.dialogs.DialogCreateHigiene;
import pedro.gouveia.pluscare_cm.dialogs.DialogCreateMedicamento;
import pedro.gouveia.pluscare_cm.dialogs.DialogDeleteHigiene;
import pedro.gouveia.pluscare_cm.dialogs.DialogEditHigiene;
import pedro.gouveia.pluscare_cm.firebaseManager.FunctionsManager;
import pedro.gouveia.pluscare_cm.viewModels.MyViewModel;

public class HigieneListaActivity extends AppCompatActivity {
    
    private LinearLayout containerHigiene;
    private MyViewModel viewModel;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private ScrollView higieneScroll;
    private FrameLayout higieneFrame;

    private FunctionsManager functionsManager;
    private SharedPreferences sharedPreferences;

    private ArrayList<Higiene> higienes;

    private DialogCreateHigiene dCH;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_higiene_layout);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        higienes = new ArrayList<>();

        containerHigiene = findViewById(R.id.higieneLayout);
        higieneScroll = findViewById(R.id.higieneScroll);
        higieneFrame = findViewById(R.id.higieneFrame);

        higieneScroll.setVisibility(View.GONE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        functionsManager = new FunctionsManager(this, sharedPreferences, viewModel);

        dCH = new DialogCreateHigiene(this, functionsManager);

        FragmentManager fm = getSupportFragmentManager();

        fab = findViewById(R.id.floatingActionButton2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("teste","clicou no botao fab");
                dCH.show();

                Window window = dCH.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            }

        });

        viewModel.getHigiene().observe(this, item ->{

            if(item == null){
                higieneFrame.setVisibility(View.GONE);
                higieneScroll.setVisibility(View.VISIBLE);
                Toast.makeText(this, "No higiene", Toast.LENGTH_LONG);
            } else if (item.size() > 0) {
                higieneFrame.setVisibility(View.GONE);
                higieneScroll.setVisibility(View.VISIBLE);
                higienes = item;
                containerHigiene.removeAllViews();
                for (Higiene heg : higienes) {
                    addCardHigiene(heg);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        replaceFragment(new FragmentLoading());
        functionsManager.getHigiene();
    }

    public void replaceFragment(Fragment frag){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.higieneFrame, frag);
        ft.commit();
    }

    private void addCardHigiene(Higiene aHigiene){

        Log.d("teste", "Entrou on card utilizador");
        View view = getLayoutInflater().inflate(R.layout.higiene_card, null);

        TextView txtNomeHigiene = view.findViewById(R.id.nomeHigieneCard);
        TextView txtDescricaoHigiene = view.findViewById(R.id.descricaoHigieneCard);


        Button editarHigiene = view.findViewById(R.id.btn_higiene_edit);
        Button deleteHigiene = view.findViewById(R.id.btn_higiene_delete);

        deleteHigiene.setOnClickListener(view1 -> {
            DialogDeleteHigiene cdd=new DialogDeleteHigiene(this, aHigiene);
            cdd.show();

            Window window = cdd.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        });

        editarHigiene.setOnClickListener(view1 -> {
            DialogEditHigiene cdd=new DialogEditHigiene(this, aHigiene);
            cdd.show();

            Window window = cdd.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        });



        Button btnHigiene = view.findViewById(R.id.btn_higiene_delete);

        txtNomeHigiene.setText(aHigiene.getTitulo());
        txtDescricaoHigiene.setText(aHigiene.getDescricao());

        containerHigiene.addView(view);
    }

}
