package com.example.exo9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import com.example.exo9.database.Evenement_DB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private ListView eventListView;
    private Button btn;
    private String selectedDate;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (selectedDate != null) {
                afficherEvenementsPourDate(selectedDate);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Evenement_DB dbHelper = new Evenement_DB(this);
        dbHelper.initEvenements();

        calendarView = findViewById(R.id.calendar);
        eventListView = findViewById(R.id.eventListView);
        btn = findViewById(R.id.button);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Convertir la date sélectionnée en format "dd/MM/yyyy"
                // +1 car les mois commencent à 0
                selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);

                // Afficher les événements associés à la date sélectionnée.
                afficherEvenementsPourDate(selectedDate);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedDate != null) {
                    // Si une date a été sélectionnée, utilisez-la.
                    Intent eventIntent = new Intent(MainActivity.this, EventAddActivity.class);
                    eventIntent.putExtra("Date", selectedDate);
                    startActivity(eventIntent);
                } else {
                    // Si aucune date n'a été sélectionnée, utilisez la date actuelle.
                    Calendar calendar = Calendar.getInstance();
                    selectedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.getTime());
                    Intent eventIntent = new Intent(MainActivity.this, EventAddActivity.class);
                    eventIntent.putExtra("Date", selectedDate);
                    startActivity(eventIntent);
                }
            }
        });

    }

    private void afficherEvenementsPourDate(String dateSelect) {
        Evenement_DB dbHelper = new Evenement_DB(this);

        ArrayList<Evenement> evenements = dbHelper.getEventsFromDB(dateSelect);
        Log.d("MainActivity", "Événements récupérés pour la date " + dateSelect + ": " + evenements);

        // Créez un adaptateur pour afficher les événements dans le ListView.
        ArrayAdapter<Evenement> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, evenements);

        // Associez l'adaptateur au ListView.
        eventListView.setAdapter(adapter);

        // Notifiez à l'adaptateur que les données ont été modifiées.
        adapter.notifyDataSetChanged();
    }
}