package pedro.gouveia.pluscare_cm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NovaContaActivity extends AppCompatActivity {

    private EditText username, password, verifyPassword, createAddress, email;
    private TextView dataNascimento;
    private Button buttonCreate, buttonCancel;
    private ImageView imageView;
    private Date date1;

    private ArrayList<String> userNames = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_conta_layout);

        imageView = findViewById(R.id.imageView);

        //userNames =

        username = findViewById(R.id.createUsernameText);
        password = findViewById(R.id.createPassword);
        verifyPassword = findViewById(R.id.createPasswordVerify);
        createAddress = findViewById(R.id.createAddress);

        email = findViewById(R.id.createEmail);

        dataNascimento = findViewById(R.id.editTextDate);

        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        buttonCreate = findViewById(R.id.buttonCreateAccount);
        buttonCancel = findViewById(R.id.buttonCancelCreate);

        imageView.setOnClickListener(view -> {
            //Colocar permissão para escolher a foto a partir da galeria
            Toast.makeText(this,"Under Development",Toast.LENGTH_SHORT);
        });

        buttonCreate.setOnClickListener(view -> {
            if(userNames.contains(username.getText())){
                Toast.makeText(this,getString(R.string.usernameNotAvailable),Toast.LENGTH_SHORT);
                username.setText("");
                verifyPassword.setText("");
                password.setText("");
                createAddress.setText("");
                dataNascimento.setText("");
            } else {

                if (verifyPassword.getText().equals(password.getText())) {
                    new Utilizador(username.getText().toString(), email.getText().toString(), createAddress.getText().toString(), date1);
                    Toast.makeText(this,getString(R.string.createdAccountSuccess),Toast.LENGTH_LONG);
                    switchActivities(LoginActivity.class);
                } else {
                    Toast.makeText(this, getString(R.string.passwordUnmatched), Toast.LENGTH_SHORT);
                    verifyPassword.setText("");
                    password.setText("");
                }
            }
        });

        buttonCancel.setOnClickListener(view -> {
            switchActivities(LoginActivity.class);
        });
    }

    private void switchActivities(Class i) {
        Intent switchActivityIntent = new Intent(this, i);
        startActivity(switchActivityIntent);
    }

}
