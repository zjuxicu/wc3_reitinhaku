package reitinhaku.kayttoliittyma;

import reitinhaku.algoritmit.*;

/**
 * AlgoritminValitsija
 */
public class AlgoritminValitsija {
    private Kartta kartta;

    public AlgoritminValitsija(Kartta kartta) {
        this.kartta = kartta;

        Dijkstra d = new Dijkstra();
        long aloitusAika = System.nanoTime();
        d.aloita(kartta);
        long lopetusAika = System.nanoTime();
        long kesto = (lopetusAika - aloitusAika);
        double sekuntit = ((double) kesto / 1000000000);
        System.out.println("Polun haussa kesti Dijkstralle " + kesto + "ns ~" + sekuntit + "s");

    }

}