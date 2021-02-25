package reitinhaku.kayttoliittyma;

import reitinhaku.algoritmit.*;
import reitinhaku.logiikka.*;

/**
 * AlgoritminValitsija
 * 
 * Osa käyttöliittymää.
 */
public class AlgoritminValitsija {
    private Kartta kartta;

    /**
     * Käyttöliittymä antaa AlgoritminValitsijalle käyttäjän valitseman kartan ja
     * konstruktori käynnistää eri algoritmit.
     * 
     * @param kartta Käyttäjän valitsema kartta.
     */
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

    /**
     * Suorittaa reitinhaun Dijkstran algoritmillä ja ilmoittaa kuluneen ajan.
     */
    public void dijkstra() {
        Dijkstra d = new Dijkstra();
        long aloitusAika = System.nanoTime();
        d.alusta(kartta);
        boolean onnistui = d.haku();
        long lopetusAika = System.nanoTime();
        long kesto = (lopetusAika - aloitusAika);
        double sekuntit = ((double) kesto / 1000000000);
        System.out.println("Polun haussa kesti Dijkstralle " + kesto + "ns ~" + sekuntit + "s");
        if (onnistui) {
            System.out.println("Päästiin maaliin!");
            System.out.println("Reitti: " + d.getReitti());
            System.out.println("Reitin pituus: " + d.getPituus());
            System.out.println("Dijkstra kävi läpi haun aikana " + d.getVieraillut() + " solmua.");

        } else {
            System.out.println("Reittiä ei löytynyt, kokeile eri arvoja!");
        }
    }

    /**
     * Suorittaa reitinhaun A* algoritmillä ja ilmoittaa kuluneen ajan.
     */
    public void astar() {
        Astar a = new Astar();
        long aloitusAika = System.nanoTime();
        a.alusta(kartta);
        boolean onnistui = a.haku();
        long lopetusAika = System.nanoTime();
        long kesto = (lopetusAika - aloitusAika);
        double sekuntit = ((double) kesto / 1000000000);
        System.out.println("Polun haussa kesti A*:lle " + kesto + "ns ~" + sekuntit + "s");
        if (onnistui) {
            System.out.println("Päästiin maaliin!");
            System.out.println("Reitti: " + a.getReitti());
            System.out.println("Reitin pituus: " + a.getPituus());
            System.out.println("A* kävi läpi haun aikana " + a.getVieraillut() + " solmua.");

        } else {
            System.out.println("Reittiä ei löytynyt, kokeile eri arvoja!");
        }
    }
}