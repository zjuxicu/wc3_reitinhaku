package reitinhaku.kayttoliittyma;

import reitinhaku.algoritmit.*;
import reitinhaku.kartta.*;

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
        kartta.nollaaPiirros();
        System.out.println("Etsitään reittiä Dijkstran algoritmillä.");
        System.out.println();
        dijkstra();
        kartta.nollaaPiirros();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Etsitään reittiä A* algoritmillä.");
        System.out.println();
        astar();
        kartta.nollaaPiirros();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Etsitään reittiä JPS algoritmillä.");
        System.out.println();
        jps();
    }

    /**
     * Suorittaa reithinhaun JPS-algoritmillä ja ilmoittaa kuluneen ajan ja muita tilastoja.
     */
    public void jps() {
        JPS jps = new JPS();
        long aloitusAika = System.nanoTime();
        jps.alusta(kartta);
        boolean onnistui = jps.haku();
        long lopetusAika = System.nanoTime();
        long kesto = (lopetusAika - aloitusAika);
        double sekuntit = ((double) kesto / 1000000000);
        System.out.println("Polun haussa kesti JPS:lle " + kesto + "ns ~" + sekuntit + "s");
        if (onnistui) {
            Solmu s = jps.loppuSolmu();
            jps.luoPolku(s);
            kartta.tulosta(kartta.getAlkuX() + "-"+kartta.getAlkuY() + "JPS" + kartta.getAlkuX() + "-"+kartta.getAlkuY());
            System.out.println("Päästiin maaliin!");
            System.out.println("Hyppypisteet: " + jps.getReitti());
            System.out.println("Reitin pituus: " + jps.getPituus());
            System.out.println("JPS kävi läpi haun aikana " + jps.getVieraillut() + " solmua.");

        } else {
            System.out.println("Reittiä ei löytynyt, kokeile eri arvoja!");
        }
    }

    /**
     * Suorittaa reitinhaun Dijkstran algoritmillä ja ilmoittaa kuluneen ajan ja muita tilastoja.
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
            Solmu s = d.loppuSolmu();
            d.luoPolku(s);
            kartta.tulosta(kartta.getAlkuX() + "-"+kartta.getAlkuY() + "Dijkstra" + kartta.getAlkuX() + "-"+kartta.getAlkuY());
            System.out.println("Päästiin maaliin!");
            System.out.println("Reitti: " + d.getReitti());
            System.out.println("Reitin pituus: " + s.getLahdosta());
            System.out.println("Dijkstra kävi läpi haun aikana " + d.getVieraillut() + " solmua.");

        } else {
            System.out.println("Reittiä ei löytynyt, kokeile eri arvoja!");
        }
    }

    /**
     * Suorittaa reitinhaun A* algoritmillä ja ilmoittaa kuluneen ajan ja muita tilastoja.
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
            Solmu s = a.loppuSolmu();
            a.luoPolku(s);
            kartta.tulosta(kartta.getAlkuX() + "-"+kartta.getAlkuY() + "Astar" + kartta.getAlkuX() + "-"+kartta.getAlkuY());
            System.out.println("Päästiin maaliin!");
            System.out.println("Reitti: " + a.getReitti());
            System.out.println("Reitin pituus: " + s.getLahdosta());
            System.out.println("A* kävi läpi haun aikana " + a.getVieraillut() + " solmua.");

        } else {
            System.out.println("Reittiä ei löytynyt, kokeile eri arvoja!");
        }
    }
}