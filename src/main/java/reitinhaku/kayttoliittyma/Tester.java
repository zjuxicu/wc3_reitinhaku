package reitinhaku.kayttoliittyma;

import reitinhaku.algoritmit.Astar;
import reitinhaku.algoritmit.Dijkstra;
import reitinhaku.algoritmit.JPS;
import reitinhaku.algoritmit.Koordinaatti;
import reitinhaku.algoritmit.Solmu;
import reitinhaku.kartta.*;

/**
 * Tester suorittaa eri algoritmien vertailut halutun määrän kertoja ja
 * ilmoittaa tilastoista keskiarvoja.
 */
public class Tester {
    Koordinaatti lahto;
    Koordinaatti maali;
    float[] dijkstraAjatOnnistui;
    float[] dijkstraAjatEi;
    float[] dijkstraSolmut;
    float[] aStarAjatOnnistui;
    float[] aStarAjatEi;
    float[] aStarSolmut;
    float[] jpsAjatOnnistui;
    float[] jpsAjatEi;
    float[] jpsSolmut;
    String[] aStarReitti;
    String[] dijkstraReitti;
    String[] jpsReitti;
    int jpsOnnistui;
    int dOnnistui;
    int aOnnistui;
    int kerrat;
    int dlyhyempi;
    int alyhyempi;
    int jpslyhyempi;

    public Tester(String kartannimi, int kerrat) {
        Kartanlaturi laturi = new Kartanlaturi();
        System.out.println("Arvottu kartta : " + kartannimi);
        long kartanaika = System.nanoTime();
        Kartta kartta = new Kartta(laturi.lataaKartta(kartannimi), kartannimi);
        System.out.println("Kartan lataamiseen kului: " + (long) (System.nanoTime() - kartanaika));
        char[][] taulu = kartta.getTaulukko();
        int rajaX = taulu.length - 1;
        int rajaY = taulu[0].length - 1;
        this.kerrat = kerrat;
        dijkstraAjatOnnistui = new float[kerrat];
        dijkstraAjatEi = new float[kerrat];
        aStarAjatOnnistui = new float[kerrat];
        aStarAjatEi = new float[kerrat];
        dijkstraSolmut = new float[kerrat];
        aStarSolmut = new float[kerrat];
        aStarReitti = new String[kerrat];
        dijkstraReitti = new String[kerrat];
        jpsAjatEi = new float[kerrat];
        jpsAjatOnnistui = new float[kerrat];
        jpsReitti = new String[kerrat];
        jpsSolmut = new float[kerrat];
        jpsOnnistui = 0;
        aOnnistui = 0;
        dOnnistui = 0;
        dlyhyempi = 0;
        alyhyempi = 0;
        jpslyhyempi = 0;

        for (int i = 0; i < kerrat; i++) {
            int xLahto = arvo(rajaX);
            int yLahto = arvo(rajaY);
            while (!kartta.rajojenSisalla(xLahto, yLahto)) {
                xLahto = arvo(rajaX);
                yLahto = arvo(rajaY);
            }
            int xMaali = arvo(rajaX);
            int yMaali = arvo(rajaY);
            while (!kartta.rajojenSisalla(xMaali, yMaali)) {
                xMaali = arvo(rajaX);
                yMaali = arvo(rajaY);
            }
            lahto = new Koordinaatti(xLahto, yLahto);
            maali = new Koordinaatti(xMaali, yMaali);
            kartta.asetaLahto(xLahto, yLahto);
            kartta.asetaMaali(xMaali, yMaali);
            System.out.println("Etsitään reittiä välillä: " + lahto + " -> " + maali);
            float p = kartta.linnuntie(lahto, maali);
            kartta.nollaaPiirros();
            float j = (float) (Math.floor(jpsMittaus(kartta, i) * 1000 + 0.5) / 1000);
            kartta.nollaaPiirros();
            float d = (float) (Math.floor(dijkstraMittaus(kartta, i) * 1000 + 0.5) / 1000);
            kartta.nollaaPiirros();
            float a = (float) (Math.floor(aStarMittaus(kartta, i) * 1000 + 0.5) / 1000);
            System.out.println("Linnuntietä matka on: " + p);
            System.out.println("Dijkstran löytämä reitti: " + d + " solmut: " + dijkstraSolmut[i]);
            System.out.println("A*-algoritmin löytämä reitti: " + a + " solmut: " + aStarSolmut[i]);
            System.out.println("JPS-agoritmin löytämä reitti: " + j + " solmut: " + jpsSolmut[i]);
            if (d < a && d < j) {
                dlyhyempi++;
            }
            if (a < d && a < j) {
                alyhyempi++;
            }
            if (j < d && j < a) {
                jpslyhyempi++;
            }
            System.out.println("------------------------------------------------------------");
        }
        yhteenveto();
    }

    /**
     * Tulostaa yhteenvedon suoritetuista reitinhauista.
     */
    public void yhteenveto() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Reitti löytyi Dijkstralla " + dOnnistui + " kertaa.");
        System.out.println("Keskimäärin Dijkstra tutki " + keskiarvo(dijkstraSolmut) + " solmua.");
        System.out.println("Keskimäärin aikaa kesti haun onnistuessa " + keskiarvo(dijkstraAjatOnnistui) / 1000000000
                + " sekuntia");
        if (kerrat - dOnnistui != 0) {
            System.out.println("Keskimäärin aikaa kesti haun epäonnistuessa " + keskiarvo(dijkstraAjatEi) / 1000000000
                    + " sekuntia");

        }
        System.out.println("Reitti löytyi A*-algorimitllä " + aOnnistui + " kertaa.");
        System.out.println("Keskimäärin A*-algoritmi tutki " + keskiarvo(aStarSolmut) + " solmua.");
        System.out.println(
                "Keskimäärin aikaa kesti haun onnistuessa " + keskiarvo(aStarAjatOnnistui) / 1000000000 + " sekuntia");
        if (kerrat - aOnnistui != 0) {
            System.out.println(
                    "Keskimäärin aikaa kesti haun epäonnistuessa " + keskiarvo(aStarAjatEi) / 1000000000 + " sekuntia");

        }
        System.out.println("Reitti löytyi JPS-algorimitllä " + jpsOnnistui + " kertaa.");
        System.out.println("Keskimäärin JPS-algoritmi tutki " + keskiarvo(jpsSolmut) + " solmua.");
        System.out.println(
                "Keskimäärin aikaa kesti haun onnistuessa " + keskiarvo(jpsAjatOnnistui) / 1000000000 + " sekuntia");
        if (kerrat - jpsOnnistui != 0) {
            System.out.println(
                    "Keskimäärin aikaa kesti haun epäonnistuessa " + keskiarvo(jpsAjatEi) / 1000000000 + " sekuntia");

        }
        if (dlyhyempi > 0) {
            System.out.println("Dijkstra löysi lyhyimmän reitin " + dlyhyempi + " kertaa.");
        }
        if (alyhyempi > 0) {
            System.out.println("A* löysi lyhyimmän reitin " + alyhyempi + " kertaa.");
        }
        if (jpslyhyempi > 0) {
            System.out.println("JPS löysi lyhyimmän reitin " + jpslyhyempi + " kertaa.");
        }
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Suorittaa ja kerää tiedot JPS-hausta.
     * 
     * @param kartta
     * @param i
     * @return float
     */
    private float jpsMittaus(Kartta kartta, int i) {
        JPS j = new JPS();
        long t = System.nanoTime();
        j.alusta(kartta);
        if (j.haku()) {
            t = System.nanoTime() - t;
            jpsOnnistui++;
            jpsAjatOnnistui[i] = t;
            Solmu s = j.loppuSolmu();
            j.luoPolku(s);
            kartta.tulosta(lahto + "JPS" + maali);
        } else {
            t = System.nanoTime() - t;
            jpsAjatEi[i] = t;
        }
        jpsSolmut[i] = j.getVieraillut();
        jpsReitti[i] = j.getReitti();
        return j.getPituus();
    }

    /**
     * Suorittaa ja kerää tiedot Dijkstran hausta.
     * 
     * @param kartta
     * @param i
     * @return float
     */
    private float dijkstraMittaus(Kartta kartta, int i) {
        Dijkstra d = new Dijkstra();
        float pituus = 0;
        long t = System.nanoTime();
        d.alusta(kartta);
        if (d.haku()) {
            t = System.nanoTime() - t;
            dOnnistui++;
            dijkstraAjatOnnistui[i] = t;
            Solmu s = d.loppuSolmu();
            d.luoPolku(s);
            kartta.tulosta(lahto + "Dijkstra" + maali);
            pituus = s.getLahdosta();
        } else {
            t = System.nanoTime() - t;
            dijkstraAjatEi[i] = t;
        }
        dijkstraSolmut[i] = d.getVieraillut();
        dijkstraReitti[i] = d.getReitti();
        return pituus;
    }

    /**
     * Suorittaa ja kerää tiedot A* hausta.
     * 
     * @param kartta
     * @param i
     * @return float
     */
    private float aStarMittaus(Kartta kartta, int i) {
        Astar a = new Astar();
        float pituus = 0;
        long t = System.nanoTime();
        a.alusta(kartta);
        if (a.haku()) {
            t = System.nanoTime() - t;
            aOnnistui++;
            aStarAjatOnnistui[i] = t;
            Solmu s = a.loppuSolmu();
            a.luoPolku(s);
            kartta.tulosta(lahto + "AStar" + maali);
            pituus = s.getLahdosta();
        } else {
            t = System.nanoTime() - t;
            aStarAjatEi[i] = t;

        }
        aStarSolmut[i] = a.getVieraillut();
        aStarReitti[i] = a.getReitti();
        return pituus;
    }

    /**
     * Palauttaa taulukosta keskiarvon vertailua varten.
     * 
     * @param taulu
     * @return float
     */
    private float keskiarvo(float[] taulu) {
        float d = 0;
        for (float l : taulu) {
            d += l;
        }
        return (d / taulu.length);
    }

    /**
     * Arpoo tuloksen joka on väliltä 0 - raja.
     * 
     * @param raja
     * @return int
     */
    private int arvo(int raja) {
        int kello = (int) System.nanoTime();
        int tulos = kello % raja;
        int abstulos = tulos > 0 ? tulos : -tulos;
        if (abstulos >= raja) {
            kello = (int) System.nanoTime();
            arvo(raja);
        }
        return abstulos;
    }
}
