package com.example.exo5_tp2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.Context;

public class ShakeDetector implements SensorEventListener {

    private long lastShakeTime;
    private static final float SHAKE_THRESHOLD = 15.5f;
    // Valeur seuil de secousse (ajuster si nécessaire)
    private static final int SHAKE_TIME_INTERVAL = 1000;
    // Intervalle minimal entre les secousses en millisecondes
    // Au moins 1 seconde devra s'écouler entre deux secousses pour que
    // la deuxième secousse soit prise en compte.

    private final SensorManager sensorManager;
    private OnShakeListener onShakeListener;

    public ShakeDetector(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public interface OnShakeListener {
        default void onShake() {

        }
    }

    public void setOnShakeListener(OnShakeListener listener) {
        onShakeListener = listener;
    }

    public void start() {
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();

            if ((currentTime - lastShakeTime) > SHAKE_TIME_INTERVAL) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(x * x + y * y + z * z);
                if (acceleration > SHAKE_THRESHOLD) {
                    lastShakeTime = currentTime;
                    if (onShakeListener != null) {
                        onShakeListener.onShake();
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Non utilisé ici
    }
}

