package com.example.exo7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // On récupère les données des champs de saisie
                EditText nomEdit = findViewById(R.id.modifier_nom);
                EditText pnomEdit = findViewById(R.id.modifier_prenom);
                EditText ageEdit = findViewById(R.id.modifier_age);
                EditText telEdit = findViewById(R.id.modifier_tel);
                EditText competenceEdit = findViewById(R.id.modifier_competence);

                // On récupère le contenu des champs de saisie sous forme de chaînes de caractères
                String nom = nomEdit.getText().toString();
                String prenom = pnomEdit.getText().toString();
                String age = ageEdit.getText().toString();
                String tel = telEdit.getText().toString();
                String competence = competenceEdit.getText().toString();

                // On crée un Intent pour ouvrir la nouvelle activité
                Intent FormIntent = new Intent(MainActivity.this, FormRecapActivity.class);

                // On passez les données récupérées à l'Intent
                FormIntent.putExtra(getResources().getString(R.string.nom), nom);
                FormIntent.putExtra(getResources().getString(R.string.pr_nom), prenom);
                FormIntent.putExtra(getResources().getString(R.string.age), age);
                FormIntent.putExtra(getResources().getString(R.string.t_l_phone), tel);
                FormIntent.putExtra(getResources().getString(R.string.comp_tences), competence);

                // On démarre l'activité
                startActivity(FormIntent);
            }
        });
    }
}