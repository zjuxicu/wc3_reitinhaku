package reitinhaku.algoritmit;

import reitinhaku.kartta.Kartta;
import reitinhaku.tietorakenteet.Keko;
import reitinhaku.tietorakenteet.Lista;

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
    private float pituus;
    private String reitti;
    private Keko keko;
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
        this.vieraillut = 0;
        this.alku = new Koordinaatti(kartta.getAlkuX(), kartta.getAlkuY());
        this.maali = new Koordinaatti(kartta.getMaaliX(), kartta.getMaaliY());
        this.vierailtu = new boolean[taulukko.length][taulukko[0].length];
        keko = new Keko(taulukko.length * taulukko[0].length, new Solmu(maali));
        Solmu s = new Solmu(alku, 0, null);
        keko.lisaa(s);
        etaisyys = new float[taulukko.length][taulukko[0].length];

        for (int i = 0; i < taulukko.length; i++) {
            for (int j = 0; j < taulukko[0].length; j++) {
                etaisyys[i][j] = suuri;
            }
        }
        etaisyys[kartta.getAlkuX()][kartta.getAlkuY()] = 0;
        kartta.piirraAlku(alku.getX(), alku.getY());
        kartta.piirraMaali(maali.getX(), maali.getY());
    }

    /**
     * Haku suoritetaan käyttämällä A*-algoritmiä.
     * 
     * @return boolean Palauttaa arvon löytyikö reittiä vai ei.
     */
    public boolean haku() {
        while (!keko.tyhja()) {
            Solmu n = keko.nouda();
            int x = n.getKoordinaatti().getX();
            int y = n.getKoordinaatti().getY();
            if (vierailtu[x][y]) {
                continue;
            }
            if (!kartta.rajojenSisalla(x, y)) {
                continue;
            }
            vieraillut++;
            vierailtu[x][y] = true;

            if (x == maali.getX() && y == maali.getY()) {
                this.loppuSolmu = n;
                return true;
            }
            tarkistaNaapurit(n);

        }
        kartta.tulosta(alku + "Astar ei reittiä" + maali);
        return false;
    }

    /**
     * Käy läpi annetun Solmun naapuri solmut ja lisää ne tarvittaessa
     * tarkasteltavien solmujen kekoon.
     * 
     * @param n
     */
    public void tarkistaNaapurit(Solmu n) {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }
                int uusiX = n.getKoordinaatti().getX() + x;
                int uusiY = n.getKoordinaatti().getY() + y;
                if (!kartta.rajojenSisalla(uusiX, uusiY)) {
                    continue;
                }
                float kuljettu;
                int absX = uusiX > 0 ? uusiX : -uusiX;
                int absY = uusiY > 0 ? uusiY : -uusiY;
                if (absX + absY == 1) {
                    kuljettu = etaisyys[n.getKoordinaatti().getX()][n.getKoordinaatti().getY()] + 1;
                } else {
                    kuljettu = (float) (etaisyys[n.getKoordinaatti().getX()][n.getKoordinaatti().getY()] + 1.414);
                }
                if (kuljettu < etaisyys[uusiX][uusiY]) {
                    etaisyys[uusiX][uusiY] = kuljettu;
                    Koordinaatti k = new Koordinaatti(uusiX, uusiY);
                    Solmu s = new Solmu(k, kuljettu + kartta.linnuntie(k, maali), n);
                    keko.lisaa(s);
                }
            }
        }

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
            kartta.piirraAStar(s.getKoordinaatti().getX(), s.getKoordinaatti().getY(),
                    s.getVanhempi().getKoordinaatti().getX(), s.getVanhempi().getKoordinaatti().getY());
            s = s.getVanhempi();
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
