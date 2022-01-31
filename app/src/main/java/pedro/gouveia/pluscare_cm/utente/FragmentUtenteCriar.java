package pedro.gouveia.pluscare_cm.utente;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Utente;
import pedro.gouveia.pluscare_cm.main.LoginActivity;
import pedro.gouveia.pluscare_cm.viewModels.ViewModelUtente;

public class FragmentUtenteCriar extends Fragment {

    private EditText username, alcunha, morada, dataNascimento, estadoCivil, grauEscolaridade, profissao, nacionalidade, altura, cc, nif, niss, nus;
    private Button buttonCreate, buttonCancel;
    private ImageView imageView;
    private Date date1;
    private AutoCompleteTextView txtEstadoCivil;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> userNames = new ArrayList<>();
    private Menu menuBar;


    public FragmentUtenteCriar(){ }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        String[] estadoCivilArray = getResources().getStringArray(R.array.estadoCivil);

        Log.d("teste", estadoCivilArray[0]);

        arrayAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item , estadoCivilArray);

        return inflater.inflate(R.layout.fragment_utente_criar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtEstadoCivil = view.findViewById(R.id.txtEstadoCivil);
        txtEstadoCivil.setAdapter(arrayAdapter);

        txtEstadoCivil.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();

                Toast.makeText(getContext(), "Item " + item, Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        menuBar = menu;
        inflater.inflate(R.menu.fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}