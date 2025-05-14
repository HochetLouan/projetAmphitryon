package com.example.ap41front;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NouvelleCommandeActivity extends AppCompatActivity {
    Spinner platsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nouvelle_commande);
        remplissageSpinner();

    }
    public void remplissageSpinner(){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://10.0.2.2/Back/controller/ControleurPlatDispo.php")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();

                JSONArray jsonArrayLesPlats = null;
                try {
                    jsonArrayLesPlats = new JSONArray(responseStr);
                    List<String> platsList = new ArrayList<>();
                    platsList.add("plats");

                    for (int i = 0; i < jsonArrayLesPlats.length(); i++) {
                        JSONObject platObject = jsonArrayLesPlats.getJSONObject(i);
                        String nomPlat = platObject.getString("nomPlat");
                        platsList.add(nomPlat);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Spinner platsSpinner = findViewById(R.id.plats);

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                    NouvelleCommandeActivity.this,
                                    android.R.layout.simple_spinner_item,
                                    platsList
                            );
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            platsSpinner.setAdapter(adapter);
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }





            }
            public void onFailure(Call call, IOException e) {
                Log.d("Test", "erreur!!! commandes introuvables");
            }
        });
        Spinner qtePlat = findViewById(R.id.quantitée);
        List<Integer> qteList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            qteList.add(i);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(
                NouvelleCommandeActivity.this,
                android.R.layout.simple_spinner_item,
                qteList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qtePlat.setAdapter(adapter);


        Spinner table = findViewById(R.id.table);
        List<Integer> tableList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            tableList.add(i);
        }
        ArrayAdapter<Integer> adaptertable = new ArrayAdapter<>(
                NouvelleCommandeActivity.this,
                android.R.layout.simple_spinner_item,
                tableList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        table.setAdapter(adaptertable);

    }
}