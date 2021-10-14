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

    /* annetaan String nimille variablet, jotta niitä on helpompi käyttää
     */

    private static final String DATABASE_NAME = "PAIVAKIRJAKOKOELMA.db";
    private static final int DATABASE_VERSION = 1;
    private static final String PAIVAKIRJA_TABLE = "PAIVAKIRJA_TABLE";
    private static final String ID = "ID";
    private static final String PAIVAKIRJA_DATE = "PAIVAKIRJA_DATE";
    private static final String PAIVAKIRJA_ARVIO = "PAIVAKIRJA_ARVIO";
    private static final String PAIVAKIRJA_MUISTIO = "PAIVAKIRJA_MUISTIO";
    private final Context context;

    /*
    Database konstruktori, tarvitaan vain contexti luomiseen, koska nimi, factory ja versio ei muutu
     */
    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /*
    onCreate luo DataBasen ensimäisellä kerralla, kun tätä koodia käytetään
    tässä luodaan DataBasen Tablet, eli luodaan taulukon soluja, jotka ovat liitoksissa toisiin arvoihin
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + PAIVAKIRJA_TABLE +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PAIVAKIRJA_DATE + " TEXT, " +
                PAIVAKIRJA_ARVIO + " DOUBLE, " +
                PAIVAKIRJA_MUISTIO + " TEXT)";
        db.execSQL(createTableStatement);
    }
    /*
    onUpgradea ei tarvitse määritellä tässä projektissa, koska versio ei muutu
    tämä siis päivittäisi Databasen
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        /*db.execSQL("DROP TABLE IF EXISTS " + PAIVAKIRJA_TABLE);
        onCreate(db);*/
    }

    /*
    addData vaatii paivakirja muodossa tietoa

     */
    public boolean addData(paivakirja paivakirja){
        /*
            se ensin chekkaa onko paivakirja tallennettu ennestään jo
         */
        boolean result = checkIfAllreadyExists(paivakirja.getPaiva());
        if(!result){

            /*
            sen jälkeen avataan Database ja lisätään tieto sinne
             */
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(PAIVAKIRJA_DATE, paivakirja.getPaiva());
            cv.put(PAIVAKIRJA_ARVIO, paivakirja.getArvio());
            cv.put(PAIVAKIRJA_MUISTIO, paivakirja.getMuistio());
            long insert = db.insert(PAIVAKIRJA_TABLE, null, cv);
            /*
            pitää muistaa varautua virhetilanteisiin
             */
            return insert != -1;
        } else {
            return false;
        }
    }

    /*
    tämä methodi palauttaa kaikki tiedot DataBasesta paivakirja Listana
     */
    public List<paivakirja> kaikkiPaivakirjat(){
        List<paivakirja> kirjat = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + PAIVAKIRJA_TABLE, null);
        /*
        jos cursorissa on yksi tai useampi osuma niin aletaan muokkaa tietoja paivakirja muotoon
        ja laittaa se kirjat-listaan
         */
        if(cursor.moveToFirst()){
            /*
            do - while: eli suorita tietyt asiat niin kauan kuin while ehto toimii
            tässä tapauksessa niin kauan kuin löytyy seuraava rivit tietoa
             */
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


    /*
    tarkistetaan, että onko tietylle päivälle luotu päiväkirja
     */
    public boolean checkIfAllreadyExists(String date){
        /*
        ensin haetaan kaikki paivakirjat tietokannasta
         */
        List<paivakirja> kaikki = kaikkiPaivakirjat();
        if(kaikki.size() != 0){
            for(int i = 0; i < kaikki.size(); i++){
                /*
                jos tietokannasta löytyy päiväkirjalisäys jonka päivämäärä on sama kuin annettu
                päivämäärä niin palatautetaa tosi arvo
                 */
                if(date.equals(kaikki.get(i).getPaiva())){
                    return true;
                }
            }
        }
        return false;
    }

    /*
    tässä lisätään tietylle päivälle Arvio sarakkeeseen arvio
     */
    public boolean addArvio(String date, double arvio){
        /*
        ensin etsitään tietokannasta tietty päiväkirja, jonka päivämäärä vastaa annettua päivämäärää
         */
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PAIVAKIRJA_ARVIO, arvio);
        Cursor cursor = db.rawQuery("SELECT * FROM " + PAIVAKIRJA_TABLE + " WHERE " + PAIVAKIRJA_DATE + " =?", new String[] {date});
        if(cursor.getCount()>0){
            /*
            löydetyn päivämäärän Arviosarakkeen tieto päivitetään
             */
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
    /*
    samalla logiikalla kuin addArvio methodissa toimiva Muistio sarakkeen päivitys methodi
     */

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
    /*
    tässä koodissa yritetään palauttaa annetun päivämäärän päiväkirja
     */

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

    /*
    ei tarvittu koodi, mutta hakee Databasesta paivakirjan ja poistaa sen
     */

    public boolean deletePaivamaara(paivakirja paivakirja){
        SQLiteDatabase db = this.getWritableDatabase();
        String querystring = "DELETE FROM " + PAIVAKIRJA_TABLE + " WHERE " + PAIVAKIRJA_DATE + " = " + paivakirja.getPaiva();
        Cursor cursor = db.rawQuery(querystring, null);
        return cursor.moveToFirst();
    }

}
