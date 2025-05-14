package com.example.ap41front;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String responseStr ;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        final Button buttonValiderAuthentification = findViewById(R.id.buttonValiderAuthentification);
        buttonValiderAuthentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    authentification();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        final Button buttonQuitterAuthentification = (Button) findViewById(R.id.buttonQuitterAuthentification);
        buttonQuitterAuthentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
    }
    public void authentification() throws IOException{
        final EditText textLogin = findViewById(R.id.editTextLogin);
        final EditText textMdp = findViewById(R.id.editTextMdp);


        RequestBody formBody = new FormBody.Builder()
                .add("login", textLogin.getText().toString())
                .add("mdp",  textMdp.getText().toString())
                .build();


        Request request = new Request.Builder()
                .url("http://10.0.2.2/Back/controller/ControleurAuthentification.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public  void onResponse(Call call, Response response) throws IOException {


                responseStr = response.body().string();
                Log.d("Test" , "response http : " + responseStr);

                if (responseStr.compareTo("false")!=0){
                    textLogin.setText("");
                    textMdp.setText("");
                    try {
                        JSONObject utilisateur = new JSONObject(responseStr);
                        Log.d("Test",utilisateur.getString("login") + " est  connecté");
                        if(utilisateur.getString("statut").compareTo("Chef")==0) {
                            Intent intent = new Intent(MainActivity.this, MenuChefCuisineActivity.class);
                            intent.putExtra("utilisateur", utilisateur.toString());
                            startActivity(intent);
                        }
                        else if (utilisateur.getString("statut").compareTo("Serveur")==0){
                            Intent intent = new Intent(MainActivity.this, MenuServeurActivity.class);
                            intent.putExtra("utilisateur", utilisateur.toString());
                            startActivity(intent);
                        }
                        else if (utilisateur.getString("statut").compareTo("Chef-Salle")==0){
                            Intent intent = new Intent(MainActivity.this, MenuChefSalleActivity.class);
                            intent.putExtra("utilisateur", utilisateur.toString());
                            startActivity(intent);
                        }
                    }
                    catch(JSONException e){
                        Log.d("Test", e.getMessage());
                    }
                } else {
                    Log.d("Test","Login ou mot de  passe non valide !");
                }
            }

            public void onFailure(Call call, IOException e)
            {
                Log.d("Test","erreur!!! connexion toujours  impossible" + e.getMessage());
            }

        });
    }
}