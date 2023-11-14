package com.example.exo4_tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView textViewDirection, textViewValues;
    private float THREASHOLD = 5.0f;
    // (à ajuster en fonction de sensibilité de l'appareil)
    // Seuil arbitraire qui détermine à quel point le téléphone doit être incliné
    // pour être considéré comme incliné dans une certaine direction
    String direction = "";

    private final SensorEventListener sensorEvent = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event){

            float x = event.values[0];
            float y = event.values[1];

            if (y > THREASHOLD) {
                direction = "Haut";
            } else if (y < -THREASHOLD) {
                direction = "Bas";
            } else if (x > THREASHOLD) {
                direction = "Gauche";
            } else if (x < -THREASHOLD) {
                direction ="Droite";
            }

            // Mettre à jour les TextViews.
            textViewValues.setText("x: " + x + "\ny: " + y);
            textViewDirection.setText("Direction du mouvement: " + direction);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Gérer les changements de précision du capteur si nécessaire.
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDirection = findViewById(R.id.direction);
        textViewValues = findViewById(R.id.values);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEvent, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEvent);
    }

}