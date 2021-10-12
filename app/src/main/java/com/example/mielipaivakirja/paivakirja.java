package com.example.mielipaivakirja;

import java.util.ArrayList;

public class paivakirja {
    private String paivamaara;
    private int arviointi;
    private String saavutukset;

    public paivakirja(){
    }

    public  paivakirja(String paivamaara, int arviointi, String muistio){
        this.paivamaara = paivamaara;
        this.arviointi = arviointi;
        this.saavutukset = muistio;
    }

    public String toString(){
        return this.paivamaara + "\n arvio: " + getArviointi() + "\n saavutukset: " + getSaavutukset();
    }

    public String getPaivamaara(){
        return this.paivamaara;
    }

    public int getArviointi(){
        return arviointi;
    }

    public String getSaavutukset(){
        return saavutukset;
    }

}
