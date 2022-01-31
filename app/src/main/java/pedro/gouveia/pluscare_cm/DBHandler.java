package pedro.gouveia.pluscare_cm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pedro.gouveia.pluscare_cm.viewModels.MyViewModel;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PlusCare";


    //TABELA TAREFAS
    private static final String TABLE_TAREFAS = "tarefas";
    private static final String FIRSTCOL_TAREFAS= "id";
    private static final String SECONDCOL_TAREFAS = "idFuncionario";
    private static final String THIRDCOL_TAREFAS = "idAndar";
    private static final String FOURTHCOL_TAREFAS = "titulo";
    private static final String FIFTHCOL_TAREFAS = "Descricao";
    private static final String SIXTHCOL_TAREFAS = "dataInicio";
    private static final String SEVENTHCOL_TAREFAS = "dataFim";
    private static final String DICTIONARY_TABLE_TAREFAS_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_TAREFAS + " ("
            + FIRSTCOL_TAREFAS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SECONDCOL_TAREFAS + " INTEGER, "
            + THIRDCOL_TAREFAS + " INTEGER, "
            + FOURTHCOL_TAREFAS + " TEXT, "
            + FIFTHCOL_TAREFAS + " TEXT, "
            + SIXTHCOL_TAREFAS + " DATE, "
            + SEVENTHCOL_TAREFAS + " DATE);";

    //TABELA MEDICAMENTOS
    private static final String TABLE_MEDICAMENTOS = "medicamentos";
    private static final String FIRSTCOL_MEDICAMENTOS = "id";
    private static final String SECONDCOL_MEDICAMENTOS = "Titulo";
    private static final String THIRDCOL_MEDICAMENTOS = "Descricao";
    private static final String DICTIONARY_TABLE_MEDICAMENTOS_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_MEDICAMENTOS + " (" + FIRSTCOL_MEDICAMENTOS + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SECONDCOL_MEDICAMENTOS + " TEXT, " + THIRDCOL_MEDICAMENTOS + " TEXT);";

    //TABELA OCORRENCIAS
    private static final String TABLE_OCORRENCIAS = "ocorrencias";
    private static final String FIRSTCOL_OCORRENCIAS = "id";
    private static final String SECONDCOL_OCORRENCIAS = "Titulo";
    private static final String THIRDCOL_OCORRENCIAS = "Descricao";
    private static final String FIFTHCOL_OCORRENCIAS = "dataInicio";
    private static final String SIXTHCOL_OCORRENCIAS = "dataFim";
    private static final String DICTIONARY_TABLE_OCORRENCIAS_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_OCORRENCIAS + " ("
            + FIRSTCOL_OCORRENCIAS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SECONDCOL_OCORRENCIAS + " TEXT, "
            + THIRDCOL_OCORRENCIAS + " TEXT, "
            + FIFTHCOL_OCORRENCIAS + " DATE, "
            + SIXTHCOL_OCORRENCIAS + " DATE);";


    //TABELA HIGIENES
    private static final String TABLE_HIGIENES = "higienes";
    private static final String FIRSTCOL_HIGIENES = "id";
    private static final String SECONDCOL_HIGIENES = "Titulo";
    private static final String THIRDCOL_HIGIENES = "Descricao";
    private static final String DICTIONARY_TABLE_HIGIENES_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_HIGIENES + " ("
            + FIRSTCOL_HIGIENES + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SECONDCOL_HIGIENES + " TEXT, "
            + THIRDCOL_HIGIENES + " TEXT);";

    //TABELA UTENTES
    private static final String TABLE_UTENTES = "utentes";
    private static final String ONECOL_UTENTES = "id";
    private static final String TWOCOL_UTENTES = "nome";
    private static final String THREECOL_UTENTES = "morada";
    private static final String FOURCOL_UTENTES = "situacaoMatrimonial";
    private static final String FIVECOL_UTENTES = "profissao";
    private static final String SIXCOL_UTENTES = "nivelEducacao";
    private static final String SEVENCOL_UTENTES = "nacionalidade";
    private static final String EIGHTCOL_UTENTES = "nomePreferencia";
    private static final String NINECOL_UTENTES = "tipoSangue";
    private static final String TENCOL_UTENTES = "deficiencias";
    private static final String ELEVENCOL_UTENTES = "doencas";
    private static final String TWELVECOL_UTENTES = "nif";
    private static final String THIRTEENCOL_UTENTES = "nss";
    private static final String FOURTEENCOL_UTENTES = "cc";
    private static final String FIFTHTEENCOL_UTENTES = "dataNascimento";
    private static final String DICTIONARY_TABLE_UTENTES_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_UTENTES + " ("
            + ONECOL_UTENTES + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TWOCOL_UTENTES + " TEXT, "
            + THREECOL_UTENTES + " TEXT, "
            + FOURCOL_UTENTES + " TEXT, "
            + FIVECOL_UTENTES + " TEXT, "
            + SIXCOL_UTENTES + " TEXT, "
            + SEVENCOL_UTENTES + " TEXT, "
            + EIGHTCOL_UTENTES + " TEXT, "
            + NINECOL_UTENTES + " TEXT, "
            + TENCOL_UTENTES + " TEXT, "
            + ELEVENCOL_UTENTES + " TEXT, "
            + TWELVECOL_UTENTES + " INTEGER, "
            + THIRTEENCOL_UTENTES + " INTEGER, "
            + FOURTEENCOL_UTENTES + " INTEGER, "
            + FIFTHTEENCOL_UTENTES + " DATE);";


    private static final String TABLE_UTILIZADORES = "utilizadores";
    private static final String FIRSTCOL_UTILIZADORES = "id";
    private static final String SECONDCOL_UTILIZADORES = "email";
    private static final String SECTHIRD_UTILIZADORES = "password";
    private static final String THIRDCOL_UTILIZADORES = "role";
    private static final String FOURTHCOL_UTILIZADORES = "tipo";
    private static final String DICTIONARY_TABLE_UTILIZADORES_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_UTILIZADORES + " ("
            + FIRSTCOL_UTILIZADORES + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SECONDCOL_UTILIZADORES + " TEXT, "
            + SECTHIRD_UTILIZADORES + " TEXT, "
            + THIRDCOL_UTILIZADORES + " TEXT, "
            + FOURTHCOL_UTILIZADORES + " INTEGER);";



    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());
    private MyViewModel myViewModel;

    DBHandler(Context aContext, MyViewModel aMyViewModel){
        super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
        myViewModel = aMyViewModel;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_TAREFAS_CREATE);
        db.execSQL(DICTIONARY_TABLE_MEDICAMENTOS_CREATE);
        db.execSQL(DICTIONARY_TABLE_OCORRENCIAS_CREATE);
        db.execSQL(DICTIONARY_TABLE_HIGIENES_CREATE);
        db.execSQL(DICTIONARY_TABLE_UTENTES_CREATE);
        db.execSQL(DICTIONARY_TABLE_UTILIZADORES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
