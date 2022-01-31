package pedro.gouveia.pluscare_cm.utente;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import pedro.gouveia.pluscare_cm.FragmentAdapterTarefa;
import pedro.gouveia.pluscare_cm.FragmentLoading;
import pedro.gouveia.pluscare_cm.classes.Andar;
import pedro.gouveia.pluscare_cm.viewModels.MyViewModel;
import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Tarefa;
import pedro.gouveia.pluscare_cm.classes.Utente;
import pedro.gouveia.pluscare_cm.firebaseManager.FunctionsManager;
import pedro.gouveia.pluscare_cm.viewModels.ViewModelUtente;

public class CriarUtente extends AppCompatActivity implements View.OnClickListener{

    private TextView txtNomeUtente, txtNomePrefUtente, txtDataNascimento, txtMoradaUtente, txtProfissao, txtNacionalidade, txtAltura, txtCC, txtNIF, txtNISS, txtNUS;
    private AutoCompleteTextView txtEstadoCivil, txtEscolaridade, txtAndar;
    private ArrayAdapter<String> estadoCivilAdapter, grauEscolaridadeAdapter;
    private ArrayAdapter<Andar> andarArrayAdapter;
    private ArrayList<String> userNames = new ArrayList<>();
    private ArrayList<Andar> seccoes;
    //private CardView cardImage;
    private ActivityResultLauncher<Intent> mGetContent;
    private ActivityResultLauncher<String> contentString;
    //private ImageView imageUtente;
    private Button btnCriarUtente, btnCancelar;

    private MyViewModel viewModel;
    private ViewModelUtente vmUtente;
    private FunctionsManager functionsManager;
    private Toolbar toolbarUtente;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private FrameLayout utentesFrame;
    private String estadoCivil, escolaridade;
    private Andar andar;
    private View contextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_utente_criar);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        vmUtente = new ViewModelProvider(this).get(ViewModelUtente.class);


        functionsManager = new FunctionsManager(this, PreferenceManager.getDefaultSharedPreferences(getApplicationContext()), viewModel);

        contextView = findViewById(R.id.context_view);

        toolbarUtente = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarUtente);

        utentesFrame = findViewById(R.id.utentesFrame);
        utentesFrame.setVisibility(View.VISIBLE);
        replaceFragment(new FragmentLoading());

        /*GET IMAGE FROM GALLERY*/
/*        mGetContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // Handle the returned Uri

                        Intent intent = result.getData();

                        if(intent != null){
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                        getContentResolver(), intent.getData()
                                );

                                imageUtente.setImageBitmap(bitmap);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });*/

      /*  contentString = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imageUtente.setImageURI(result);
            }
        });*/

        // Inflate the layout for this fragment
        String[] estadoCivilArray = getResources().getStringArray(R.array.estadoCivil);
        String[] grauEscolaridadeArray = getResources().getStringArray(R.array.escolaridadeArray);

        Log.d("teste", estadoCivilArray[0]);

        estadoCivilAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item , estadoCivilArray);
        grauEscolaridadeAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item , grauEscolaridadeArray);

        btnCriarUtente = findViewById(R.id.buttonCriarUtente);
        btnCancelar =  findViewById(R.id.buttonCancelarUtente);
        btnCancelar.setOnClickListener(this);
        btnCriarUtente.setOnClickListener(this);

        txtEstadoCivil = findViewById(R.id.txtEstadoCivil);
        txtEstadoCivil.setAdapter(estadoCivilAdapter);

        //cardImage = findViewById(R.id.card_image_utente);

        txtEscolaridade = findViewById(R.id.txtEscolaridade);
        txtEscolaridade.setAdapter(grauEscolaridadeAdapter);

        txtAndar = findViewById(R.id.txtAndar);

        //imageUtente = findViewById(R.id.image_utente);

        txtEstadoCivil.setOnItemClickListener((adapterView, view, i, l) -> {
            Log.d("teste", "Position ESTADO: " + i);
            estadoCivil = adapterView.getItemAtPosition(i).toString();
        });
        txtEscolaridade.setOnItemClickListener((adapterView, view, i, l) -> {
            Log.d("teste", "Position ESCOL: " + i);
            escolaridade = adapterView.getItemAtPosition(i).toString();
        });

        txtNomeUtente = findViewById(R.id.txtNomeUtente);
        txtNomePrefUtente = findViewById(R.id.txtNomePrefUtente);
        txtDataNascimento = findViewById(R.id.txtDataNascimento);
        txtMoradaUtente = findViewById(R.id.txtMoradaUtente);
        txtProfissao = findViewById(R.id.txtProfissao);
        txtNacionalidade = findViewById(R.id.txtNacionalidade);
        txtAltura = findViewById(R.id.txtAltura);
        txtCC = findViewById(R.id.txtCC);
        txtNIF = findViewById(R.id.txtNIF);
        txtNISS = findViewById(R.id.txtNISS);
        txtNUS = findViewById(R.id.txtNUS);

        //cardImage.setOnClickListener(this);

        vmUtente.getAddUtenteSuccess().observe(this, item -> {
            Snackbar snack = Snackbar.make(contextView, "Utente inserido com sucesso!", Snackbar.LENGTH_SHORT);
            snack.setAction("Ok", view -> { });
            snack.addCallback( new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);

                    Intent i = new Intent(getBaseContext(), UtentesListaActivity.class);
                    startActivity(i);
                }
            });
            snack.show();
        });

        vmUtente.getAddUtenteError().observe(this, item -> {
            Snackbar snack = Snackbar.make(contextView, item, Snackbar.LENGTH_SHORT);
            snack.setAction("Ok", view -> { });
            snack.show();
        });

        viewModel.getSeccoes().observe(this, item ->{

            if(item == null){
                Toast.makeText(this, "No seccoes", Toast.LENGTH_LONG);
                utentesFrame.setVisibility(View.GONE);
            } else if (item.size() > 0) {
                seccoes = item;
                utentesFrame.setVisibility(View.GONE);
                andarArrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item , seccoes);
                txtAndar.setAdapter(andarArrayAdapter);
                txtAndar.setOnItemClickListener((adapterView, view, i, l) -> {
                    Log.d("teste", "Position: " + i + ", adapter size = " + andarArrayAdapter.getCount());
                    andar = (Andar) adapterView.getItemAtPosition(i);
                });
            }
        });
        functionsManager.getSeccoes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_menu, menu);
        
        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        
        if(id == R.id.goBack){
            Log.d("teste", "Goback pressed");
           
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public void replaceFragment(Fragment frag){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.utentesFrame, frag);
        ft.commit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){/*
            case R.id.card_image_utente:
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                mGetContent.launch(gallery);
                break;*/
            case R.id.buttonCriarUtente:
                criarUtente();
                break;
            case R.id.buttonCancelarUtente:

                Intent i = new Intent(getBaseContext(), UtentesListaActivity.class);
                startActivity(i);
                break;
        }
    }

    private void criarUtente(){

        String nome, nomePref, morada, profissao, cc, nif,nus,niss,altura, nacionalidade,dataNascimento;


        nome = txtNomeUtente.getText().toString();
        nomePref = txtNomePrefUtente.getText().toString();
        morada = txtMoradaUtente.getText().toString();
        profissao = txtProfissao.getText().toString();
        altura = txtAltura.getText().toString();
        cc = txtCC.getText().toString();
        nif = txtNIF.getText().toString();
        niss = txtNISS.getText().toString();
        nus = txtNUS.getText().toString();
        dataNascimento = txtDataNascimento.getText().toString();
        nacionalidade = txtNacionalidade.getText().toString();
        Snackbar snack = Snackbar.make(contextView, "Tem informações por preencher", Snackbar.LENGTH_SHORT);
        snack.setAction("Ok", view -> { });
        if(andar == null || estadoCivil == "" || escolaridade == "" || dataNascimento == "" || nus == ""
                || niss == "" || nif == "" || cc == "" || altura == ""
                || profissao == "" || morada == "" || nomePref == "" || nome == ""){

            if(andar == null){  Log.d("teste", "Andar"); }
            if(estadoCivil == ""){ Log.d("teste", "estadoCivil"); }
            if(escolaridade == ""){ Log.d("teste", "escolaridade"); }
            if(dataNascimento == ""){ Log.d("teste", "dataNascimento"); }
            if(nus == ""){ Log.d("teste", "nus"); }
            if(niss == ""){ Log.d("teste", "niss"); }
            if(nif == ""){ Log.d("teste", "nif"); }
            if(cc == ""){ Log.d("teste", "cc"); }
            if(altura == ""){ Log.d("teste", "altura"); }
            if(profissao == ""){ Log.d("teste", "profissao"); }
            if(morada == ""){ Log.d("teste", "morada"); }
            if(nomePref == ""){ Log.d("teste", "nomePref"); }
            if(nome == ""){ Log.d("teste", "nome"); }

            snack.show();
        } else {
            if(cc.length() != 8){
                snack = Snackbar.make(contextView, "O CC tem de conter 8 digitos", Snackbar.LENGTH_SHORT);
                snack.setAction("Ok", view -> { });
                snack.show();
            } else if(nif.length() != 9){
                snack = Snackbar.make(contextView, "O NIF tem de conter 9 digitos", Snackbar.LENGTH_SHORT);
                snack.setAction("Ok", view -> { });
                snack.show();
            } else if(niss.length() != 11){
                snack = Snackbar.make(contextView, "O NISS tem de conter 11 digitos", Snackbar.LENGTH_SHORT);
                snack.setAction("Ok", view -> { });
                snack.show();
            } else if(nus.length() != 9){
                snack = Snackbar.make(contextView, "O NUS tem de conter 9 digitos", Snackbar.LENGTH_SHORT);
                snack.setAction("Ok", view -> { });
                snack.show();
            } else {
                Utente u = new Utente(nome, nomePref, morada, dataNascimento,
                        estadoCivil, escolaridade, profissao, nacionalidade,
                        Long.parseLong(altura), Long.parseLong(cc), Long.parseLong(nif),
                        Long.parseLong(niss), Long.parseLong(nus), andar.getId());

                functionsManager.addUtente(u, vmUtente);

                Log.d("teste", u.toString());
            }
        }
    }
}
