package com.example.exo6_tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewValue;
    private ImageView objectImageView;
    private ProximityDetector proximityDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewValue = findViewById(R.id.promityValue);
        objectImageView = findViewById(R.id.imageObject);

        // Instancier la classe ProximiteDetector
        proximityDetector = new ProximityDetector(this);
        proximityDetector.setOnProximityListener(new ProximityDetector.OnProximityListener() {
            @Override
            public void onProximityDetected(float distance, boolean estVisible) {
                textViewValue.setText("Distance : " + distance + "cm");
                if (estVisible == true){
                    objectImageView.setVisibility(View.VISIBLE);
                } else {
                    objectImageView.setVisibility(View.INVISIBLE);
                }
            }
        });

        // Appelez registerProximitySensor pour commencer à surveiller la proximité de l'objet
        proximityDetector.registerProximitySensor();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Se desenregistrer du capteur lorsque qu'on en a plus besoin
        proximityDetector.unregisterProximitySensor();
    }

}