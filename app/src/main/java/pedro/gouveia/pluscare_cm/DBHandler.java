package pedro.gouveia.pluscare_cm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBHandler extends SQLiteOpenHelper implements Runnable{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PlusCare";

    //TABELA TAREFAS
    private static final String TABLE_TAREFAS = "tarefas";
    private static final String FIRSTCOL_TAREFAS= "id";
    private static final String FIRSTSECONDCOL_TAREFAS= "medicamentoId";
    private static final String SECONDFIRSTCOL_TAREFAS= "tarefasId";
    private static final String SECONDCOL_TAREFAS = "idFuncionario";
    private static final String THIRDCOL_TAREFAS = "idAndar";
    private static final String FOURTHCOL_TAREFAS = "titulo";
    private static final String FIFTHCOL_TAREFAS = "Descricao";
    private static final String SIXTHCOL_TAREFAS = "Estado";
    private static final String SEVENTHCOL_TAREFAS = "idUtente";
    private static final String EIGTHCOL_TAREFAS = "dataTarefa";
    private static final String DICTIONARY_TABLE_TAREFAS_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_TAREFAS + " (" + FIRSTCOL_TAREFAS + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIRSTSECONDCOL_TAREFAS + " TEXT, " + SECONDFIRSTCOL_TAREFAS + " TEXT, " + SECONDCOL_TAREFAS + " TEXT, " + THIRDCOL_TAREFAS + " TEXT, " + FOURTHCOL_TAREFAS + " TEXT, " + FIFTHCOL_TAREFAS + " TEXT, " + SIXTHCOL_TAREFAS + " TEXT, " + SEVENTHCOL_TAREFAS + " TEXT, " + EIGTHCOL_TAREFAS +" DATE);";

    //TABELA MEDICAMENTOS
    private static final String TABLE_MEDICAMENTOS = "medicamentos";
    private static final String FIRSTCOL_MEDICAMENTOS = "id";
    private static final String SECONDCOL_MEDICAMENTOS = "Nome";
    private static final String THIRDCOL_MEDICAMENTOS = "Descricao";
    private static final String FOURDCOL_MEDICAMENTOS = "Tipo";
    private static final String DICTIONARY_TABLE_MEDICAMENTOS_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_MEDICAMENTOS + " (" + FIRSTCOL_MEDICAMENTOS + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SECONDCOL_MEDICAMENTOS + " TEXT, " + THIRDCOL_MEDICAMENTOS + " TEXT, " + FOURDCOL_MEDICAMENTOS + " TEXT); ";

    //TABELA OCORRENCIAS
    private static final String TABLE_OCORRENCIAS = "ocorrencias";
    private static final String FIRSTCOL_OCORRENCIAS = "id";
    private static final String SECONDCOL_OCORRENCIAS = "Titulo";
    private static final String THIRDCOL_OCORRENCIAS = "Descricao";
    private static final String FIFTHCOL_OCORRENCIAS = "dataInicio";
    private static final String DICTIONARY_TABLE_OCORRENCIAS_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_OCORRENCIAS + " (" + FIRSTCOL_OCORRENCIAS + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SECONDCOL_OCORRENCIAS + " TEXT, " + THIRDCOL_OCORRENCIAS + " TEXT, " + FIFTHCOL_OCORRENCIAS + " DATE);";


    //TABELA HIGIENES
    private static final String TABLE_HIGIENES = "higienes";
    private static final String FIRSTCOL_HIGIENES = "id";
    private static final String SECONDCOL_HIGIENES = "Titulo";
    private static final String THIRDCOL_HIGIENES = "Descricao";
    private static final String DICTIONARY_TABLE_HIGIENES_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_HIGIENES + " (" + FIRSTCOL_HIGIENES + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SECONDCOL_HIGIENES + " TEXT, " + THIRDCOL_HIGIENES + " TEXT);";

    //TABELA UTENTES
    private static final String TABLE_UTENTES = "utentes";
    private static final String ONECOL_UTENTES = "id";
    private static final String TWOCOL_UTENTES = "nome";
    private static final String THREECOL_UTENTES = "nomePreferencia";
    private static final String FOURCOL_UTENTES = "morada";
    private static final String FIVECOL_UTENTES = "dataNascimento";
    private static final String SIXCOL_UTENTES = "estadoCivil";
    private static final String SEVENCOL_UTENTES = "grauEscolaridade";
    private static final String EIGHTCOL_UTENTES = "profissao";
    private static final String NINECOL_UTENTES = "nacionalidade";
    private static final String TENCOL_UTENTES = "altura";
    private static final String ELEVENCOL_UTENTES = "cc";
    private static final String TWELVECOL_UTENTES = "nif";
    private static final String THIRTEENCOL_UTENTES = "niss";
    private static final String FOURTEENCOL_UTENTES = "nus";
    private static final String DICTIONARY_TABLE_UTENTES_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_UTENTES + " (" + ONECOL_UTENTES + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TWOCOL_UTENTES + " TEXT, " + THREECOL_UTENTES + " TEXT, " + FOURCOL_UTENTES + " TEXT, " + FIVECOL_UTENTES + " TEXT, " + SIXCOL_UTENTES + " TEXT, " + SEVENCOL_UTENTES + " TEXT, " + EIGHTCOL_UTENTES + " TEXT, " + NINECOL_UTENTES + " TEXT, " + TENCOL_UTENTES + " TEXT, " + ELEVENCOL_UTENTES + " TEXT, " + TWELVECOL_UTENTES + " INTEGER, " + THIRTEENCOL_UTENTES + " INTEGER, " + FOURTEENCOL_UTENTES + " INTEGER);";


    private static final String TABLE_UTILIZADORES = "utilizadores";
    private static final String FIRSTCOL_UTILIZADORES = "id";
    private static final String SECONDCOL_UTILIZADORES = "nome";
    private static final String SECTHIRD_UTILIZADORES = "morada";
    private static final String THIRDCOL_UTILIZADORES = "email";
    private static final String FOURTHCOL_UTILIZADORES = "tipo";
    private static final String FIFTHCOL_UTILIZADORES = "dataNascimento";
    private static final String DICTIONARY_TABLE_UTILIZADORES_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_UTILIZADORES + " (" + FIRSTCOL_UTILIZADORES + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SECONDCOL_UTILIZADORES + " TEXT, " + SECTHIRD_UTILIZADORES + " TEXT, " + THIRDCOL_UTILIZADORES + " TEXT, " + FOURTHCOL_UTILIZADORES + " INTEGER, " + FIFTHCOL_UTILIZADORES +" TEXT);";



    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());
    private MyViewModel myViewModel;

    DBHandler(Context aContext, MyViewModel aMyViewModel){
        super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
        myViewModel = aMyViewModel;
        Log.d("teste","instanciou");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("teste","a criar o primeiro");
        db.execSQL(DICTIONARY_TABLE_TAREFAS_CREATE);
        db.execSQL(DICTIONARY_TABLE_MEDICAMENTOS_CREATE);
        db.execSQL(DICTIONARY_TABLE_OCORRENCIAS_CREATE);
        db.execSQL(DICTIONARY_TABLE_HIGIENES_CREATE);
        db.execSQL(DICTIONARY_TABLE_UTENTES_CREATE);
        db.execSQL(DICTIONARY_TABLE_UTILIZADORES_CREATE);
        Log.d("teste","deu");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("teste","deu upgraed");
    }

    public void addMedicamento(Medicamento aMedicamento){

        executor.execute(() -> {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(SECONDCOL_MEDICAMENTOS, aMedicamento.getNome());
            values.put(THIRDCOL_MEDICAMENTOS, aMedicamento.getDescricao());
            values.put(FOURDCOL_MEDICAMENTOS, aMedicamento.getTipo());

            db.insert(TABLE_MEDICAMENTOS,null,values);

            db.close();

            //ArrayList<String> topicList = getAllTopics();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    /*myViewModel.setTopics(topicList);
                    myViewModel.setNavigateTopic();*/
                }
            });
        });

    }

    public void addOcorrencia(Ocorrencia aOcorrencia){

        executor.execute(() -> {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(SECONDCOL_OCORRENCIAS, aOcorrencia.getTitulo());
            values.put(THIRDCOL_OCORRENCIAS, aOcorrencia.getDescricao());
            values.put(FIFTHCOL_OCORRENCIAS, aOcorrencia.getDataInicio()+"");

            db.insert(TABLE_OCORRENCIAS,null,values);

            db.close();

            //ArrayList<String> topicList = getAllTopics();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    /*myViewModel.setTopics(topicList);
                    myViewModel.setNavigateTopic();*/
                }
            });
        });
    }

    public void addHigiene(Higiene aHigiene){

        executor.execute(() -> {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(SECONDCOL_HIGIENES, aHigiene.getTitulo());
            values.put(THIRDCOL_HIGIENES, aHigiene.getDescricao());

            db.insert(TABLE_HIGIENES,null,values);

            db.close();

            //ArrayList<String> topicList = getAllTopics();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    /*myViewModel.setTopics(topicList);
                    myViewModel.setNavigateTopic();*/
                }
            });
        });
    }

    public void addTarefa(Tarefa aTarefa){

        executor.execute(() -> {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(FIRSTSECONDCOL_TAREFAS, aTarefa.getMedicamentoId());
            values.put(SECONDFIRSTCOL_TAREFAS, aTarefa.getHigieneId());
            values.put(SECONDCOL_TAREFAS, aTarefa.getIdFuncionario());
            values.put(THIRDCOL_TAREFAS, aTarefa.getIdAndar());
            values.put(FOURTHCOL_TAREFAS, aTarefa.getTitulo());
            values.put(FIFTHCOL_TAREFAS, aTarefa.getDescricao());
            values.put(SIXTHCOL_TAREFAS, aTarefa.getEstado());
            values.put(SEVENTHCOL_TAREFAS, aTarefa.getIdUtente());
            values.put(EIGTHCOL_TAREFAS, aTarefa.getDataTarefa()+"");

            db.insert(TABLE_TAREFAS,null,values);

            db.close();

            //ArrayList<String> topicList = getAllTopics();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    /*myViewModel.setTopics(topicList);
                    myViewModel.setNavigateTopic();*/
                }
            });
        });
    }

    public void addUtente(Utente aUtente){

        executor.execute(() -> {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(TWOCOL_UTENTES, aUtente.getNome());
            values.put(THREECOL_UTENTES, aUtente.getNomePreferencia());
            values.put(FOURCOL_UTENTES, aUtente.getMorada());
            values.put(FIVECOL_UTENTES, aUtente.getDataNascimento()+"");
            values.put(SIXCOL_UTENTES, aUtente.getEstadoCivil());
            values.put(SEVENCOL_UTENTES, aUtente.getGrauEscolaridade());
            values.put(EIGHTCOL_UTENTES, aUtente.getProfissao());
            values.put(NINECOL_UTENTES, aUtente.getNacionalidade());
            values.put(TENCOL_UTENTES, aUtente.getAltura());
            values.put(ELEVENCOL_UTENTES, aUtente.getCc());
            values.put(TWELVECOL_UTENTES, aUtente.getNif());
            values.put(THIRTEENCOL_UTENTES, aUtente.getNiss());
            values.put(FOURTEENCOL_UTENTES, aUtente.getNus());

            db.insert(TABLE_UTENTES,null,values);

            db.close();

            //ArrayList<String> topicList = getAllTopics();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    /*myViewModel.setTopics(topicList);
                    myViewModel.setNavigateTopic();*/
                }
            });
        });
    }

    public void addUtilizador(Utilizador aUtilizador){

        executor.execute(() -> {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(SECONDCOL_UTILIZADORES, aUtilizador.getNome());
            values.put(SECTHIRD_UTILIZADORES, aUtilizador.getMorada());
            values.put(THIRDCOL_UTILIZADORES, aUtilizador.getEmail());
            values.put(FOURTHCOL_UTILIZADORES, aUtilizador.getTipo());
            values.put(FIFTHCOL_UTILIZADORES, aUtilizador.getData_nascimento()+"");

            db.insert(TABLE_UTILIZADORES,null,values);

            db.close();

            //ArrayList<String> topicList = getAllTopics();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    /*myViewModel.setTopics(topicList);
                    myViewModel.setNavigateTopic();*/
                }
            });
        });
    }

    // As listagens abaixo precisam de ser testadas - UNTESTED

    public ArrayList<Tarefa> listTarefas(){

        String idFunc, idAndar, titulo, descricao, estado, idUtente, data;
        Date date2 = null;
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Tarefa> tarefasList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_TAREFAS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                idFunc = cursor.getString(1);
                idAndar = cursor.getString(2);
                titulo = cursor.getString(3);
                descricao = cursor.getString(4);
                estado = cursor.getString(5);
                idUtente = cursor.getString(6);
                data = cursor.getString(7);

                try {
                    date2 = new SimpleDateFormat("dd/MM/yyyy").parse(data);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                tarefasList.add(new Tarefa(idFunc,idAndar,titulo,descricao,estado,idUtente,date2));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return tarefasList;
    }

    public ArrayList<Higiene> listHigienes(){

        String titulo, descricao;

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Higiene> higienesList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_HIGIENES;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                titulo = cursor.getString(1);
                descricao = cursor.getString(2);
                higienesList.add(new Higiene(titulo,descricao));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return contact list
        return higienesList;

    }

    public ArrayList<Ocorrencia> listOcorrencias(){

        String titulo, descricao, dataInicio;
        Date date2 = null;

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Ocorrencia> ocorrenciasList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_OCORRENCIAS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                titulo = cursor.getString(1);
                descricao = cursor.getString(2);
                dataInicio = cursor.getString(3);

                try {
                    date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicio);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                ocorrenciasList.add(new Ocorrencia(titulo,descricao,date2));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return ocorrenciasList;

    }

    public ArrayList<Medicamento> listMedicamentos(){

        String nome, descricao, tipo;

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Medicamento> medicamentosList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_MEDICAMENTOS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                nome = cursor.getString(1);
                descricao = cursor.getString(2);
                tipo = cursor.getString(3);

                medicamentosList.add(new Medicamento(nome, descricao, tipo));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return contact list
        return medicamentosList;

    }

    public ArrayList<Utente> listUtentes(){

        String nome, nomePref, morada, dataNascimento, estadoCivil, grauEscolaridade, profissao, nacionalidade;
        long cc, nif, niss, nus;
        int altura;
        Date date2 = null;

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Utente> utentesList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_UTENTES;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                nome = cursor.getString(1);
                nomePref = cursor.getString(2);
                morada = cursor.getString(3);
                dataNascimento = cursor.getString(4);
                estadoCivil = cursor.getString(5);
                grauEscolaridade = cursor.getString(6);
                profissao = cursor.getString(7);
                nacionalidade = cursor.getString(8);
                altura = cursor.getInt(9);
                cc = cursor.getLong(10);
                nif = cursor.getLong(11);
                niss = cursor.getLong(12);
                nus = cursor.getLong(13);

                try {
                    date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                utentesList.add(new Utente(nome, nomePref, morada, date2, estadoCivil, grauEscolaridade, profissao, nacionalidade, altura, cc, nif, niss, nus));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return contact list
        return utentesList;

    }

    public ArrayList<Utilizador> listUtilizadores(){

        String nome, morada, email, tipo, dataNascimento;
        Date date2 = null;

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Utilizador> utilizadoresList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_UTILIZADORES;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                nome = cursor.getString(1);
                morada = cursor.getString(2);
                email = cursor.getString(3);
                dataNascimento = cursor.getString(5);

                try {
                    date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                utilizadoresList.add(new Utilizador(nome, morada, email, date2));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return contact list
        return utilizadoresList;

    }


    @Override
    public void run() {

    }
}
