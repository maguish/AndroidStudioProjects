package com.example.exo7;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FormRecapActivity extends Activity {

    private Button btn1, btn2;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // Récupérez l'Intent qui a démarré l'activité
        Intent intent = getIntent();

        // On extrait les données des extras de l'Intent
        String nom = intent.getStringExtra(getResources().getString(R.string.nom));
        String prenom = intent.getStringExtra(getResources().getString(R.string.pr_nom));
        String age = intent.getStringExtra(getResources().getString(R.string.age));
        String tel = intent.getStringExtra(getResources().getString(R.string.t_l_phone));
        String competence = intent.getStringExtra(getResources().getString(R.string.comp_tences));

        // On récupére les TextViews de la mise en page qui est dans "activity_form"
        TextView nomView = findViewById(R.id.recup_nom);
        TextView pr_nomView = findViewById(R.id.recup_prenom);
        TextView ageView = findViewById(R.id.recup_age);
        TextView telView = findViewById(R.id.recup_tel);
        TextView compView = findViewById(R.id.recup_com);

        // Affichez les données extraites de l'Intent dans les TextViews
        nomView.setText(getResources().getString(R.string.nom)+" : "+nom);
        pr_nomView.setText(getResources().getString(R.string.pr_nom)+" : "+prenom);
        ageView.setText(getResources().getString(R.string.age)+" : "+age);
        telView.setText(getResources().getString(R.string.t_l_phone)+" : "+tel);
        compView.setText(getResources().getString(R.string.comp_tences)+" : "+competence);

        //On récupère l'id des boutons
        btn1 = (Button) findViewById(R.id.ok);
        btn2 = (Button) findViewById(R.id.retour);

        //"Ok" pour valider le formulaire et ouvrir une page vide
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // On crée un Intent pour ouvrir la nouvelle activité
                Intent videIntent = new Intent(FormRecapActivity.this,VideActivity.class);

                // On passez les données récupérées à l'Intent
                videIntent.putExtra(getResources().getString(R.string.t_l_phone), tel);

                // On démarre l'activité
                startActivity(videIntent);

                //Afficher que le formulaire a été envoyé
                Toast.makeText(getApplicationContext(), R.string.form_envoy_avec_succ_s, Toast.LENGTH_LONG).show();
            }
        });

        //"Retour" pour revenir à la page précédente
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}

