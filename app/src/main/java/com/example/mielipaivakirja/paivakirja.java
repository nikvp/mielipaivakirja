package com.example.mielipaivakirja;

public class paivakirja {
    private String paiva;
    private double arvio;
    private String muistio;

    public paivakirja(){
    }

    public paivakirja(String paiva, double arvio, String muistio){
        this.paiva = paiva;
        this.arvio = arvio;
        this.muistio = muistio;

    }

    @Override
    public String toString() {
        return "paivakirja{" +
                "paiva='" + paiva + '\'' +
                ", arvio=" + arvio +
                ", muistio='" + muistio + '\'' +
                '}';
    }


    public String getPaiva(){
        return this.paiva;
    }

    public double getArvio(){
        return this.arvio;
    }

    public String getMuistio(){
        return this.muistio;
    }


    public void addPaiva(String paiva){
        this.paiva = paiva;
    }

    public void addArvio(double arvio){
        this.arvio = arvio;
    }

    public void addMuistio(String muistio){
        this.muistio = muistio;
    }
}
