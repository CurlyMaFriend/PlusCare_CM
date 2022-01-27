package pedro.gouveia.pluscare_cm.firebaseManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pedro.gouveia.pluscare_cm.MyViewModel;

public class AuthManager {

    private String TAG = "AuthManager";
    private FirebaseAuth mAuth;
    private final Handler handler;
    private MyViewModel viewModel;
    private SharedPreferences sharedPreferences;

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public AuthManager(MyViewModel viewModelInstance, SharedPreferences sp){

        this.mAuth = FirebaseAuth.getInstance();
        sharedPreferences = sp;

        this.viewModel = viewModelInstance;
        handler = new Handler();
    }

    public FirebaseUser getUserLogin(){
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){

            user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                @Override
                public void onComplete(@NonNull Task<GetTokenResult> task) {
                    if(task.isSuccessful()){
                        String token = task.getResult().getToken();
                        Log.d(TAG,"Token: " + token);
                        sharedPreferences.edit().putString("user_token", token).commit();
                        sharedPreferences.edit().putString("user_type", user.getDisplayName().substring(user.getDisplayName().indexOf('|')+1)).commit();
                        viewModel.setUserTokenReady("token");
                    } else {
                        Log.d(TAG, "Erro: ");
                    }
                }
            });

        }

        return user;
    }

    public void signOut(){
        mAuth.signOut();
    }

    public CompletableFuture<Void> signInUser(String email, String password){
        executorService.execute(() -> {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            handler.post(()->{
                                viewModel.setCurrentUser(user);
                            });
                            Log.d(TAG, "User authenticated Successfully");
                        } else {
                            handler.post(()->{
                                viewModel.setCurrentUser(null);
                            });
                            Log.d(TAG, "User not authenticated");
                        }
                    }
                });
        });

        return null;
    }

}
