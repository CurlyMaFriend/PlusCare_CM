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
import pedro.gouveia.pluscare_cm.viewModels.MyViewModel;
import pedro.gouveia.pluscare_cm.classes.Higiene;
import pedro.gouveia.pluscare_cm.classes.Medicamento;
import pedro.gouveia.pluscare_cm.classes.Ocorrencia;
import pedro.gouveia.pluscare_cm.classes.Tarefa;
import pedro.gouveia.pluscare_cm.classes.Utente;
import pedro.gouveia.pluscare_cm.classes.Utilizador;

public class FunctionsManager {

    private MyViewModel viewModel;
    private String apiUrl = "https://europe-west1-plus-care-api.cloudfunctions.net/api";
    private String TAG = "functions";
    private RequestQueue requestQueue;
    private SharedPreferences sharedPreferences;
    private Gson gson = new GsonBuilder().create();
    private final String utenteUrl = apiUrl + "/utente";
    private final String utilizadorUrl = apiUrl + "/utilizador";
    private final String tarefasUrl = apiUrl + "/tarefa";
    private final String tarefasFiltrosUrl = apiUrl + "/tarefa/filters";
    private final String adminStatsUrl = apiUrl + "/admin/stats";
    private final String medicamentosUrl = apiUrl + "/medicamento";
    private final String higieneUrl = apiUrl + "/higiene";
    private final String ocorrenciasUrl = apiUrl + "/ocorrencia";

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public FunctionsManager(Context cx, SharedPreferences sharedPref, MyViewModel viewModelInstance){

        requestQueue = Volley.newRequestQueue(cx);
        sharedPreferences = sharedPref;
        viewModel = viewModelInstance;
    }


    /*---------------------------------------------*/
    /*-------------------TAREFAS-------------------*/
    /*---------------------------------------------*/

    /**
     * Obter tarefas associadas a um utente com um determinado estado
     * @param utente_id id do utente cujas tarefas estao associadas
     * @param estado estado == "" -> devolve todas
     *               estado == "completa" -> devolve tarefas terminadas...
     */
    public void getTarefasUtente(String utente_id, String estado){

        String tempBody = "{\"utente_id\": \"" + utente_id + "\"";

        if(estado.equals("")){
            tempBody += "}";
        } else {
            tempBody += ",\"estado\":\"" + estado + "\"}";
        }

        final String reqBody = tempBody;

        String user_token = sharedPreferences.getString("user_token", "none");

        if(!user_token.equals("none") && !utente_id.equals("")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tarefasFiltrosUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Tarefa[] tarefas = gson.fromJson(response, Tarefa[].class);

                            if(estado.equals("completa")){
                                viewModel.setTarefasUtenteComp(tarefas);
                            } else if (estado.equals("por fazer")){
                                viewModel.setTarefasUtenteFaz(tarefas);
                            }

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
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return reqBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", reqBody, "utf-8");
                        return null;
                    }
                }

            };

            Log.d("teste", "req: " + stringRequest);

            // Add the request to the RequestQueue.
            requestQueue.add(stringRequest);
        }
    }

  /*  public void getTarefasFuncionario(String estado){

        String tempBody = "";

        if(!estado.equals("")){
            tempBody += "{\"estado\":\"" + estado + "\"}";
        }

        final String reqBody = tempBody;

        String user_token = sharedPreferences.getString("user_token", "none");

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tarefasFiltrosUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Tarefa[] tarefas = gson.fromJson(response, Tarefa[].class);

                            if(estado.equals("completa")){
                                viewModel.setTarefasUtenteComp(tarefas);
                            } else if (estado.equals("por fazer")){
                                viewModel.setTarefasUtenteFaz(tarefas);
                            }

                            //Log.d(TAG, "Response: " + response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "Error: " + error.toString());
                }
            }) {
                *//**
                 * Passing some request headers
                 * *//*
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Bearer " + user_token);
                    return headers;
                }
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return reqBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", reqBody, "utf-8");
                        return null;
                    }
                }

            };

            Log.d("teste", "req: " + stringRequest);

            // Add the request to the RequestQueue.
            requestQueue.add(stringRequest);
        }
    }*/

    /*----------------------------------------------*/
    /*-----------------MEDICAMENTOS-----------------*/
    /*----------------------------------------------*/

    public void getMedicamentos(){

        String user_token = sharedPreferences.getString("user_token", "none");

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, medicamentosUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Medicamento[] medicamentos = gson.fromJson(response, Medicamento[].class);
                            viewModel.setMedicamentos(medicamentos);
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

    public void addMedicamento(Medicamento med){

        String user_token = sharedPreferences.getString("user_token", "none");

        String medicamentoJson = gson.toJson(med);

        Log.d(TAG, medicamentoJson);

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, medicamentosUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Response add medicamento: " + response);
                            getMedicamentos();
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
                        return medicamentoJson == null ? null : medicamentoJson.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", medicamentoJson, "utf-8");
                        return null;
                    }
                }

            };

            // Add the request to the RequestQueue.
            requestQueue.add(stringRequest);
        }
    }

    public void updateMedicamento(Medicamento med){

        String user_token = sharedPreferences.getString("user_token", "none");

        String medicamentoJson = gson.toJson(med);

        Log.d(TAG, medicamentoJson);

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.PUT, medicamentosUrl+"/"+med.getId(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Response update medicamento: " + response);
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
                        return medicamentoJson == null ? null : medicamentoJson.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", medicamentoJson, "utf-8");
                        return null;
                    }
                }

            };

            // Add the request to the RequestQueue.
            requestQueue.add(stringRequest);
        }
    }

    /*-----------------------------------------*/
    /*-----------------HIGIENE-----------------*/
    /*-----------------------------------------*/

    public void getHigiene(){

        String user_token = sharedPreferences.getString("user_token", "none");

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, higieneUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Higiene[] higienes = gson.fromJson(response, Higiene[].class);
                            viewModel.setHigiene(higienes);
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

    public void addHigiene(Higiene hg){

        String user_token = sharedPreferences.getString("user_token", "none");

        String higieneJson = gson.toJson(hg);

        Log.d(TAG, higieneJson);

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, higieneUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Response add higiene: " + response);
                            getHigiene();
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
                        return higieneJson == null ? null : higieneJson.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", higieneJson, "utf-8");
                        return null;
                    }
                }

            };

            // Add the request to the RequestQueue.
            requestQueue.add(stringRequest);
        }
    }
/*
    public void updateHigiene(Higiene hg){

        String user_token = sharedPreferences.getString("user_token", "none");

        String higieneJson = gson.toJson(hg);

        Log.d(TAG, higieneJson);

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.PUT, higieneUrl+"/"+hg.getId(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Response update higiene: " + response);
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
                *//**
                 * Passing some request headers
                 * *//*
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
                        return higieneJson == null ? null : higieneJson.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", higieneJson, "utf-8");
                        return null;
                    }
                }

            };

            // Add the request to the RequestQueue.
            requestQueue.add(stringRequest);
        }
    }*/

    /*-------------------------------------------------*/
    /*-------------------OCORRENCIAS-------------------*/
    /*-------------------------------------------------*/


    public void getOcorrencias(){

        String user_token = sharedPreferences.getString("user_token", "none");

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, ocorrenciasUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Ocorrencia[] ocorrencias = gson.fromJson(response, Ocorrencia[].class);
                            viewModel.setOcorrencias(ocorrencias);
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

    public void addOcorrencia(Ocorrencia ocorrencia){

        String user_token = sharedPreferences.getString("user_token", "none");

        String ocorrenciaJson = gson.toJson(ocorrencia);

        Log.d(TAG, ocorrenciaJson);

        if(!user_token.equals("none")){

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ocorrenciasUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Response add ocorrencia: " + response);
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
                        return ocorrenciaJson == null ? null : ocorrenciaJson.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", ocorrenciaJson, "utf-8");
                        return null;
                    }
                }

            };

            // Add the request to the RequestQueue.
            requestQueue.add(stringRequest);
        }
    }


    /*---------------------------------------------*/
    /*-------------------UTENTES-------------------*/
    /*---------------------------------------------*/

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

    /*--------------------------------------------*/
    /*----------------UTILIZADORES----------------*/
    /*--------------------------------------------*/

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


    /*------------------------------------------------*/
    /*-------------------ADMINSTATS-------------------*/
    /*------------------------------------------------*/

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
