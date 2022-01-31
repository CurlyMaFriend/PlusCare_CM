package pedro.gouveia.pluscare_cm.utente;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Andar;
import pedro.gouveia.pluscare_cm.classes.Utente;
import pedro.gouveia.pluscare_cm.main.LoginActivity;
import pedro.gouveia.pluscare_cm.viewModels.ViewModelUtente;

public class FragmentUtenteCriar extends Fragment {

    private EditText username, alcunha, morada, dataNascimento, estadoCivil, grauEscolaridade, profissao, nacionalidade, altura, cc, nif, niss, nus;
    private Button buttonCreate, buttonCancel;
    private ImageView imageView;
    private Date date1;
    private AutoCompleteTextView txtEstadoCivil, txtEscolaridade, txtAndar;
    private ArrayAdapter<String> estadoCivilAdapter, grauEscolaridadeAdapter;
    private ArrayAdapter<Andar> andarArrayAdapter;
    private ArrayList<String> userNames = new ArrayList<>();
    private Menu menuBar;
    private ArrayList<Andar> seccoes;
    private CardView cardImage;
    private ActivityResultLauncher<Intent> mGetContent;
    private ImageView imageUtente;

    public FragmentUtenteCriar(ArrayList<Andar> aSeccoes){
        seccoes = aSeccoes;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mGetContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Handle the returned Uri

                    Intent intent = result.getData();

                    if(intent != null){
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                    getActivity().getContentResolver(), intent.getData()
                            );

                            imageUtente.setImageBitmap(bitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        String[] estadoCivilArray = getResources().getStringArray(R.array.estadoCivil);
        String[] grauEscolaridadeArray = getResources().getStringArray(R.array.escolaridadeArray);

        Log.d("teste", estadoCivilArray[0]);

        estadoCivilAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item , estadoCivilArray);
        grauEscolaridadeAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item , grauEscolaridadeArray);
        andarArrayAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item , seccoes);

        return inflater.inflate(R.layout.fragment_utente_criar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtEstadoCivil = view.findViewById(R.id.txtEstadoCivil);
        txtEstadoCivil.setAdapter(estadoCivilAdapter);

        cardImage = view.findViewById(R.id.card_image_utente);

        txtEscolaridade = view.findViewById(R.id.txtEscolaridade);
        txtEscolaridade.setAdapter(grauEscolaridadeAdapter);

        txtAndar = view.findViewById(R.id.txtAndar);
        txtAndar.setAdapter(andarArrayAdapter);

        imageUtente = view.findViewById(R.id.image_utente);

        txtEstadoCivil.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();

                Toast.makeText(getContext(), "Item " + item, Toast.LENGTH_SHORT);
            }
        });

        txtEscolaridade.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();

                Toast.makeText(getContext(), "Item " + item, Toast.LENGTH_SHORT);
            }
        });

        txtAndar.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Andar item = (Andar) parent.getItemAtPosition(i);

                Toast.makeText(getContext(), "Item " + item, Toast.LENGTH_SHORT);
            }
        });

        cardImage.setOnClickListener(view1 -> {
            Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
            gallery.setType("image/*");
            mGetContent.launch(gallery);


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