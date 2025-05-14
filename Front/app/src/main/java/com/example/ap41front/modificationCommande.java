package com.example.ap41front;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ap41front.ligneCommande.LigneCommande;
import com.example.ap41front.ligneCommande.LignesCommandes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class modificationCommande extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modification_commande);
        listePlats();


    }
    public void listePlats(){
        OkHttpClient client = new OkHttpClient();
        LignesCommandes lignesCommandes = new LignesCommandes();
        int idCommande = getIntent().getIntExtra("idCommande", -1);
        if (idCommande == -1) {
            Log.d("Test" , "idCommande est null");
            finish(); // Ferme l'activité proprement
            return;
        }
        Log.d("Test" , "idCommande est null"+idCommande);
        RequestBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(idCommande))
                .build();

        Request request = new Request.Builder()
                .url("http://10.0.2.2/Back/controller/ControleurPlat.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {


            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                JSONArray jsonArrayLesPlats = null;
                try {
                    jsonArrayLesPlats = new JSONArray(responseStr);
                    for (int i = 0; i < jsonArrayLesPlats.length(); i++) {
                        JSONObject jsonLigneCommande = jsonArrayLesPlats.getJSONObject(i);
                        LigneCommande uneLigneCommande = new LigneCommande(jsonLigneCommande.getInt("idPlat"),
                                jsonLigneCommande.getInt("quantite"),
                                jsonLigneCommande.getString("commentaire"),
                                jsonLigneCommande.getString("etat"),
                                jsonLigneCommande.getString("nomPlat")
                        );
                        Log.d("Test" , "idCommande est null"+uneLigneCommande.getIdPlat());
                        lignesCommandes.ajouterLigneCommandes(uneLigneCommande);
                    }

                    runOnUiThread(() -> {
                        ListView listViewLignecommande = findViewById(R.id.listViewPlats);
                        List<LigneCommande> lignes = lignesCommandes.getLignesCommandes();
                        listViewLignecommande.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        listViewLignecommande.setAdapter(new LigneCommandeListAdapter(modificationCommande.this, lignes));
                        listViewLignecommande.setOnItemClickListener((parent, view, position, id) -> {
                            LigneCommande LigneCommandeCliquee = lignes.get(position);
                            Toast.makeText(modificationCommande.this,
                                    "Commande ID: " + LigneCommandeCliquee.getIdPlat(),
                                    Toast.LENGTH_SHORT).show();

                            // ici tu peux lancer une nouvelle activity ou autre action
                            Intent intent = new Intent(modificationCommande.this, modificationPlat.class);
                            intent.putExtra("LigneCommande", LigneCommandeCliquee.toString());
                            startActivity(intent);
                        });
                    });
                } catch (JSONException e) {
                    Log.d("Test", e.getMessage());
                }
            }

            public void onFailure(Call call, IOException e) {
                Log.d("Test", "erreur!!! commandes introuvables");
            }
        });
    }
}