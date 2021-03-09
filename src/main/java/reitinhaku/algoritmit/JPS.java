package reitinhaku.algoritmit;

/**
 * @referenced JPS by ClintFMullins / https://github.com/ClintFMullins
 */
import java.util.Arrays;

import reitinhaku.kartta.*;
import reitinhaku.tietorakenteet.Keko;
import reitinhaku.tietorakenteet.Lista;

public class JPS {
    private Koordinaatti alku;
    private Koordinaatti maali;
    private Kartta kartta;
    private boolean[][] vierailtu;
    private char[][] taulukko;
    private int[][] hypattavat;
    private int[] apu;
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
     * Haku suoritetaan käyttämällä JPS-algoritmiä.
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

            vieraillut++;
            vierailtu[x][y] = true;

            if (x == maali.getX() && y == maali.getY()) {
                this.loppuSolmu = n;
                return true;
            }
            Lista hyppypisteet = tarkistaNaapurit(n);
            for (int i = 0; i < hyppypisteet.koko(); i++) {
                if (hyppypisteet.nouda(i) != null) {
                    keko.lisaa(hyppypisteet.nouda(i));
                }
            }
        }
        kartta.tulosta(alku + "JPS ei reittiä" + maali);
        return false;

    }

    /**
     * Käy läpi annetun Solmun naapurit ja lisää hyppypisteet palautettavalle
     * listalle.
     * 
     * @param n
     * @return Solmu[]
     */
    public Lista tarkistaNaapurit(Solmu n) {
        Lista hypyt = new Lista();
        hypattavat = tarkistaRajat(n);
        for (int i = 0; i < hypattavat.length; i++) {
            apu = hyppaa(hypattavat[i][0], hypattavat[i][1], n.getKoordinaatti().getX(), n.getKoordinaatti().getY());
            if (apu[0] != -1) {
                int x = apu[0];
                int y = apu[1];
                Solmu k = new Solmu(x, y);
                float f = kartta.linnuntie(k.getKoordinaatti(), n.getKoordinaatti()) + n.getMaalista()
                        + n.getLahdosta();
                if (vierailtu[x][y] || etaisyys[x][y] > f) {
                    k.paivita(kartta.linnuntie(k.getKoordinaatti(), n.getKoordinaatti()) + n.getMaalista(),
                            kartta.linnuntie(k.getKoordinaatti(), maali), n);
                    hypyt.lisaa(k);
                }
            }
        }
        return hypyt;

    }

    /**
     * Hyppy tutkii kartalta sijainteja rekursiivisesti vanhemman suunnasta
     * eteenpäin.
     * 
     * @param x
     * @param y
     * @param vx
     * @param vy
     * @return int[]
     */
    public int[] hyppaa(int x, int y, int vx, int vy) {
        int[] jx = { -1, -1 };
        int[] jy = { -1, -1 };
        int dx = (x - vx) / Math.max(Math.abs(x - vx), 1);
        int dy = (y - vy) / Math.max(Math.abs(y - vy), 1);

        if (!kartta.rajojenSisalla(x, y)) {
            return new int[] { -1, -1 };
        }
        if (x == this.maali.getX() && y == this.maali.getY()) {
            return new int[] { x, y };
        }
        if (dx != 0 && dy != 0) {
            if ((kartta.rajojenSisalla(x - dx, y + dy) && !kartta.rajojenSisalla(x - dx, y))
                    || (kartta.rajojenSisalla(x + dx, y - dy) && !kartta.rajojenSisalla(x, y - dy))) {
                return new int[] { x, y };
            }
        } else {
            if (dx != 0) {
                if ((kartta.rajojenSisalla(x + dx, y + 1) && !kartta.rajojenSisalla(x, y + 1))
                        || (kartta.rajojenSisalla(x + dx, y - 1) && !kartta.rajojenSisalla(x, y - 1))) {
                    return new int[] { x, y };
                }
            } else {
                if ((kartta.rajojenSisalla(x + 1, y + dy) && !kartta.rajojenSisalla(x + 1, y))
                        || (kartta.rajojenSisalla(x - 1, y + dy) && !kartta.rajojenSisalla(x - 1, y))) {
                    return new int[] { x, y };
                }
            }
        }

        if (dx != 0 && dy != 0) {
            jx = hyppaa(x + dx, y, x, y);
            jy = hyppaa(x, y + dy, x, y);
            if (jx[0] != -1 || jy[0] != -1) {
                return new int[] { x, y };
            }
        }
        if (kartta.rajojenSisalla(x + dx, y) || kartta.rajojenSisalla(x, y + dy)) {
            return hyppaa(x + dx, y + dy, x, y);
        } else {
            return new int[] { -1, -1 };

        }
    }

    /**
     * @param n
     * @return int[][]
     */
    public int[][] tarkistaRajat(Solmu n) {
        Solmu vanhempi = n.getVanhempi();
        int x = n.getKoordinaatti().getX();
        int y = n.getKoordinaatti().getY();
        hypattavat = new int[5][2];
        if (vanhempi != null) {
            int vx = vanhempi.getKoordinaatti().getX();
            int vy = vanhempi.getKoordinaatti().getY();
            int dx = (x - vx) / Math.max(Math.abs(x - vx), 1);
            int dy = (y - vy) / Math.max(Math.abs(y - vy), 1);
            if (dx != 0 && dy != 0) {
                if (kartta.rajojenSisalla(x, y + dy)) {
                    hypattavat[0] = (new int[] { x, y + dy });
                }
                if (kartta.rajojenSisalla(x + dx, y)) {
                    hypattavat[1] = (new int[] { x + dx, y });
                }
                if (kartta.rajojenSisalla(x, y + dy) || kartta.rajojenSisalla(x + dx, y)) {
                    hypattavat[2] = (new int[] { x + dx, y + dy });
                }
                if (!kartta.rajojenSisalla(x - dx, y) && kartta.rajojenSisalla(x, y + dy)) {
                    hypattavat[3] = (new int[] { x - dx, y + dy });
                }
                if (!kartta.rajojenSisalla(x, y - dy) && kartta.rajojenSisalla(x + dx, y)) {
                    hypattavat[4] = (new int[] { x + dx, y - dy });
                }
            } else {
                if (dx == 0) {
                    if (kartta.rajojenSisalla(x, y + dy)) {
                        if (kartta.rajojenSisalla(x, y + dy)) {
                            hypattavat[0] = (new int[] { x, y + dy });
                        }
                        if (!kartta.rajojenSisalla(x + 1, y)) {
                            hypattavat[1] = (new int[] { x + 1, y + dy });
                        }
                        if (!kartta.rajojenSisalla(x - 1, y)) {
                            hypattavat[2] = (new int[] { x - 1, y + dy });
                        }
                    }
                } else {
                    if (kartta.rajojenSisalla(x + dx, y)) {
                        if (kartta.rajojenSisalla(x + dx, y)) {
                            hypattavat[0] = (new int[] { x + dx, y });
                        }
                        if (!kartta.rajojenSisalla(x, y + 1)) {
                            hypattavat[1] = (new int[] { x + dx, y + 1 });
                        }
                        if (!kartta.rajojenSisalla(x, y - 1)) {
                            hypattavat[2] = (new int[] { x + dx, y - 1 });
                        }
                    }
                }
            }
        } else {
            return haeNaapurit(n);
        }

        return hypattavat;
    }

    /**
     * HaeNaapurit palauttaa lähtösolmun naapurit joista voidaan hypätä eteenpäin.
     * 
     * @param n
     * @return int[][]
     */
    public int[][] haeNaapurit(Solmu n) {
        int[][] naapurit = new int[8][2];
        int x = n.getKoordinaatti().getX();
        int y = n.getKoordinaatti().getY();
        boolean d0 = false;
        boolean d1 = false;
        boolean d2 = false;
        boolean d3 = false;

        if (kartta.rajojenSisalla(x, y - 1)) {
            naapurit[0] = (new int[] { x, y - 1 });
            d0 = d1 = true;
        }
        if (kartta.rajojenSisalla(x + 1, y)) {
            naapurit[1] = (new int[] { x + 1, y });
            d1 = d2 = true;
        }
        if (kartta.rajojenSisalla(x, y + 1)) {
            naapurit[2] = (new int[] { x, y + 1 });
            d2 = d3 = true;
        }
        if (kartta.rajojenSisalla(x - 1, y)) {
            naapurit[3] = (new int[] { x - 1, y });
            d3 = d0 = true;
        }
        if (d0 && kartta.rajojenSisalla(x - 1, y - 1)) {
            naapurit[4] = (new int[] { x - 1, y - 1 });
        }
        if (d1 && kartta.rajojenSisalla(x + 1, y - 1)) {
            naapurit[5] = (new int[] { x + 1, y - 1 });
        }
        if (d2 && kartta.rajojenSisalla(x + 1, y + 1)) {
            naapurit[6] = (new int[] { x + 1, y + 1 });
        }
        if (d3 && kartta.rajojenSisalla(x - 1, y + 1)) {
            naapurit[7] = (new int[] { x - 1, y + 1 });
        }
        return naapurit;
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
            this.pituus += kartta.linnuntie(s.getKoordinaatti(), s.getVanhempi().getKoordinaatti());
            polku.lisaa(s.getVanhempi());
            kartta.piirraJPS(s.getKoordinaatti().getX(), s.getKoordinaatti().getY(),
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
