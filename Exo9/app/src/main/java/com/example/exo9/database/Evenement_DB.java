package com.example.exo9.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.exo9.Evenement;

import java.util.ArrayList;

public class Evenement_DB extends SQLiteOpenHelper {
    // Constructeur
    public Evenement_DB(Context context) {
        super(context, "MaBaseDeDonnees", null, 2);
    }

    // Ici on crée notre table 'Evenements'
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSQL = "CREATE TABLE Evenements (ID INTEGER PRIMARY KEY, Intitule TEXT, Debut TEXT, Fin TEXT, Jour TEXT)";
        db.execSQL(createTableSQL);
    }

    // Ici on met à jour le schéma de la base de données quand elle est modifiée
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            // On supprimme la table de la version précédente
            db.execSQL("DROP TABLE IF EXISTS Evenements");

            // On recrée la table
            onCreate(db);

            // On met à jour le numéro de version de la base de données
            db.setVersion(newVersion);
        }
    }

    // Méthode pour ajouter un nouvel enregistrement dans la table
    public long ajouterEvenement(String intitule, String debut, String fin, String jour) {
        // Pour écrire dans la base de données
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Intitule", intitule);
        values.put("Debut", debut);
        values.put("Fin", fin);
        values.put("Jour", jour);

        long newRowId = db.insert("Evenements", null, values);

        // Fermer la base de données après l'insertion
        db.close();

        // Retourne l'ID de la nouvelle ligne insérée
        return newRowId;
    }

    // Méthode pour récupérer les trajets depuis la base de données
    public ArrayList<Evenement> getEventsFromDB(String jour) {

        ArrayList<Evenement> evenements = new ArrayList<>();

        // Pour accéder à la base de données en mode "lecture seule"
        SQLiteDatabase db = this.getReadableDatabase();

        // Requête pour récupérer les trajets depuis la base de données
        String query = "SELECT * FROM Evenements WHERE jour = ?";
        String[] args = {jour};
        Cursor c = db.rawQuery(query, args);

        if (c.moveToFirst()) {
            do {
                // Récupérez les données des colonnes en fonction de la structure de votre table "Trajets"
                @SuppressLint("Range") String intitule = c.getString(c.getColumnIndex("Intitule"));
                @SuppressLint("Range") String debut = c.getString(c.getColumnIndex("Debut"));
                @SuppressLint("Range") String fin = c.getString(c.getColumnIndex("Fin"));

                // Créez un objet Trajet avec les données récupérées
                Evenement ev = new Evenement(intitule, debut, fin, jour);

                // Ajoutez le trajet à la liste
                evenements.add(ev);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return evenements;
    }

    //Methodes pour reinitialiser la table
    public void initEvenements() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Requête SQL pour supprimer tous les enregistrements de la table "Trajets"
        db.delete("Evenements", null, null);

        db.close();
    }

}
