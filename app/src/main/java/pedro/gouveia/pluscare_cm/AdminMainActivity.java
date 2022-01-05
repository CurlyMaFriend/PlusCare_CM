package pedro.gouveia.pluscare_cm;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AdminMainActivity extends AppCompatActivity {

    private TextView numTarefa, numMedicamento, numOcorrencia, numHigiene;
    private Button buttonTarefas, buttonMedicamentos, buttonOcorrencias, buttonHigiene;
    private FragmentManager fm;
    private FragmentTransaction ft;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main_activity);



    }

    public void replaceFragment(Fragment frag){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frameView, frag);
        ft.commit();
    }

}
