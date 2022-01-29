package pedro.gouveia.pluscare_cm;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;

import pedro.gouveia.pluscare_cm.admin.AdminStats;
import pedro.gouveia.pluscare_cm.classes.Higiene;
import pedro.gouveia.pluscare_cm.classes.Medicamento;
import pedro.gouveia.pluscare_cm.classes.Ocorrencia;
import pedro.gouveia.pluscare_cm.classes.Tarefa;
import pedro.gouveia.pluscare_cm.classes.Utente;
import pedro.gouveia.pluscare_cm.classes.Utilizador;

public class MyViewModel extends androidx.lifecycle.ViewModel {

    public MutableLiveData<FirebaseUser> currentUser = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Utilizador>> users = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Utente>> utentes = new MutableLiveData<>();
    public MutableLiveData<AdminStats> adminStats = new MutableLiveData<>();
    public MutableLiveData<String> userTokenReady = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Medicamento>> medicamentos = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Tarefa>> tarefasUtenteComp = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Tarefa>> tarefasUtenteFaz = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Tarefa>> tarefasUtentePassadas = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Higiene>> higienes = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Tarefa>> getTarefasUtenteComp() {
        return tarefasUtenteComp;
    }

    public void setTarefasUtenteComp(Tarefa[] tarefasUtenteComp) {
        this.tarefasUtenteComp.setValue(new ArrayList<Tarefa>(Arrays.asList(tarefasUtenteComp)));
    }

    public MutableLiveData<ArrayList<Tarefa>> getTarefasUtenteFaz() {
        return tarefasUtenteFaz;
    }

    public void setTarefasUtenteFaz(Tarefa[] tarefasUtenteFaz) {
        this.tarefasUtenteFaz.setValue(new ArrayList<Tarefa>(Arrays.asList(tarefasUtenteFaz)));
    }

    public MutableLiveData<ArrayList<Tarefa>> getTarefasUtentePassadas() {
        return tarefasUtentePassadas;
    }

    public void setTarefasUtentePassadas(Tarefa[] tarefasUtentePassadas) {
        this.tarefasUtentePassadas.setValue(new ArrayList<Tarefa>(Arrays.asList(tarefasUtentePassadas)));
    }

    public MutableLiveData<String> getUserTokenReady() {
        return userTokenReady;
    }

    public void setUserTokenReady(String userTokenReady) {
        Log.d("teste", "entrou no set user token");
        this.userTokenReady.setValue(userTokenReady);
    }

    public MutableLiveData<AdminStats> getAdminStats() {
        return adminStats;
    }

    public void setAdminStats(AdminStats as) {
        this.adminStats.setValue(as);
    }

    public MutableLiveData<ArrayList<Utente>> getUtentes() {
        return utentes;
    }

    public void addUtente(Utente utente) {
        ArrayList<Utente> utentesArray = utentes.getValue();
        utentesArray.add(utente);
        this.utentes.setValue(utentesArray);
    }

    public void setUtentes(Utente[] utente) {
        this.utentes.setValue(new ArrayList<Utente>(Arrays.asList(utente)));
    }

    public MutableLiveData<ArrayList<Utilizador>> getUsers() {
        return users;
    }

    public void addUser(Utilizador user) {
        ArrayList<Utilizador> usersArray = users.getValue();
        usersArray.add(user);
        this.users.setValue(usersArray);
    }

    public void setUsers(Utilizador[] users) {
        this.users.setValue(new ArrayList<Utilizador>(Arrays.asList(users)));
    }

    public MutableLiveData<ArrayList<Medicamento>> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(Medicamento[] medicamentos){
        this.medicamentos.setValue(new ArrayList<Medicamento>(Arrays.asList(medicamentos)));
    }

    public MutableLiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(FirebaseUser currentUser) {
        this.currentUser.setValue(currentUser);
    }

    public static boolean isNetworkAvailable(Context con) {
        try {
            ConnectivityManager cm = (ConnectivityManager) con
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setOcorrencias(Ocorrencia[] ocorrencias) {

    }

    public MutableLiveData<ArrayList<Higiene>> getHigienes() {
        return higienes;
    }

    public void setHigienes(Higiene[] higienes) {
        this.higienes.setValue(new ArrayList<Higiene>(Arrays.asList(higienes)));
    }

}
