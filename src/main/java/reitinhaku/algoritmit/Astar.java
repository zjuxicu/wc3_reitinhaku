package reitinhaku.algoritmit;

import java.util.PriorityQueue;

import reitinhaku.logiikka.Kartta;
import reitinhaku.tietorakenteet.Keko;

/**
 * Lyhyimmän reitin haku A*-algoritmillä.
 */
public class Astar {

    private Koordinaatti alku;
    private Koordinaatti maali;
    private Kartta kartta;
    private boolean[][] vierailtu;
    private char[][] taulukko;
    private int vieraillut;
    private double pituus;
    private String reitti;
    private Keko jono;

    /**
     * Alustaa tarvittavat arvot algoritmin suorittamiseksi.
     * 
     * @param kartta Käyttäjän valitsema kartta.
     */
    public void alusta(Kartta kartta) {
        this.kartta = kartta;
        this.taulukko = kartta.getTaulukko();
        this.vieraillut = 0;
        this.alku = new Koordinaatti(kartta.getAlkuX(), kartta.getAlkuY());
        this.maali = new Koordinaatti(kartta.getMaaliX(), kartta.getMaaliY());
        this.vierailtu = new boolean[taulukko.length][taulukko[0].length];
        jono = new Keko(taulukko.length * taulukko[0].length, new Koordinaatti(maali));
        jono.lisaa(new Koordinaatti(alku.getX(), alku.getY()));
    }

    /**
     * Haku suoritetaan käyttämällä A*-algoritmiä.
     * 
     * @return boolean Palauttaa arvon löytyikö reittiä vai ei.
     */
    public boolean haku() {

        while (!jono.tyhja()) {
            Koordinaatti k = jono.nouda();
            vieraillut++;
            int x = k.getX();
            int y = k.getY();
            this.reitti = kirjaaReitti(k);
            if (x == maali.getX() && y == maali.getY()) {

                this.pituus = k.getReitinPituus();
                return true;
            }

            for (Koordinaatti naapuri : k.naapurit()) {
                if (kartta.rajojenSisalla(naapuri)) {
                    tarkistaNaapuri(naapuri, k);
                }
            }
        }
        return false;
    }

    public void tarkistaNaapuri(Koordinaatti naapuri, Koordinaatti n) {
        int nx = naapuri.getX();
        int ny = naapuri.getY();
        if (vierailtu[nx][ny]) {
            return;
        }
        vierailtu[nx][ny] = true;
        char ch = taulukko[nx][ny];
        if (ch == '@' || ch == 'O') { // nyt vain out of boundsit rajana
            return;
        }
        jono.lisaa(new Koordinaatti(nx, ny, n, (n.getReitinPituus() + kartta.linnuntie(naapuri, n))));
    }

    /**
     * Ylläpitää Koordinaatin "reitti"-arvoa. Metodi kirjaa tarvittavan siirtymän
     * Koordinaatin vanhemmasta.
     * 
     * @param n Koordinaatti, jonka reitti päivitetään.
     * @return String Palauttaa päivitetyn reitin.
     */
    public String kirjaaReitti(Koordinaatti n) {
        Koordinaatti v;
        int nx;
        int ny;
        int vx;
        int vy;
        if (n.getVanhempi() == null) {
            return "";
        } else {
            v = n.getVanhempi();
            nx = n.getX();
            ny = n.getY();
            vx = v.getX();
            vy = v.getY();
        }
        if (nx == vx) {
            if (ny < vy) {
                return reitti + "V ";
            }
            if (ny > vy) {
                return reitti + "O ";
            }
        }

        if (nx < vx) {
            if (ny > vy) {
                return reitti + "YO ";
            }
            if (ny < vy) {
                return reitti + "YV ";
            }
        }
        if (nx > vx) {
            if (ny < vy) {
                return reitti + "AV ";
            }
            if (ny > vy) {
                return reitti + "AO ";
            }
        }

        if (nx > vx) {
            return reitti + "A ";
        }
        if (nx < vx) {
            return reitti + "Y ";
        }

        return reitti;

    }

    public String getReitti() {
        return this.reitti;
    }

    public double getPituus() {
        return this.pituus;
    }

    public int getVieraillut() {
        return this.vieraillut;
    }
}
