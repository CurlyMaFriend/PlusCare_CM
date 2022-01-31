package pedro.gouveia.pluscare_cm.viewModels;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

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

public class ViewModelUtente extends androidx.lifecycle.ViewModel {

    public MutableLiveData<Integer> goBack = new MutableLiveData<>();

    public MutableLiveData<Integer> getGoBack() {
        return goBack;
    }

    public void setGoBack(Integer goBack) {
        this.goBack.setValue(goBack);
    }
}
