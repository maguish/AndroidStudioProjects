package com.example.exo8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.exo8.data.Trajet;
import com.example.exo8.database.DB_Trajets;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private SQLiteDatabase maDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pour obtenir une instance de la base de données
        DB_Trajets dbHelper = new DB_Trajets(this);

        // On reinitialise la table
        dbHelper.initTrajets();

        // Liste des trajets à ajouter
        ArrayList<Trajet> trajets = new ArrayList<>();
        trajets.add(new Trajet("Montpellier", "Lyon", "06:29 - 08:24"));
        trajets.add(new Trajet("Montpellier", "Lyon", "08:59 - 10:50"));
        trajets.add(new Trajet("Nice", "Lyon", "10:59 - 12:50"));
        trajets.add(new Trajet("Chambery", "Lyon", "15:59 - 17:50"));

        // Ajout des trajets dans la base de données
        for (Trajet t : trajets) {
            dbHelper.ajouterTrajet(t.getDepart(),t.getArrive(),t.getHoraire());
        }

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // On récupère les données des champs de saisie
                EditText departEdit =findViewById(R.id.depart);
                EditText arriveEdit =findViewById(R.id.arrive);

                // On récupère le contenu des champs de saisie sous forme de chaînes de caractères
                String depart = departEdit.getText().toString();
                String arrive = arriveEdit.getText().toString();

                // On crée un Intent pour ouvrir la nouvelle activité
                Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);

                // On passez les données récupérées à l'Intent
                resultIntent.putExtra("Départ",depart);
                resultIntent.putExtra("Arrivé",arrive);

                // On démarre l'activité
                startActivity(resultIntent);

            }
        });
    }
}