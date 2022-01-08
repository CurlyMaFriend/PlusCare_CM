package pedro.gouveia.pluscare_cm;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragmentUtenteCriar extends Fragment {

    private EditText username, alcunha, morada, dataNascimento, estadoCivil, grauEscolaridade, profissao, nacionalidade, altura, cc, nif, niss, nus;
    private Button buttonCreate, buttonCancel;
    private ImageView imageView;
    private Date date1;

    private ArrayList<String> userNames = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_utente_criar, container, false);
    }

    protected void onViewCreated(@Nullable Bundle savedInstanceState, @Nullable View viewFrag) {

        super.onViewCreated(viewFrag,savedInstanceState);

        imageView = viewFrag.findViewById(R.id.imageView);

        //userNames =

        username = viewFrag.findViewById(R.id.nomeNovoUtente);
        alcunha = viewFrag.findViewById(R.id.nomePrefNovoUtente);
        morada = viewFrag.findViewById(R.id.moradaNovoUtente);
        dataNascimento = viewFrag.findViewById(R.id.dataNascimentoNovoUtente);
        estadoCivil = viewFrag.findViewById(R.id.estadoCivilNovoUtente);
        grauEscolaridade = viewFrag.findViewById(R.id.grauEscolaNovoUtente);
        profissao = viewFrag.findViewById(R.id.profissaoNovoUtente);
        nacionalidade = viewFrag.findViewById(R.id.nacionalidadeNovoUtente);
        altura = viewFrag.findViewById(R.id.alturaNovoUtente);
        cc = viewFrag.findViewById(R.id.ccNovoUtente);
        nif = viewFrag.findViewById(R.id.nifNovoUtente);
        niss = viewFrag.findViewById(R.id.nissNovoUtente);
        nus = viewFrag.findViewById(R.id.nusNovoUtente);

        buttonCreate = viewFrag.findViewById(R.id.buttonCriarUtente);
        buttonCancel = viewFrag.findViewById(R.id.buttonCancelCreate);


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
            new Utente (username.getText().toString(), alcunha.getText().toString(),morada.getText().toString(),date1,estadoCivil.getText().toString(),grauEscolaridade.getText().toString(),profissao.getText().toString(),nacionalidade.getText().toString(),altura.getText().toString(),Integer.parseInt(cc.getText().toString()),Integer.parseInt(nif.getText().toString()),Integer.parseInt(niss.getText().toString()),Integer.parseInt(nus.getText().toString()));
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