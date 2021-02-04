package reitinhaku.algoritmit;

import java.util.ArrayDeque;

import reitinhaku.logiikka.Kartta;

public class Dijkstra {
    private Koordinaatti alku;
    private Koordinaatti maali;
    private Kartta kartta;
    private boolean[][] vierailtu;
    private char[][] taulukko;
    private int korkeus;
    private int leveys;
    private double pituus;
    private String reitti;
    private ArrayDeque<Koordinaatti> jono;

    public void alusta(Kartta kartta) {
        this.kartta = kartta;
        this.taulukko = kartta.getTaulukko();
        this.korkeus = taulukko.length;
        this.leveys = taulukko[0].length;
        this.vierailtu = new boolean[korkeus][leveys];
        this.alku = new Koordinaatti(kartta.getAlkuX(), kartta.getAlkuY());
        this.maali = new Koordinaatti(kartta.getMaaliX(), kartta.getMaaliY());
        jono = new ArrayDeque<>();
        jono.add(alku);
        vierailtu[alku.x][alku.y] = true;

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
            if (n.x == maali.x && n.y == maali.y) {
                this.reitti = n.reitti;
                pituus = n.laskeReitinPituus(reitti);
                return true;
            }
            char ch = taulukko[n.x][n.y];
            if (ch == '@' || ch == 'O') { // nyt vain out of boundsit rajana
                continue;
            }
            for (Koordinaatti naapuri : n.naapurit()) {
                tarkistaNaapuri(naapuri);
            }
        }
        return false;
    }

    public void tarkistaNaapuri(Koordinaatti naapuri) {
        if (kartta.rajojenSisalla(naapuri)) {
            if (vierailtu[naapuri.x][naapuri.y]) {
                return;
            }
            jono.add(naapuri);
            vierailtu[naapuri.x][naapuri.y] = true;
        }
    }
}
