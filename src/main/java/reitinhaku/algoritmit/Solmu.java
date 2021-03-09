package reitinhaku.algoritmit;

import reitinhaku.tietorakenteet.Lista;

public class Solmu implements Comparable<Solmu> {

    private Koordinaatti k;
    private Solmu maali;
    private String reitti;
    private float lahdosta; // Lähtökoordinaatista kuljetun reitin pituus.
    private float maalista;
    private Solmu vanhempi;

    /**
     * Pitää kirjaa reitistä Stringinä ja reitin pituutta doublena.
     * 
     * @param x
     * @param y
     */
    public Solmu(int x, int y) {
        k = new Koordinaatti(x, y);
        reitti = "";
        this.vanhempi = null;
    }

    /**
     * Asettaa A* vertailua varten maalikoordinaatin.
     *
     * @param maali
     */
    public Solmu(Solmu maali) {
        this.maali = maali;
    }

    
    /** 
     * @param lahdosta
     * @param maalista
     * @param vanhempi
     */
    public void paivita(float lahdosta, float maalista, Solmu vanhempi) {
        this.vanhempi = vanhempi;
        this.lahdosta = lahdosta;
        this.maalista = maalista;
    }

    public Solmu(Koordinaatti k, float lahdosta, Solmu vanhempi) {
        this.k = k;
        this.lahdosta = lahdosta;
        this.vanhempi = vanhempi;
    }

    public Solmu(Koordinaatti k) {
        this.k = k;
    }
    
    /** 
     * @return Koordinaatti
     */
    public Koordinaatti getKoordinaatti() {
        return k;
    }

    
    /** 
     * @return Solmu
     */
    public Solmu getVanhempi() {
        return vanhempi;
    }

    
    /** 
     * @return float
     */
    public float getMaalista() {
        return maalista;
    }

    
    /** 
     * @return float
     */
    public float getLahdosta() {
        return lahdosta;
    }

    
    /** 
     * @param o
     * @return int
     */
    @Override
    public int compareTo(Solmu o) {
        if (this.maalista - o.maalista + this.lahdosta - o.lahdosta > 0) {
            return 1;
        }
        if (this.maalista - o.maalista + this.lahdosta - o.lahdosta < 0) {
            return -1;
        }
        return 0;
    }

    /**
     * Luo kutsuttaessa listan Koordinaatin viereisistä koordinaateista ja lisää
     * reittiin missä suunnassa tämä naapuri sijaitsee.
     * 
     * @return List<Koordinaatti> Koordinaatin viereiset koordinaatit. Ei ota
     *         huomioon onko sijainti mahdollinen, vai ei.
     */
    public Solmu[] naapurit() {
        Lista lista = new Lista();
        int x = k.getX();
        int y = k.getY();
        lista.lisaa(new Solmu(x + 1, y));
        lista.lisaa(new Solmu(x - 1, y));
        lista.lisaa(new Solmu(x, y + 1));
        lista.lisaa(new Solmu(x, y - 1));
        lista.lisaa(new Solmu(x + 1, y + 1));
        lista.lisaa(new Solmu(x + 1, y - 1));
        lista.lisaa(new Solmu(x - 1, y + 1));
        lista.lisaa(new Solmu(x - 1, y - 1));
        return lista.palauta();
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return this.k + " ";
    }
}
