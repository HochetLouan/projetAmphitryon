package com.example.ap41front;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ap41front.Commande.Commande;
import com.example.ap41front.Commande.Commandes;

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

public class ListeCommande extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste_commande);
        listeCommandes();

        final Button BtnNouvelleCommande = (Button) findViewById(R.id.btnNewCommande);
        BtnNouvelleCommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeCommande.this, NouvelleCommandeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void listeCommandes(){
        OkHttpClient client = new OkHttpClient();
        ArrayList LesIdCommandes = new ArrayList<String>();
        Commandes lesCommandes = new Commandes();
        RequestBody formBody = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url("http://10.0.2.2/Back/controller/ControleurCommandes.php")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {


            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                JSONArray jsonArrayLesCommandes = null;
                try {
                    jsonArrayLesCommandes = new JSONArray(responseStr);
                    for (int i = 0; i < jsonArrayLesCommandes.length(); i++) {
                        JSONObject jsonCommande = jsonArrayLesCommandes.getJSONObject(i);
                        Commande uneCommande = new Commande(jsonCommande.getInt("idCommande"),
                                jsonCommande.getString("EtatCommande"),
                                jsonCommande.getInt("numTable")
                        );
                                lesCommandes.ajouterCommandes(uneCommande);
                    }
                    ListView listViewCommandes = findViewById(R.id.listViewCommandes);
                    List<Commande> commandes = lesCommandes.getCommandes();
                    listViewCommandes.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    runOnUiThread(() -> {
                        listViewCommandes.setAdapter(new CommandeListAdapter(getApplicationContext(), commandes));
                        listViewCommandes.setOnItemClickListener((parent, view, position, id) -> {
                            Commande commandeCliquee = commandes.get(position);
                            Intent intent = new Intent(ListeCommande.this, modificationCommande.class);
                            intent.putExtra("idCommande", commandeCliquee.getId());
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


