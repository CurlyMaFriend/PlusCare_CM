package pedro.gouveia.pluscare_cm.utente;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Utilizador;
import pedro.gouveia.pluscare_cm.main.LoginActivity;

public class FragmentUtilizadorCriar extends Fragment {

    private TextInputEditText nomeUtilizador, moradaText, emailText, passwordText, estadoCivilText;
    private Button buttonCriar, buttonCancelar;
    private ArrayList<String> emails = new ArrayList<>();
    private Date date1;

    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_utilizador_criar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //emails =

        nomeUtilizador = view.findViewById(R.id.txtNomeUtilizador);
        passwordText = view.findViewById(R.id.txtPasswordUtilizador);
        moradaText = view.findViewById(R.id.moradaText);

        emailText = view.findViewById(R.id.txtEmailUtilizador);

        buttonCriar = view.findViewById(R.id.buttonCriarUtilizador);
        buttonCancelar = view.findViewById(R.id.buttonCancelarUtilizador);

        buttonCriar.setOnClickListener(view2 -> {
            if(emails.contains(emailText.getText())){
                Toast.makeText(getContext(),getString(R.string.usernameNotAvailable),Toast.LENGTH_SHORT);
                nomeUtilizador.setText("");
                emailText.setText("");
                passwordText.setText("");
                moradaText.setText("");
                emailText.setText("");
            } else {

                    new Utilizador(emailText.getText().toString(), passwordText.getText().toString(), nomeUtilizador.getText().toString(), moradaText.getText().toString());
                    Toast.makeText(getContext(),getString(R.string.createdAccountSuccess),Toast.LENGTH_LONG);
            }
        });

        buttonCancelar.setOnClickListener(view2-> {
            Log.d("teste","teste do botao cancelar");
            getParentFragmentManager().beginTransaction().hide(this).commit();
            getActivity().findViewById(R.id.utilizadoresFrame).setVisibility(View.GONE);
            getActivity().findViewById(R.id.floatingActionButton).setVisibility(View.VISIBLE);
            getActivity().findViewById(R.id.utilizadoresScroll).setVisibility(View.VISIBLE);
        });

        /*buttonCriar.setOnClickListener(view -> {
            if(emails.contains(emailText.getText())){
                Toast.makeText(getContext(),getString(R.string.usernameNotAvailable),Toast.LENGTH_SHORT);
                nomeUtilizador.setText("");
                emailText.setText("");
                passwordText.setText("");
                moradaText.setText("");
                emailText.setText("");
                dataNascimentoText.setText("");
            } else {

                    new Utilizador(emailText.getText().toString(), passwordText.getText().toString(), nomeUtilizador.getText().toString(), moradaText.getText().toString(), date1.toString());
                    Toast.makeText(getContext(),getString(R.string.createdAccountSuccess),Toast.LENGTH_LONG);
            }
        });*/


    }



}
