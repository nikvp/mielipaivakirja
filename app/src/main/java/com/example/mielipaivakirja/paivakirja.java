package com.example.mielipaivakirja;

public class paivakirja {
    /*
    * paivakirja.class helpottaa tietojen tallennusta
    * classi tarvitsee päivamäärän Stringinä, arvion Double ja muistion Stringinä
    * */
    private String paiva;
    private double arvio;
    private String muistio;

    /*
    * Olio vaatii tyhjän konstruktorin, mutta sitä ei tarvitse käyttää
    * */
    public paivakirja(){
    }

    /*
    * konstruktori, joka vaatii kaksi Stringiä ja yhden double arvon
    * */
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

    /*
    * luodaan paivakirja.classin get() methodit
     */
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
