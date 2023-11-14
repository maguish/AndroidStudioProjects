package com.example.exo3_tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private FrameLayout layout;
    private TextView textViewValue;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private double acceleration;

    private SensorEventListener sensorEvent = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event){

            // On récupère les valeurs d'accélération sur les axes x, y et z
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // On utilise les valeurs d'accélération pour définir la couleur du fond d'écran

            // Valeur de l'accélération
            acceleration = Math.sqrt(x * x + y * y + z * z);

            // On détermine la couleur du fond d'écran en fonction de l'accélération

            // On initialise la couleur du fond d'écran
            int backgroundColor = Color.GREEN; // valeurs inférieures : vert

            if (acceleration > 14) {
                // valeurs supérieurs : rouge.
                backgroundColor = Color.RED;
            } else if (acceleration > 9.8) {
                // valeurs moyennes : noir
                backgroundColor = Color.BLACK;
            }

            layout.setBackgroundColor(backgroundColor);

            // Afficher les valeurs de l'accéléromètre
            textViewValue.setText("Accélération : " + acceleration);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Ne rien faire ici
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewValue = findViewById(R.id.valueAcceleration);
        layout = findViewById(R.id.background);

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