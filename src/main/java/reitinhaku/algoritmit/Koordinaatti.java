package reitinhaku.algoritmit;

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
    private float reitinPituus;

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
    public Koordinaatti(int x, int y, Koordinaatti vanhempi, float reitinPituus) {
        this.x = x;
        this.y = y;
        this.vanhempi = vanhempi;
        this.reitinPituus = reitinPituus;

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

}
