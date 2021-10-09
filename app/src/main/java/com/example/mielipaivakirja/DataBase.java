package com.example.mielipaivakirja;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    public static final String paivakirja_table = "PAIVAKIRJA_TABLE";
    public static final String arviointiKA = "ARVIOINTI_KESKIARVO";
    public static final String COLUMN_MUISTIO = "MUISTIO";
    public static final String DATE = "DATE";

    public DataBase(@Nullable Context context) {
        super(context, "paivakirjat.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTableStatement = "CREATE TABLE " + paivakirja_table + " (" + DATE + " STRING PRIMARY KEY, " +
                arviointiKA + " INT, " + COLUMN_MUISTIO + " STRING)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addData(paivakirja paivakirja){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(arviointiKA, paivakirja.getArviointi());
        cv.put(DATE, paivakirja.getPaivamaara());
        cv.put(COLUMN_MUISTIO, paivakirja.getSaavutukset());

        long insert = db.insert(paivakirja_table, null, cv);
        if(insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public List<paivakirja> tuoPaivakirjat(){
        List<paivakirja> palautus = new ArrayList();

        String query = "SELECT * FROM " + paivakirja_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                String paivamaara = cursor.getString(0);
                int arvioKA = cursor.getInt(1);
                String muistio = cursor.getString(2);
                paivakirja pvkirja = new paivakirja(paivamaara, arvioKA, muistio);
                palautus.add(pvkirja);

            } while(cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return palautus;
    }

    public boolean deletePaivamaara(paivakirja pvkirja){
        SQLiteDatabase db = this.getWritableDatabase();
        String querystring = "DELETE FROM " + paivakirja_table + " WHERE " + DATE + " = " + pvkirja.getPaivamaara();
        Cursor cursor = db.rawQuery(querystring, null);
        if(cursor.moveToFirst()){
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteMuistio(paivakirja pvkirja){
        SQLiteDatabase db = this.getWritableDatabase();
        String uusiMuistio = pvkirja.getSaavutukset();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MUISTIO, uusiMuistio);





        return false;
    }
}
