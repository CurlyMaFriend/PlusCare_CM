package pedro.gouveia.pluscare_cm.utente;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Utilizador;
import pedro.gouveia.pluscare_cm.main.LoginActivity;

public class FragmentUtilizadorCriar extends Fragment {

    private EditText username, password, verifyPassword, createAddress, email;
    private TextView dataNascimento;
    private Button buttonCreate, buttonCancel;
    private ImageView imageView;
    private Date date1;

    private ArrayList<String> userNames = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.nova_conta_layout, container, false);
    }

    protected void onViewCreated(@Nullable Bundle savedInstanceState, @Nullable View viewFrag) {

        super.onViewCreated(viewFrag,savedInstanceState);

        imageView = viewFrag.findViewById(R.id.imageView);

        //userNames =

        username = viewFrag.findViewById(R.id.createUsernameText);
        password = viewFrag.findViewById(R.id.createPassword);
        verifyPassword = viewFrag.findViewById(R.id.createPasswordVerify);
        createAddress = viewFrag.findViewById(R.id.createAddress);

        email = viewFrag.findViewById(R.id.createEmail);

        dataNascimento = viewFrag.findViewById(R.id.editTextDate);

        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        buttonCreate = viewFrag.findViewById(R.id.buttonCreateAccount);
        buttonCancel = viewFrag.findViewById(R.id.buttonCancelCreate);

        imageView.setOnClickListener(view -> {
            //Colocar permissão para escolher a foto a partir da galeria
            Toast.makeText(getContext(),"Under Development",Toast.LENGTH_SHORT);
        });

        buttonCreate.setOnClickListener(view -> {
            if(userNames.contains(username.getText())){
                Toast.makeText(getContext(),getString(R.string.usernameNotAvailable),Toast.LENGTH_SHORT);
                username.setText("");
                verifyPassword.setText("");
                password.setText("");
                createAddress.setText("");
                dataNascimento.setText("");
            } else {

                if (verifyPassword.getText().equals(password.getText())) {
                    new Utilizador(email.getText().toString(), password.getText().toString(), username.getText().toString(), createAddress.getText().toString(), date1.toString());
                    Toast.makeText(getContext(),getString(R.string.createdAccountSuccess),Toast.LENGTH_LONG);
                    switchActivities(LoginActivity.class);
                } else {
                    Toast.makeText(getContext(), getString(R.string.passwordUnmatched), Toast.LENGTH_SHORT);
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
        Intent switchActivityIntent = new Intent(getContext(), i);
        startActivity(switchActivityIntent);
    }

}
