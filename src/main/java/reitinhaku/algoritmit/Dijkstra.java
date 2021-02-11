package reitinhaku.algoritmit;

import java.util.ArrayDeque;

import reitinhaku.logiikka.Kartta;

/**
 * Lyhyimmän reitin haku Dijkstran algoritmillä.
 */
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

    /**
     * Alustaa tarvittavat arvot algoritmin suorittamiseksi.
     * 
     * @param kartta Käyttäjän valitsema kartta.
     */
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
        vierailtu[alku.getX()][alku.getY()] = true;

    }

    /**
     * Algoritmin valitsijan kutsuma metodi, joka ensin alustaa kartan arvot ja
     * suorittaa itse algoritmin ja ilmoittaa käyttäjälle tuloksen.
     * 
     * @param kartta Käyttäjän valitsema kartta.
     */
    public void aloita(Kartta kartta) {
        alusta(kartta);

        if (haku()) {
            System.out.println("Päästiin maaliin!");
            System.out.println("Reitti: " + reitti);
            System.out.println("Reitin pituus: " + pituus);

        } else {
            System.out.println("Reittiä ei löytynyt, kokeile eri arvoja!");
        }
    }

    /**
     * Haku suoritetaan käyttämällä Dijkstran algoritmiä.
     * 
     * @return boolean Palauttaa arvon löytyikö reittiä vai ei.
     */
    public boolean haku() {
        while (!jono.isEmpty()) {
            Koordinaatti n = jono.poll();
            int x = n.getX();
            int y = n.getY();
            char ch = taulukko[x][y];

            if (ch == '@' || ch == 'O') { // nyt vain out of boundsit rajana
                continue;
            }
            if (x == maali.getX() && y == maali.getY()) {
                this.reitti = n.getReitti();
                pituus = n.laskeReitinPituus(reitti);
                return true;
            }

            for (Koordinaatti naapuri : n.naapurit()) {
                tarkistaNaapuri(naapuri);
            }
        }
        return false;
    }

    /**
     * tarkistaNaapuri tarkistaa onhan annettu parametri kartan rajojen sisäpuolella
     * ja lisää sen tarvittaessa käsiteltävien koordinaattien tietorakenteeseen.
     * 
     * @param naapuri Käsiteltävä koordinaatti.
     */
    public void tarkistaNaapuri(Koordinaatti naapuri) {
        if (kartta.rajojenSisalla(naapuri)) {
            int nx = naapuri.getX();
            int ny = naapuri.getY();
            if (vierailtu[nx][ny]) {
                return;
            }
            jono.add(naapuri);
            vierailtu[nx][ny] = true;
        }
    }
}
