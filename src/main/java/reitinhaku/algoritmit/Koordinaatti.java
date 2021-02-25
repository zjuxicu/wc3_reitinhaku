package reitinhaku.algoritmit;

import reitinhaku.tietorakenteet.Lista;

/**
 * Koordinaatti
 * 
 * Tietorakenne, joka pitää kirjaa kartan pisteistä ja reitistä pisteitten
 * välillä.
 */
public class Koordinaatti {

    private int x;
    private int y;
    private Koordinaatti maali;
    private Koordinaatti lahto;
    private Koordinaatti vanhempi;
    private String reitti;
    private double reitinPituus;

    /**
     * Dijkstrassa käytetty Koordinaatti.
     * 
     * @param x
     * @param y
     */
    public Koordinaatti(int x, int y) {
        this.x = x;
        this.y = y;
        reitti = "";
        reitinPituus = 0;
    }

    /**
     * Asettaa A* vertailua varten maalikoordinaatin.
     *
     * @param maali
     */
    public Koordinaatti(Koordinaatti maali) {
        this.maali = maali;
    }

    /**
     * A*-algoritmin käsiteltävien jonoa varten konstruktori, joka tietää mistä
     * Koordinaatista tultiin ja tähänastisen lähdöstä kuljetun matkan.
     * 
     * @param x
     * @param y
     * @param vanhempi
     * @param reitinPituus
     */
    public Koordinaatti(int x, int y, Koordinaatti vanhempi, double reitinPituus) {
        this.x = x;
        this.y = y;
        this.vanhempi = vanhempi;
        this.reitinPituus = reitinPituus;

    }

    /**
     * Koordinaattien naapureiden läpikäymisen käytetty konstruktori, jota Dijkstran
     * algoritmissä hyödynnetään. Katso naapurit().
     * 
     * @param x
     * @param y
     * @param reitti
     */
    public Koordinaatti(int x, int y, String reitti) {
        this.x = x;
        this.y = y;
        this.reitti = reitti;
    }

    /**
     * Tulosta koordinaatin sijainti.
     * 
     * @return String
     */
    public String toString() {
        return "[" + x + "," + y + "]";
    }

    /**
     * Palauttaa Koordinaatin x-arvon.
     * 
     * @return int
     */
    public int getX() {
        return this.x;
    }

    /**
     * Palauttaa Koordinaatin y-arvon.
     * 
     * @return int
     */
    public int getY() {
        return this.y;
    }

    public Koordinaatti getLahto() {
        return this.lahto;
    }

    public Koordinaatti getMaali() {
        return this.maali;
    }

    public Koordinaatti getKoordinaatti() {
        return this;
    }

    public String getReitti() {
        return this.reitti;
    }

    /**
     * Palauttaa Koordinaatin vanhemman.
     * 
     * @return Koordinaatti
     */
    public Koordinaatti getVanhempi() {
        return this.vanhempi;
    }

    /**
     * Palauttaa reitinPituuden lähdöstä kyseiseen Solmuun.
     * 
     * @return double
     */
    public double getReitinPituus() {
        return this.reitinPituus;
    }

    /**
     * Luo kutsuttaessa listan Koordinaatin viereisistä koordinaateista ja lisää
     * reittiin missä suunnassa tämä naapuri sijaitsee.
     * 
     * @return List<Koordinaatti> Koordinaatin viereiset koordinaatit. Ei ota
     *         huomioon onko sijainti mahdollinen, vai ei.
     */
    public Koordinaatti[] naapurit() {
        Lista lista = new Lista();
        lista.lisaa(new Koordinaatti(x + 1, y, reitti + "A "));
        lista.lisaa(new Koordinaatti(x - 1, y, reitti + "Y "));
        lista.lisaa(new Koordinaatti(x, y + 1, reitti + "O "));
        lista.lisaa(new Koordinaatti(x, y - 1, reitti + "V "));
        lista.lisaa(new Koordinaatti(x + 1, y + 1, reitti + "AO "));
        lista.lisaa(new Koordinaatti(x + 1, y - 1, reitti + "AV "));
        lista.lisaa(new Koordinaatti(x - 1, y + 1, reitti + "YO "));
        lista.lisaa(new Koordinaatti(x - 1, y - 1, reitti + "YV "));
        return lista.palauta();
    }

    /**
     * Dijkstran algoritmiä varten toteutettu reitinlaskuri.
     * 
     * @param reitti esim. "AO AO A AV A O O O".
     * @return double samalla esimerkillä tulostuisi ~9.24
     */
    public double laskeReitinPituus(String reitti) {
        if (reitti.length() == 0) {
            return 0;
        }
        double pituus = 0;
        String[] str = reitti.split(" ");
        double apu = Math.sqrt(2);
        for (int i = 0; i < str.length; i++) {
            if (str[i].length() == 1) {
                pituus++;
            } else {
                pituus += apu;
            }
        }

        return pituus;
    }
}
