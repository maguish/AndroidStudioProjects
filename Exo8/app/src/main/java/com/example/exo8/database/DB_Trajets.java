package com.example.exo8.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.exo8.data.Trajet;

import java.util.ArrayList;

public class DB_Trajets extends SQLiteOpenHelper {

    // Constructeur
    public DB_Trajets(Context context) {

        super(context, "MaBaseDeDonnees", null, 1);
    }

    // Ici on crée notre table 'Trajets'
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSQL = "CREATE TABLE Trajets (" +
                "ID INTEGER PRIMARY KEY,  " +
                "NomVilleDepart TEXT, " +
                "NomVilleArrivee TEXT, " +
                "Horaire TEXT)";
        db.execSQL(createTableSQL);
    }

    // Ici on met à jour le schéma de la base de données quand elle est modifiée
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            // On supprimme la table de la version précédente
            db.execSQL("DROP TABLE IF EXISTS Trajets");

            // On recrée la table
            onCreate(db);

            // On met à jour le numéro de version de la base de données
            db.setVersion(newVersion);
        }
    }

    // Méthode pour ajouter un nouvel enregistrement dans la table
    public long ajouterTrajet(String depart, String arrive, String horaire) {
        // Pour écrire dans la base de données
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NomVilleDepart", depart);
        values.put("NomVilleArrivee", arrive);
        values.put("Horaire", horaire);

        long newRowId = db.insert("Trajets", null, values);

        // Fermer la base de données après l'insertion
        db.close();

        // Retourne l'ID de la nouvelle ligne insérée
        return newRowId;
    }

    // Méthode pour récupérer les trajets depuis la base de données
    public ArrayList<Trajet> getTrajetsFromDB(String v1, String v2) {

        ArrayList<Trajet> trajets = new ArrayList<>();

        // Pour accéder à la base de données en mode "lecture seule"
        SQLiteDatabase db = this.getReadableDatabase();

        // Requête pour récupérer les trajets depuis la base de données
        String query = "SELECT * FROM Trajets WHERE NomVilleDepart = ? AND NomVilleArrivee = ?";
        String[] args = {v1, v2};
        Cursor c = db.rawQuery(query, args);

        if (c.moveToFirst()) {
            do {
                // Récupérez les données des colonnes en fonction de la structure de votre table "Trajets"
                @SuppressLint("Range") String nomVilleDepart = c.getString(c.getColumnIndex("NomVilleDepart"));
                @SuppressLint("Range") String nomVilleArrivee = c.getString(c.getColumnIndex("NomVilleArrivee"));
                @SuppressLint("Range") String horaire = c.getString(c.getColumnIndex("Horaire"));

                // Créez un objet Trajet avec les données récupérées
                Trajet trajet = new Trajet(nomVilleDepart, nomVilleArrivee, horaire);

                // Ajoutez le trajet à la liste
                trajets.add(trajet);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return trajets;
    }

    //Methodes pour reinitialiser la table
    public void initTrajets() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Requête SQL pour supprimer tous les enregistrements de la table "Trajets"
        db.delete("Trajets", null, null);

        db.close();
    }

}
