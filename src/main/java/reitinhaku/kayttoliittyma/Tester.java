package reitinhaku.kayttoliittyma;

import reitinhaku.algoritmit.Astar;
import reitinhaku.algoritmit.Dijkstra;
import reitinhaku.algoritmit.Koordinaatti;
import reitinhaku.logiikka.*;
import java.util.Arrays;

public class Tester {
    float[] dijkstraAjatOnnistui;
    float[] dijkstraAjatEi;
    float[] dijkstraSolmut;
    float[] aStarAjatOnnistui;
    float[] aStarAjatEi;
    float[] aStarSolmut;
    int dOnnistui;
    int aOnnistui;

    public Tester(String kartannimi, int kerrat) {
        Kartanlaturi laturi = new Kartanlaturi();
        System.out.println("Arvottu kartta : " + kartannimi);
        Kartta kartta = new Kartta(laturi.lataaKartta(kartannimi), kartannimi);
        char[][] taulu = kartta.getTaulukko();
        int rajaX = taulu.length - 1;
        int rajaY = taulu[0].length - 1;
        dijkstraAjatOnnistui = new float[kerrat];
        dijkstraAjatEi = new float[kerrat];
        aStarAjatOnnistui = new float[kerrat];
        aStarAjatEi = new float[kerrat];
        dijkstraSolmut = new float[kerrat];
        aStarSolmut = new float[kerrat];
        aOnnistui = 0;
        dOnnistui = 0;
        int dlyhyempi = 0;
        int alyhyempi = 0;

        for (int i = 0; i < kerrat; i++) {

            int xLahto = arvo(rajaX);
            int yLahto = arvo(rajaY);
            while (taulu[xLahto][yLahto] == '@' || taulu[xLahto][yLahto] == 'O') {
                xLahto = arvo(rajaX);
                yLahto = arvo(rajaY);
            }
            int xMaali = arvo(rajaX);
            int yMaali = arvo(rajaY);
            while (taulu[xMaali][yMaali] == '@' || taulu[xMaali][yMaali] == 'O') {
                xMaali = arvo(rajaX);
                yMaali = arvo(rajaY);
            }
            Koordinaatti lahto = new Koordinaatti(xLahto, yLahto);
            Koordinaatti maali = new Koordinaatti(xMaali, yMaali);
            kartta.asetaLahto(xLahto, yLahto);
            kartta.asetaMaali(xMaali, yMaali);

            float p = (float) kartta.linnuntie(lahto, maali);
            float d = dijkstraMittaus(kartta, i);
            float a = AstarMittaus(kartta, i);
            if (d != a) {
                System.out.println("Reittien pituudessa eroa välillä: " + lahto + " '" + taulu[xLahto][yLahto] + "' -> "
                        + maali + " '" + taulu[xMaali][yMaali]+ "'");
                System.out.println("Linnuntietä matka on: " + p);
                System.out.println("Dijkstran löytämä reitti: " + d);
                System.out.println("A*-algoritmin löytämä reitti: " + a);
                if (d > a) {
                    System.out.print(" A* löysi lyhyemmän reitin.\n");
                    alyhyempi++;
                } else {
                    System.out.print(" Dijkstra löysi lyheämmän reitin.\n");
                    dlyhyempi++;
                }
            }

        }
        System.out.println("------------------------------------------------------------");
        System.out.println("Reitti löytyi Dijkstralla " + dOnnistui + " kertaa.");
        System.out.println("Keskimäärin Dijkstra tutki " + keskiarvo(dijkstraSolmut) + " solmua.");
        System.out.println("Keskimäärin aikaa kesti haun onnistuessa " + keskiarvo(dijkstraAjatOnnistui) / 1000000000
                + " sekuntia");
        System.out.println(
                "Keskimäärin aikaa kesti haun epäonnistuessa " + keskiarvo(dijkstraAjatEi) / 1000000000 + " sekuntia");
        System.out.println("Reitti löytyi A*-algorimitllä " + aOnnistui + " kertaa.");
        System.out.println("Keskimäärin A*-algoritmi tutki " + keskiarvo(aStarSolmut) + " solmua.");
        System.out.println(
                "Keskimäärin aikaa kesti haun onnistuessa " + keskiarvo(aStarAjatOnnistui) / 1000000000 + " sekuntia");
        System.out.println(
                "Keskimäärin aikaa kesti haun epäonnistuessa " + keskiarvo(aStarAjatEi) / 1000000000 + " sekuntia");
        System.out.println("Dijkstra löysi lyhyemmän reitin " + dlyhyempi + " kertaa.");
        System.out.println("A* löysi lyhyemmän reitin " + alyhyempi + " kertaa.");
        System.out.println("Reitit olivat yhtäpitkät " + (kerrat-dlyhyempi-alyhyempi) + " kertaa.");
    }

    private float dijkstraMittaus(Kartta kartta, int i) {
        Dijkstra d = new Dijkstra();
        long t = System.nanoTime();
        d.alusta(kartta);
        if (d.haku()) {
            dOnnistui++;
            t = System.nanoTime() - t;
            dijkstraAjatOnnistui[i] = t;
        } else {
            t = System.nanoTime() - t;
            dijkstraAjatEi[i] = t;
        }
        dijkstraSolmut[i] = d.getVieraillut();
        return (float) d.getPituus();
    }

    private float AstarMittaus(Kartta kartta, int i) {
        Astar a = new Astar();
        long t = System.nanoTime();
        a.alusta(kartta);
        if (a.haku()) {
            aOnnistui++;
            t = System.nanoTime() - t;
            aStarAjatOnnistui[i] = t;
        } else {
            t = System.nanoTime() - t;
            aStarAjatEi[i] = t;
        }
        aStarSolmut[i] = a.getVieraillut();
        return (float) a.getPituus();
    }

    private double keskiarvo(float[] taulu) {
        double d = 0;
        for (float l : taulu) {
            d += l;
        }
        return (d / taulu.length);
    }

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
