package pedro.gouveia.pluscare_cm.utente;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import pedro.gouveia.pluscare_cm.FragmentAdapterTarefa;
import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Utente;
import pedro.gouveia.pluscare_cm.classes.Utilizador;

public class UtilizadorInfo extends AppCompatActivity {

    private TextView andar, nomeUtilizador, idadeUtilizador, email, funcao;
    private FragmentAdapterTarefa fragmentAdapter;
    private Utilizador utilizador;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utilizador_info_layout);

        nomeUtilizador = findViewById(R.id.nomeUtilizador);
        andar = findViewById(R.id.andarUtilizador);
        email = findViewById(R.id.utilizadorEmail);
        funcao = findViewById(R.id.funcaoUtilizador);

        utilizador = (Utilizador) getIntent().getSerializableExtra("utilizador");

        if(utilizador != null){
            nomeUtilizador.setText(utilizador.getNome());
            andar.setText(utilizador.getAndar());
        }

    }


}
