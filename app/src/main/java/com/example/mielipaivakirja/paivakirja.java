package com.example.mielipaivakirja;

import java.util.ArrayList;

public class paivakirja {
    private String paivamaara;
    private ArrayList<Integer> arviointi;
    private ArrayList<String> saavutukset;

    public paivakirja(){
    }

    public  paivakirja(String paivamaara, int arviointi, String muistio){
        this.paivamaara = paivamaara;
        this.arviointi.add(arviointi);
        this.saavutukset.add(muistio);
    }

    public String toString(){
        return this.paivamaara + "\n arvio: " + getArviointi() + "\n saavutukset: " + getSaavutukset();
    }

    public String getPaivamaara(){
        return this.paivamaara;
    }

    public int getArviointi(){
        int summa = 0;
        int keskiarvo;
        for(int i = 0; i < arviointi.size(); i++){
            summa += arviointi.get(i);
        }
        keskiarvo = summa / arviointi.size();
        return keskiarvo;
    }

    public String getSaavutukset(){
        String text = null;
        for(int i = 0; i < saavutukset.size(); i++){
            if (i == 0){
                text = saavutukset.get(i);
            } else {
                text += "\n" + saavutukset.get(i);
            }
        }
        return text;
    }

    public void changePaivamaara(String uusiPvm){
        this.paivamaara = uusiPvm;
    }

    public void addArviointi(int uusiarvo){
        this.arviointi.add(uusiarvo);
    }

    public void addSaavutukset(String uusisaavutus){
        this.saavutukset.add(uusisaavutus);
    }

    public void deleteArviointi(int i){
        this.arviointi.remove(i);
    }

    public void deleteSaavutus(int i){
        this.saavutukset.remove(i);
    }
}
