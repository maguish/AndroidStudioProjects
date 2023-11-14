package com.example.exo8;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exo8.data.Trajet;
import com.example.exo8.database.DB_Trajets;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // On récupére l'Intent qui a démarré l'activité
        Intent intent = getIntent();

        // On extrait les données des extras de l'Intent
        String ville1 = intent.getStringExtra("Départ");
        String ville2 = intent.getStringExtra("Arrivé");

        // Pour obtenir une instance de la base de données
        DB_Trajets dbHelper = new DB_Trajets(this);
        // Récupérer les données des trajets depuis la base de données
        ArrayList<Trajet> trajets = dbHelper.getTrajetsFromDB(ville1,ville2);

        // Créer un adaptateur pour les trajets
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, trajets);

        // Associer l'adaptateur au ListView
        ListView resultView = findViewById(R.id.result);
        resultView.setAdapter(adapter);

    }
}
