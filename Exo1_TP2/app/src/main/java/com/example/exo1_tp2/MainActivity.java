package com.example.exo1_tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On récupère une instance du service qui gère les capteurs
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // On liste tous les capteurs présent sur l'apparail
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        // On récupère pour chaque capteur, son nom, son type et sa version
        List<String> sensorName = new ArrayList<>();
        for (Sensor sensor : sensorList) {
            sensorName.add("Name: " + sensor.getName() + "\r\n"
                    + "Type: " + sensor.getType() + "\r\n"
                    + "Version: " + sensor.getVersion() + "\r\n");
        }

        // On crée un adaptateur pour la liste des
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, sensorName);

        // Associer l'adaptateur au ListView
        ListView resultView = findViewById(R.id.sensorListView);
        resultView.setAdapter(adapter);

    }
}