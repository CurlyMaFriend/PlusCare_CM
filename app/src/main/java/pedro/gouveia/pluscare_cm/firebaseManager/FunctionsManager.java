package pedro.gouveia.pluscare_cm.firebaseManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pedro.gouveia.pluscare_cm.admin.AdminStats;
import pedro.gouveia.pluscare_cm.MyViewModel;
import pedro.gouveia.pluscare_cm.classes.Utente;
import pedro.gouveia.pluscare_cm.classes.Utilizador;

public class FunctionsManager {

    private MyViewModel viewModel;
    private String apiUrl = "https://us-central1-plus-care-api.cloudfunctions.net/api";
    private String TAG = "functions";
    private RequestQueue requestQueue;
    private SharedPreferences sharedPreferences;
    private Gson gson = new GsonBuilder().create();
    private final String utenteUrl = apiUrl + "/utente";
    private final String utilizadorUrl = apiUrl + "/utilizador";
    private final String adminStatsUrl = apiUrl + "/admin/stats";

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public FunctionsManager(Context cx, SharedPreferences sharedPref, MyViewModel viewModelInstance){

        requestQueue = Volley.newRequestQueue(cx);
        sharedPreferences = sharedPref;
        viewModel = viewModelInstance;
    }

    public void getUsers(){

        String user_token = sharedPreferences.getString("user_token", "none");

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, utilizadorUrl,
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

        String user_token = sharedPreferences.getString("user_token", "none");

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, utenteUrl,
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

    public void addUtente(Utente utente){

        String user_token = sharedPreferences.getString("user_token", "none");

        String utenteJson = gson.toJson(utente);

        Log.d(TAG, utenteJson);

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, utenteUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Response add utente: " + response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            String statusCode = String.valueOf(error.networkResponse.statusCode);
                            String body = "";
                            if(error.networkResponse.data!=null) {
                                try {
                                    body = new String(error.networkResponse.data,"UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }

                            Log.d(TAG, "Error - " + statusCode + ": " + body);
                        }
                    }) {
                /**
                 * Passing some request headers
                 * */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Bearer " + user_token);
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return utenteJson == null ? null : utenteJson.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", utenteJson, "utf-8");
                        return null;
                    }
                }

            };

            // Add the request to the RequestQueue.
            requestQueue.add(stringRequest);
        }
    }

    public void addUtilizador(Utilizador user){

        String user_token = sharedPreferences.getString("user_token", "none");

        String userJson = gson.toJson(user);

        Log.d(TAG, userJson);

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, utilizadorUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Response add user: " + response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    String statusCode = "";
                    String body = "";
                    if(error.networkResponse != null){
                        statusCode = String.valueOf(error.networkResponse.statusCode);
                    }
                    if(error.networkResponse.data!=null) {
                        try {
                            body = new String(error.networkResponse.data,"UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    Log.d(TAG, "Error - " + statusCode + ": " + body);
                }
            }) {
                /**
                 * Passing some request headers
                 * */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Bearer " + user_token);
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return userJson == null ? null : userJson.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", userJson, "utf-8");
                        return null;
                    }
                }

            };

            // Add the request to the RequestQueue.
            requestQueue.add(stringRequest);
        }
    }

    public boolean getAdminStats(){

        String user_token = sharedPreferences.getString("user_token", "none");

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, adminStatsUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            AdminStats as = gson.fromJson(response, AdminStats.class);
                            viewModel.setAdminStats(as);
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

            return true;
        } else {
            return false;
        }
    }

}
