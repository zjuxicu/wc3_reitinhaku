package reitinhaku.logiikka;

import reitinhaku.algoritmit.Koordinaatti;

/**
 * Kartta
 * 
 * Pitää sisällään valitun kartan tiedot.
 */

public class Kartta {

    private char[][] taulukko;
    private String nimi;
    private int aX;
    private int aY;
    private int mX;
    private int mY;

    public Kartta(char[][] taulukko, String nimi) {
        this.taulukko = taulukko;
        this.nimi = nimi;
    }

    /**
     * Asettaa kartalle käyttäjän valitseman lähtökoordinaatin.
     * 
     * @param aX Lähdön X-koordinaatti.
     * @param aY Lähdön Y-koordinaatti.
     * @return boolean Palauttaa arvon mikäli asettaminen onnistui.
     */
    public boolean asetaLahto(int aX, int aY) {
        if (aX < taulukko.length && aX >= 0 && aY < taulukko[0].length && aY >= 0) {
            this.aX = aX;
            this.aY = aY;
            return true;
        } else {
            System.out.println(
                    "Arvojen tulee olla väliltä: 0," + (taulukko.length - 1) + " ja 0," + (taulukko[0].length - 1));
            return false;
        }

    }

    /**
     * Asettaa kartalle käyttäjän valitseman maalikoordinaatin.
     * 
     * @param mX Maalin X-koordinaatti.
     * @param mY Maalin Y-koordinaatti.
     * @return boolean Palauttaa arvon mikäli asettaminen onnistui.
     */
    public boolean asetaMaali(int mX, int mY) {
        if (mX < taulukko.length && mX >= 0 && mY < taulukko[0].length && mY >= 0) {
            this.mX = mX;
            this.mY = mY;
            return true;
        } else {
            System.out.println(
                    "Arvojen tulee olla väliltä: 0," + (taulukko.length - 1) + " ja 0," + (taulukko[0].length - 1));
            return false;
        }

    }

    /**
     * Mittaa kahden koordinaatin x-arvojen välisen etäisyyden.
     * 
     * @param a
     * @param b
     * @return double
     */
    public double dX(Koordinaatti a, Koordinaatti b) {
        double x = a.getX() - b.getX();
        return x > 0 ? x : -x;

    }

    /**
     * Mittaa kahden koordinaatin y-arvojen välisen etäisyyden.
     * 
     * @param a
     * @param b
     * @return double
     */
    public double dY(Koordinaatti a, Koordinaatti b) {
        double y = a.getY() - b.getY();
        return y > 0 ? y : -y;
    }

    /**
     * Laskee kahden koordinaatin välisen euklidisen etäisyyden.
     * 
     * @param a
     * @param b
     * @return double Käyttäen Math.sqrt():tä.
     */
    public double linnuntie(Koordinaatti a, Koordinaatti b) {
        double x = dX(a, b);
        double y = dY(a, b);
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Lähtökoordinaatin X-arvon getteri.
     * 
     * @return int
     */
    public int getAlkuX() {
        return this.aX;
    }

    /**
     * Lähtökoordinaatin Y-arvon getteri.
     * 
     * @return int
     */
    public int getAlkuY() {
        return this.aY;
    }

    /**
     * Maalikoordinaatin X-arvon getteri.
     * 
     * @return int
     */
    public int getMaaliX() {
        return this.mX;
    }

    /**
     * Maalikoordinaatin Y-arvon getteri.
     * 
     * @return int
     */
    public int getMaaliY() {
        return this.mY;
    }

    /**
     * Kartan getteri char-taulukkona.
     * 
     * @return char[][]
     */
    public char[][] getTaulukko() {
        return this.taulukko;
    }

    /**
     * Kartan nimen getteri.
     * 
     * @return String
     */
    public String getNimi() {
        return this.nimi;
    }

    /**
     * Tarkistaa onko tutkittava Koordinaatti kartan rajojen sisäpuolella.
     * 
     * @param naatti Tutkittava koordinaatti.
     * @return boolean True, jos kuuluu karttaan, muuten false.
     */
    public boolean rajojenSisalla(Koordinaatti naatti) {
        int x = naatti.getX();
        int y = naatti.getY();

        return x >= 0 && y >= 0 && x < taulukko[0].length && y < taulukko.length;
    }

}
