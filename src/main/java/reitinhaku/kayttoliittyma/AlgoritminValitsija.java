package reitinhaku.kayttoliittyma;

import reitinhaku.algoritmit.*;
import reitinhaku.logiikka.*;

/**
 * AlgoritminValitsija
 */
public class AlgoritminValitsija {
    private Kartta kartta;

    public AlgoritminValitsija(Kartta kartta) {
        this.kartta = kartta;
        System.out.println("Etsitään reittiä Dijkstran algoritmillä.");
        System.out.println();
        dijkstra();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Etsitään reittiä A* algoritmillä.");
        System.out.println();
        astar();
        System.out.println("--------------------------------------------------------------");

    }

    public void dijkstra() {
        Dijkstra d = new Dijkstra();
        long aloitusAika = System.nanoTime();
        d.aloita(kartta);
        long lopetusAika = System.nanoTime();
        long kesto = (lopetusAika - aloitusAika);
        double sekuntit = ((double) kesto / 1000000000);
        System.out.println("Polun haussa kesti Dijkstralle " + kesto + "ns ~" + sekuntit + "s");
    }
    public void astar() {
        Astar a = new Astar();
        long aloitusAika = System.nanoTime();
        a.aloita(kartta);
        long lopetusAika = System.nanoTime();
        long kesto = (lopetusAika - aloitusAika);
        double sekuntit = ((double) kesto / 1000000000);
        System.out.println("Polun haussa kesti A*:lle " + kesto + "ns ~" + sekuntit + "s");
    }
}