package com.example.mielipaivakirja;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PAIVAKIRJAKOKOELMA.db";
    private static final int DATABASE_VERSION = 1;
    private static final String PAIVAKIRJA_TABLE = "PAIVAKIRJA_TABLE";
    private static final String ID = "ID";
    private static final String PAIVAKIRJA_DATE = "PAIVAKIRJA_DATE";
    private static final String PAIVAKIRJA_ARVIO = "PAIVAKIRJA_ARVIO";
    private static final String PAIVAKIRJA_MUISTIO = "PAIVAKIRJA_MUISTIO";
    private final Context context;

    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + PAIVAKIRJA_TABLE +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PAIVAKIRJA_DATE + " TEXT, " +
                PAIVAKIRJA_ARVIO + " DOUBLE, " +
                PAIVAKIRJA_MUISTIO + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        /*db.execSQL("DROP TABLE IF EXISTS " + PAIVAKIRJA_TABLE);
        onCreate(db);*/
    }

    public boolean addData(paivakirja paivakirja){
        boolean result = checkIfAllreadyExists(paivakirja.getPaiva());
        if(!result){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(PAIVAKIRJA_DATE, paivakirja.getPaiva());
            cv.put(PAIVAKIRJA_ARVIO, paivakirja.getArvio());
            cv.put(PAIVAKIRJA_MUISTIO, paivakirja.getMuistio());
            long insert = db.insert(PAIVAKIRJA_TABLE, null, cv);
            return insert != -1;
        } else {
            return false;
        }
    }

    public List<paivakirja> kaikkiPaivakirjat(){
        List<paivakirja> kirjat = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + PAIVAKIRJA_TABLE, null);
        if(cursor.moveToFirst()){
            do {
                String paiva = cursor.getString(1);
                int arvio = cursor.getInt(2);
                String muistio = cursor.getString(3);
                paivakirja uusi = new paivakirja(paiva, arvio, muistio);
                kirjat.add(uusi);
            } while(cursor.moveToNext());
        } else {
            return kirjat;
        }
        return kirjat;
    }

    public boolean checkIfAllreadyExists(String date){
        List<paivakirja> kaikki = kaikkiPaivakirjat();
        if(kaikki.size() != 0){
            for(int i = 0; i < kaikki.size(); i++){
                if(date.equals(kaikki.get(i).getPaiva())){
                    return true;
                }
            }
        }
        return false;
    }

    public paivakirja getPaivakirja(String paiva){
        List<paivakirja> kaikki = kaikkiPaivakirjat();
        if(kaikki.size()!= 0){
            for(int i = 0; i < kaikki.size(); i++){
                if(paiva.equals(kaikki.get(i).getPaiva())){
                    paivakirja palautettava = kaikki.get(i);
                    return palautettava;
                }
            }
        }
        return null;
    }

    public boolean deletePaivamaara(paivakirja paivakirja){
        SQLiteDatabase db = this.getWritableDatabase();
        String querystring = "DELETE FROM " + PAIVAKIRJA_TABLE + " WHERE " + PAIVAKIRJA_DATE + " = " + paivakirja.getPaiva();
        Cursor cursor = db.rawQuery(querystring, null);
        return cursor.moveToFirst();
    }

    public boolean addArvio(String date, double arvio){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PAIVAKIRJA_ARVIO, arvio);
        Cursor cursor = db.rawQuery("SELECT * FROM " + PAIVAKIRJA_TABLE + " WHERE " + PAIVAKIRJA_DATE + " =?", new String[] {date});
        if(cursor.getCount()>0){
            long result = db.update(PAIVAKIRJA_TABLE, cv, PAIVAKIRJA_DATE + " =?", new String[] {date} );
            cursor.close();
            db.close();
            return result != -1;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

    public boolean addMuistio(String date, String muistio){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PAIVAKIRJA_MUISTIO, muistio);
        Cursor cursor = db.rawQuery("SELECT * FROM " + PAIVAKIRJA_TABLE + " WHERE " + PAIVAKIRJA_DATE + " =?", new String[] {date});
        if(cursor.getCount()>0){
            long result = db.update(PAIVAKIRJA_TABLE, cv, PAIVAKIRJA_DATE + " =?", new String[] {date} );
            cursor.close();
            db.close();
            return result != -1;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }
}
