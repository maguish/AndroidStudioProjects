package com.example.exo5_tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ShakeDetector.OnShakeListener {
    private CameraManager cameraManager;
    private String cameraId;
    boolean isFlashOn = false;
    private ShakeDetector shakeDetector;

    private int shakeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On initialise le service de gestion du caméra
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            // On récupère l'id du caméra arrière
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        // On instancie la classe ShakeDetector
        shakeDetector = new ShakeDetector(this);
        shakeDetector.setOnShakeListener(this);
    }

    @Override
    public void onShake() {
        shakeCount++;

        // Si shakeCount est impair
        if (shakeCount % 2 == 1) {
            // Première secousse, allumer le flash
            try {
                cameraManager.setTorchMode(cameraId, true);
                isFlashOn = true;
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else { // Si shakeCount est pair
            // Deuxième secousse, éteindre le flash
            try {
                cameraManager.setTorchMode(cameraId, false);
                isFlashOn = false;
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        shakeDetector.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shakeDetector.stop();
    }
}