package com.example.exo6_tp2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ProximityDetector implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private OnProximityListener proximityListener;

    public ProximityDetector(Context context) {
        // Obtenir le gestionnaire de capteurs
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        // Obtenir le capteur de proximité
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    public void registerProximitySensor() {
        // Vérifier si le capteur de proximité est disponible
        if (proximitySensor != null) {
            // S'enregistrer au capteur de proximité avec le gestionnaire de capteurs
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void unregisterProximitySensor() {
        // Se désenregistrer du capteur de proximité lorsque qu'on en a plus besoin
        sensorManager.unregisterListener(this);
    }

    public void setOnProximityListener(OnProximityListener listener) {
        proximityListener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float distance = event.values[0];

            boolean estVisible = false;

            // La valeur de distance indique la proximité de l'objet (en général en centimètres)
            if (distance < proximitySensor.getMaximumRange()) {
                // L'objet est proche
                estVisible = true;
            } else {
                // L'objet est loin
            }

            if (proximityListener != null) {
                proximityListener.onProximityDetected(distance,estVisible);

            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Gestion des changements de précision du capteur
    }

    public interface OnProximityListener {
        void onProximityDetected(float distance, boolean estVisible);
    }
}

