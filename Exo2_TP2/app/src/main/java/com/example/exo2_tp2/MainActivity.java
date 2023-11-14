package com.example.exo2_tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On recupère les éléments de notre page activity_main
        EditText editSensorName = findViewById(R.id.editSensorName);
        TextView result = findViewById(R.id.result);

        // On met un écouteur de clic sur le bouton
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On récupère la saisie de l'utilisateur sous forme de chaine de caractère
                // et en miniscule
                String sensorName = editSensorName.getText().toString().toLowerCase();

                // On récupère une instance du service qui gère les capteurs
                sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

                // On récupère tous les types de capteurs
                Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ALL);

                if (sensor != null && sensor.getName().toLowerCase().contains(sensorName)) {
                    result.setText("Le capteur " + sensorName + " existe sur cet appareil.");
                }else {
                    result.setText("Le capteur " + sensorName + " est indisponible sur cet appareil.");
                }
            }
        });
    }
}