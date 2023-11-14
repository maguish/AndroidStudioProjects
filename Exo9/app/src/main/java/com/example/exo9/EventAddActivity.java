package com.example.exo9;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exo9.database.Evenement_DB;

import java.util.ArrayList;

public class EventAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // On récupére l'Intent qui a démarré l'activité
        Intent intent = getIntent();

        // On extrait les données des extras de l'Intent
        String selectDate = intent.getStringExtra("Date");

        // Pour obtenir une instance de la base de données
        Evenement_DB dbHelper = new Evenement_DB(this);

        Button btn = findViewById(R.id.save);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On récupére les données des champs de saisie dans "activity_event"
                EditText intituleEdit = findViewById(R.id.intitule);
                EditText debutEdit = findViewById(R.id.debut);
                EditText finEdit = findViewById(R.id.fin);

                // On récupère le contenu des champs de saisie sous forme de chaînes de caractères
                String intitule = intituleEdit.getText().toString();
                String debut = debutEdit.getText().toString();
                String fin = finEdit.getText().toString();

                dbHelper.ajouterEvenement(intitule, debut, fin, selectDate);
                Log.d("EventAddActivity", "Événement ajouté : Intitulé = " + intitule + ", Début = " + debut + ", Fin = " + fin + ", Date = " + selectDate);

                // Renvoyez un résultat à MainActivity
                setResult(RESULT_OK);

                //On revient à l'activité precedente
                finish();
            }
        });

    }
}
