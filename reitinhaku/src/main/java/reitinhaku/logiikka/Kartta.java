package reitinhaku.logiikka;

import reitinhaku.algoritmit.Koordinaatti;
import java.util.Arrays;

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

    public void asetaLahto(int aX, int aY) {
        if (aX < taulukko.length && aX >= 0 && aY < taulukko[0].length && aY >= 0) {
            this.aX = aX;
            this.aY = aY;
        } else {
            System.out.println(
                    "Arvojen tulee olla väliltä: 0," + (taulukko.length - 1) + " ja 0," + (taulukko[0].length - 1));
            System.exit(0);
        }

    }

    public void asetaMaali(int mX, int mY) {
        if (mX < taulukko.length && mX >= 0 && mY < taulukko[0].length && mY >= 0) {
            this.mX = mX;
            this.mY = mY;
        } else {
            System.out.println(
                    "Arvojen tulee olla väliltä: 0," + (taulukko.length - 1) + " ja 0," + (taulukko[0].length - 1));
            System.exit(0);
        }

    }

    public int dX() {
        return Math.abs(aX - mX);
    }

    public int dY() {
        return Math.abs(aY - mY);
    }

    public double linnuntie() {
        return Math.sqrt(dX() * dX() + dY() * dY());
    }

    public int getAlkuX() {
        return this.aX;
    }

    public int getAlkuY() {
        return this.aY;
    }

    public int getMaaliX() {
        return this.mX;
    }

    public int getMaaliY() {
        return this.mY;
    }

    public char[][] getTaulukko() {
        return this.taulukko;
    }

    public String getNimi() {
        return this.nimi;
    }

    public boolean rajojenSisalla(Koordinaatti naatti) {
        int x = naatti.getX();
        int y = naatti.getY();
        if (x >= 0 && y >= 0 && x < taulukko[0].length && y < taulukko.length) {
            return true;
        }
        return false;
    }
    /*
     * public void tulostaKartta() { for (char[] rivi : taulukko) {
     * System.out.println(Arrays.toString(rivi));
     * 
     * } }
     */
}
