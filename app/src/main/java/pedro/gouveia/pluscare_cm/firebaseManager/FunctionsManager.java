package pedro.gouveia.pluscare_cm.firebaseManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pedro.gouveia.pluscare_cm.MyViewModel;
import pedro.gouveia.pluscare_cm.Utente;
import pedro.gouveia.pluscare_cm.Utilizador;

public class FunctionsManager {

    private MyViewModel viewModel;
    private String apiUrl = "https://us-central1-plus-care-api.cloudfunctions.net/api";
    private String TAG = "functions";
    private RequestQueue requestQueue;
    private SharedPreferences sharedPreferences;
    private Gson gson = new GsonBuilder().create();

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public FunctionsManager(Context cx, SharedPreferences sharedPref, MyViewModel viewModelInstance){

        requestQueue = Volley.newRequestQueue(cx);
        sharedPreferences = sharedPref;
        viewModel = viewModelInstance;
    }

    public void getUsers(){

        String userUrl = apiUrl + "/utilizador";
        String user_token = sharedPreferences.getString("user_token", "none");

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, userUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Utilizador[] users = gson.fromJson(response, Utilizador[].class);
                            viewModel.setUsers(users);
                            //Log.d(TAG, "Response: " + response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "Error: " + error.toString());
                }
            }) {
                /**
                 * Passing some request headers
                 * */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Bearer " + user_token);
                    return headers;
                }

            };

            // Add the request to the RequestQueue.
            requestQueue.add(stringRequest);
        }
    }

    public void getUtentes(){

        String userUrl = apiUrl + "/utente";
        String user_token = sharedPreferences.getString("user_token", "none");

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, userUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Utente[] utentes = gson.fromJson(response, Utente[].class);
                            viewModel.setUtentes(utentes);
                            //Log.d(TAG, "Response: " + response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "Error: " + error.toString());
                }
            }) {
                /**
                 * Passing some request headers
                 * */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Bearer " + user_token);
                    return headers;
                }

            };

            // Add the request to the RequestQueue.
            requestQueue.add(stringRequest);
        }
    }
}
