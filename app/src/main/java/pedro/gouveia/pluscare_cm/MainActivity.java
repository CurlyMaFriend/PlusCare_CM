package pedro.gouveia.pluscare_cm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchActivities();

    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, UtilizadorInfo.class);
        startActivity(switchActivityIntent);
    }

}