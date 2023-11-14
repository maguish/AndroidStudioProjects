package com.example.exo7;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VideActivity extends Activity {

    private Button btn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vide);

        // On écupére l'Intent qui a démarré l'activité
        Intent intent = getIntent();

        // On extrait les données de l'extra de l'Intent
        String tel = intent.getStringExtra(getResources().getString(R.string.t_l_phone));

        // On récupére le TextViews de la mise en page qui est dans "activity_vide"
        TextView telView = findViewById(R.id.tel_saisi);

        // Affichez les données extraites de l'Intent dans les TextViews
        telView.setText(tel);

        btn = findViewById(R.id.appel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On récupére le numéro de téléphone saisi
                String phoneNumber = telView.getText().toString();

                // On crée un Intent pour l'action d'appel
                // On lance l'application d'appel du téléphone avec 'ACTION_DIAL'
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));

                // On démarre l'appel
                startActivity(callIntent);
            }
        });
    }
}
