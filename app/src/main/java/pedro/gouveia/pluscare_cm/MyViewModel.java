package pedro.gouveia.pluscare_cm;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;

public class MyViewModel extends androidx.lifecycle.ViewModel {

    public MutableLiveData<FirebaseUser> currentUser = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Utilizador>> users = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Utente>> utentes = new MutableLiveData<>();

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
}
