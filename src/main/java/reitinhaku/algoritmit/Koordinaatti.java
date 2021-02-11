package reitinhaku.algoritmit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Koordinaatti
 * 
 * Tietorakenne, joka pitää kirjaa kartan pisteistä ja reitistä pisteitten
 * välillä.
 */
public class Koordinaatti implements Comparator<Koordinaatti> {

    private int x;
    private int y;
    private String reitti;
    private double reitinPituus;
    private Koordinaatti vanhempi;
    private Koordinaatti maali;

    /**
     * Asettaa A* vertailua varten maalikoordinaatin.
     *
     * @param maali
     */
    public Koordinaatti(Koordinaatti maali) {
        this.maali = maali;
    }

    public Koordinaatti(int x, int y) {
        this.x = x;
        this.y = y;
        vanhempi = null;
        reitti = "";
        reitinPituus = 0;
    }

    public Koordinaatti(int x, int y, Koordinaatti vanhempi, double reitinPituus) {
        this.x = x;
        this.y = y;
        this.vanhempi = vanhempi;
        this.reitinPituus = reitinPituus;
    }

    public Koordinaatti(int x, int y, String reitti) {
        this.x = x;
        this.y = y;
        this.reitti = reitti;
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
        String str[] = reitti.split(" ");
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

    /**
     * Luo kutsuttaessa listan Koordinaatin viereisistä koordinaateista ja lisää
     * reittiin missä suunnassa tämä naapuri sijaitsee.
     * 
     * @return List<Koordinaatti> Koordinaatin viereiset koordinaatit. Ei ota
     *         huomioon onko sijainti mahdollinen, vai ei.
     */
    public List<Koordinaatti> naapurit() {
        List<Koordinaatti> naapurit = new ArrayList<>();
        naapurit.add(new Koordinaatti(x + 1, y, reitti + "A "));
        naapurit.add(new Koordinaatti(x - 1, y, reitti + "Y "));
        naapurit.add(new Koordinaatti(x, y + 1, reitti + "O "));
        naapurit.add(new Koordinaatti(x, y - 1, reitti + "V "));
        naapurit.add(new Koordinaatti(x + 1, y + 1, reitti + "AO "));
        naapurit.add(new Koordinaatti(x + 1, y - 1, reitti + "AV "));
        naapurit.add(new Koordinaatti(x - 1, y + 1, reitti + "YO "));
        naapurit.add(new Koordinaatti(x - 1, y - 1, reitti + "YV "));

        return naapurit;
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

    /**
     * Palauttaa Koordinaatin vanhemman.
     * 
     * @return Koordinaatti
     */
    public Koordinaatti getVanhempi() {
        return this.vanhempi;
    }

    /**
     * Palauttaa Reitin.
     * 
     * @return
     */
    public String getReitti() {
        return this.reitti;
    }

    /**
     * Palauttaa reitinPituuden lähdöstä kyseiseen Koordinaattiin.
     * 
     * @return double
     */
    public double getReitinPituus() {
        return this.reitinPituus;
    }

    /**
     * Palauttaa jäljellä olevan etäisyyden arvion maalista.
     * 
     * @param k Maaliin vertailtava koordinaatti.
     * @return double Euklidinen etäisyys.
     */
    public double etaisyysMaalista(Koordinaatti k) {
        double x = k.x - maali.x;
        double absx = x > 0 ? x : -x;
        double y = k.y - maali.y;
        double absy = y > 0 ? y : -y;
        return Math.sqrt(absx * absx + absy * absy);
    }

    /**
     * Toteuttaa Koordinaattien vertailun A*-algoritmiä varten.
     * 
     * @param k1
     * @param k2
     * @return int Kumpi koordinaateista on oletetusti lähempänä maalia.
     */
    @Override
    public int compare(Koordinaatti k1, Koordinaatti k2) {
        if (etaisyysMaalista(k1) > etaisyysMaalista(k2)) {
            return 1;
        }
        if (etaisyysMaalista(k1) < etaisyysMaalista(k2)) {
            return -1;
        }
        return 0;
    }

}
