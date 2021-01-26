package reitinhaku.algoritmit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import reitinhaku.algoritmit.Koordinaatti;
import reitinhaku.kayttoliittyma.Kartta;

public class Dijkstra {
    private Koordinaatti alku;
    private Koordinaatti maali;
    private Kartta kartta;
    private boolean[][] vierailtu;
    private char[][] taulukko;
    private int[][] etaisyys;
    private int korkeus;
    private int leveys;
    private String reitti;

    public void aloita(Kartta kartta) {
        this.kartta = kartta;
        this.taulukko = kartta.getTaulukko();
        this.korkeus = taulukko.length;
        this.leveys = taulukko[0].length;
        this.etaisyys = new int[korkeus][leveys];
        this.vierailtu = new boolean[korkeus][leveys];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                etaisyys[i][j] = min;
            }
        }

        this.alku = new Koordinaatti(kartta.getAlkuX(), kartta.getAlkuY());
        this.maali = new Koordinaatti(kartta.getMaaliX(), kartta.getMaaliY());

        haku(alku);


    }

    public void haku(Koordinaatti k) {
        ArrayDeque<Koordinaatti> jono = new ArrayDeque<>();
        jono.add(k);
        vierailtu[k.x][k.y] = true;
        etaisyys[k.x][k.y] = 0;


        while (!jono.isEmpty()) {
            Koordinaatti n = jono.poll();
            if (n.x == maali.x && n.y == maali.y) {
                System.out.println("Päästiin maaliin");
                this.reitti = n.reitti;
                System.out.println("Reitti: " + reitti);
                System.out.println(n.laskeReitinPituus(reitti));
                return;
            }
            char ch = taulukko[n.x][n.y];
            //System.out.println(ch + " " + n.x + "," + n.y);

            if (ch == '@' || ch == 'O') { // nyt vain out of boundsit rajana
                continue;
            }
            for (Koordinaatti naapuri : n.naapurit()) {
                if (kartta.rajojenSisalla(naapuri)) {
                    if (vierailtu[naapuri.x][naapuri.y]) {
                        continue;
                    }
                    jono.add(naapuri);
                    vierailtu[naapuri.x][naapuri.y] = true;
                }
            }
        }
        System.out.println("Reittiä ei löytynyt");
    }
}
