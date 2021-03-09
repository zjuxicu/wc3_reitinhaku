package reitinhaku.algoritmit;

import java.util.Arrays;

import reitinhaku.kartta.Kartta;
import reitinhaku.tietorakenteet.Jono;
import reitinhaku.tietorakenteet.Lista;

/**
 * Lyhyimmän reitin haku Dijkstran algoritmillä.
 */
public class Dijkstra {
    private Solmu alku;
    private Solmu maali;
    private Kartta kartta;
    private boolean[][] vierailtu;
    private char[][] taulukko;
    private float pituus;
    private String reitti;
    private Jono jono;
    private int vieraillut;
    private float[][] etaisyys;
    private float suuri = 999999;
    private Solmu loppuSolmu;

    /**
     * Alustaa tarvittavat arvot algoritmin suorittamiseksi.
     * 
     * @param kartta Käyttäjän valitsema kartta.
     */
    public void alusta(Kartta kartta) {
        this.kartta = kartta;
        this.taulukko = kartta.getTaulukko();
        this.vierailtu = new boolean[taulukko.length][taulukko[0].length];
        this.alku = new Solmu(kartta.getAlkuX(), kartta.getAlkuY());
        this.maali = new Solmu(kartta.getMaaliX(), kartta.getMaaliY());
        this.jono = new Jono(taulukko.length * taulukko[0].length + 1);
        jono.lisaa(alku);
        this.vieraillut = 0;
        etaisyys = new float[taulukko.length][taulukko[0].length];
        for (int i = 0; i < taulukko.length; i++) {
            for (int j = 0; j < taulukko[0].length; j++) {
                etaisyys[i][j] = suuri;
            }
        }
        etaisyys[kartta.getAlkuX()][kartta.getAlkuY()] = 0;
        kartta.piirraAlku(kartta.getAlkuX(), kartta.getAlkuY());
        kartta.piirraMaali(kartta.getMaaliX(), kartta.getMaaliY());
    }

    /**
     * Haku suoritetaan käyttämällä Dijkstran algoritmiä.
     * 
     * @return boolean Palauttaa arvon löytyikö reittiä vai ei.
     */
    public boolean haku() {

        while (!jono.tyhja()) {
            Solmu n = jono.poll();
            int x = n.getKoordinaatti().getX();
            int y = n.getKoordinaatti().getY();
            if (vierailtu[x][y]) {
                continue;
            }
            vieraillut++;
            vierailtu[x][y] = true;
            if (!kartta.rajojenSisalla(x,y)) {
                continue;
            }
            if (x == maali.getKoordinaatti().getX() && y == maali.getKoordinaatti().getY()) {
                this.loppuSolmu = n;
                return true;
            }
            for (Solmu naapuri : n.naapurit()) {
                if (kartta.rajojenSisalla(naapuri.getKoordinaatti())) {
                    tarkistaNaapuri(naapuri, n);
                }
            }
        }
        kartta.tulosta(alku + "Dijkstra ei reittiä" + maali);
        return false;
    }

    /**
     * Käy läpi annetun Solmun naapuri solmut ja lisää ne tarvittaessa
     * tarkasteltavien solmujen jonoon.
     * 
     * @param naapuri
     * @param n
     */
    public void tarkistaNaapuri(Solmu naapuri, Solmu n) {
        int nx = naapuri.getKoordinaatti().getX();
        int ny = naapuri.getKoordinaatti().getY();
        if (vierailtu[nx][ny]) {
            return;
        }

        int liikeX = nx - n.getKoordinaatti().getX();
        int liikeY = ny - n.getKoordinaatti().getY();
        float kuljettu = 0;
        if (liikeX == 0 || liikeY == 0) {
            kuljettu = n.getLahdosta() + 1;
        } else {
            kuljettu = (float) (n.getLahdosta() + 1.414);
        }

        if (kuljettu < etaisyys[nx][ny]) {
            etaisyys[nx][ny] = kuljettu;
            Koordinaatti k = new Koordinaatti(nx, ny);
            Solmu s = new Solmu(k, kuljettu, n);
            jono.lisaa(s);
        }
    }

    /**
     * @return String
     */
    public String getReitti() {
        return this.reitti;
    }

    /**
     * @return float
     */
    public float getPituus() {
        return this.pituus;
    }

    /**
     * @return int
     */
    public int getVieraillut() {
        return this.vieraillut;
    }

    /**
     * Jos reitti maaliin on löytynyt, luoPolku tallentaa ja piirtää kuljetun reitin
     * kartalle.
     * 
     * @param s
     */
    public void luoPolku(Solmu s) {
        Lista polku = new Lista(500);
        polku.lisaa(s);
        this.reitti = taulukko[s.getKoordinaatti().getX()][s.getKoordinaatti().getY()] + "";
        while (s.getVanhempi() != null) {
            this.reitti = reitti + " " + taulukko[s.getKoordinaatti().getX()][s.getKoordinaatti().getY()];
            polku.lisaa(s.getVanhempi());
            kartta.piirraDijkstra(s.getKoordinaatti().getX(), s.getKoordinaatti().getY(),
                    s.getVanhempi().getKoordinaatti().getX(), s.getVanhempi().getKoordinaatti().getY());
            s = s.getVanhempi();
        }

    }

    /**
     * LoppuSolmu on solmu joka on sama kuin maali, jos reitti on löydetty.
     * 
     * @return Solmu
     */
    public Solmu loppuSolmu() {
        if (this.loppuSolmu != null) {
            return loppuSolmu;
        }
        return null;
    }
}