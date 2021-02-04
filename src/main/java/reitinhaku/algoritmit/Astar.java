package reitinhaku.algoritmit;

import java.util.PriorityQueue;

import reitinhaku.logiikka.Kartta;

public class Astar {

    private Koordinaatti alku;
    private Koordinaatti maali;
    private Kartta kartta;
    private boolean[][] vierailtu;
    private char[][] taulukko;
    private int korkeus;
    private int leveys;
    private double pituus;
    private String reitti;
    private PriorityQueue<Koordinaatti> jono;

    public void alusta(Kartta kartta) {
        this.kartta = kartta;
        this.taulukko = kartta.getTaulukko();
        this.alku = new Koordinaatti(kartta.getAlkuX(), kartta.getAlkuY());
        this.maali = new Koordinaatti(kartta.getMaaliX(), kartta.getMaaliY());
        this.korkeus = taulukko.length;
        this.leveys = taulukko[0].length;
        this.vierailtu = new boolean[korkeus][leveys];
        jono = new PriorityQueue<>(new Koordinaatti(maali));
        jono.add(new Koordinaatti(alku, null, 0));
    }

    public void aloita(Kartta kartta) {
        alusta(kartta);

        if (haku(alku)) {
            System.out.println("Päästiin maaliin!");
            System.out.println("Reitti: " + reitti);
            System.out.println("Reitin pituus: " + pituus);

        } else {
            System.out.println("Reittiä ei löytynyt, kokeile eri arvoja!");
        }
    }

    public boolean haku(Koordinaatti k) {
        while (!jono.isEmpty()) {
            Koordinaatti n = jono.poll();
            char ch = taulukko[n.x][n.y];
            if (ch == '@' || ch == 'O') { // nyt vain out of boundsit rajana
                continue;
            }
            this.reitti = kirjaaReitti(n);
            if (n.x == maali.x && n.y == maali.y) {
                this.pituus = n.reitinPituus;
                return true;
            }

            for (Koordinaatti naapuri : n.naapurit()) {
                if (kartta.rajojenSisalla(naapuri)) {
                    if (vierailtu[naapuri.x][naapuri.y]) {
                        continue;
                    }
                    vierailtu[naapuri.x][naapuri.y] = true;
                    double pituus = n.reitinPituus + kartta.linnuntie(naapuri, n);
                    jono.add(new Koordinaatti(naapuri, n, pituus));
                }
            }
        }
        return false;
    }

    public String kirjaaReitti(Koordinaatti n) {
        if (n.vanhempi == null) {
            return "";
        }
        if (n.x == n.vanhempi.x && n.y < n.vanhempi.y) {
            return reitti + "V ";
        }
        if (n.x == n.vanhempi.x && n.y > n.vanhempi.y) {
            return reitti + "O ";
        }
        if (n.x < n.vanhempi.x && n.y > n.vanhempi.y) {
            return reitti + "YO ";
        }
        if (n.x < n.vanhempi.x && n.y < n.vanhempi.y) {
            return reitti + "YV ";
        }
        if (n.x > n.vanhempi.x && n.y < n.vanhempi.y) {
            return reitti + "AV ";
        }
        if (n.x > n.vanhempi.x && n.y > n.vanhempi.y) {
            return reitti + "AO ";
        }
        if (n.x > n.vanhempi.x && n.y == n.vanhempi.y) {
            return reitti + "A ";
        }
        if (n.x < n.vanhempi.x && n.y == n.vanhempi.y) {
            return reitti + "Y ";
        }

        return reitti;
    }
}
