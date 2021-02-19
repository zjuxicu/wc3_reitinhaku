package reitinhaku.algoritmit;

import reitinhaku.tietorakenteet.PrioriteettiJono;

import java.util.PriorityQueue;

import reitinhaku.logiikka.Kartta;

/**
 * Lyhyimmän reitin haku A*-algoritmillä.
 */
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
    private PrioriteettiJono jonno;
    private PriorityQueue<Koordinaatti> jono;

    /**
     * Alustaa tarvittavat arvot algoritmin suorittamiseksi.
     * 
     * @param kartta Käyttäjän valitsema kartta.
     */
    public void alusta(Kartta kartta) {
        this.kartta = kartta;
        this.taulukko = kartta.getTaulukko();
        this.alku = new Koordinaatti(kartta.getAlkuX(), kartta.getAlkuY());
        this.maali = new Koordinaatti(kartta.getMaaliX(), kartta.getMaaliY());
        this.korkeus = taulukko.length;
        this.leveys = taulukko[0].length;
        this.vierailtu = new boolean[korkeus][leveys];
        jono = new PriorityQueue<>(new Koordinaatti(maali));
        jono.add(new Koordinaatti(alku.getX(), alku.getY(), null, 0));
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
     * Haku suoritetaan käyttämällä A*-algoritmiä.
     * 
     * @return boolean Palauttaa arvon löytyikö reittiä vai ei.
     */
    public boolean haku() {
        while (!jono.isEmpty()) {
            Koordinaatti n = jono.poll();
            int x = n.getKoordinaatti().getX();
            int y = n.getKoordinaatti().getY();
            char ch = taulukko[x][y];
            if (ch == '@' || ch == 'O') { // nyt vain out of boundsit rajana
                continue;
            }
            this.reitti = kirjaaReitti(n);
            if (x == maali.getKoordinaatti().getX() && y == maali.getKoordinaatti().getY()) {
                this.pituus = n.getReitinPituus();
                return true;
            }

            for (Koordinaatti naapuri : n.getKoordinaatti().naapurit()) {
                if (kartta.rajojenSisalla(naapuri)) {
                    int nx = naapuri.getX();
                    int ny = naapuri.getY();
                    if (vierailtu[nx][ny]) {
                        continue;
                    }
                    vierailtu[nx][ny] = true;
                    double pituus = n.getReitinPituus() + kartta.linnuntie(naapuri, n.getKoordinaatti());
                    jono.add(new Koordinaatti(nx, ny, n, pituus));
                }
            }
        }
        return false;
    }

    /**
     * Ylläpitää Koordinaatin "reitti"-arvoa. Metodi kirjaa tarvittavan siirtymän
     * Koordinaatin vanhemmasta.
     * 
     * @param n Koordinaatti, jonka reitti päivitetään.
     * @return String Palauttaa päivitetyn reitin.
     */
    public String kirjaaReitti(Koordinaatti n) {
        Koordinaatti v;
        int nx;
        int ny;
        int vx;
        int vy;
        if (n.getVanhempi() == null) {
            return "";
        } else {
            v = n.getVanhempi();
            nx = n.getKoordinaatti().getX();
            ny = n.getKoordinaatti().getY();
            vx = v.getKoordinaatti().getX();
            vy = v.getKoordinaatti().getY();
        }
        if (nx == vx) {
            if (ny < vy) {
                return reitti + "V ";
            }
            if (ny > vy) {
                return reitti + "O ";
            }
        }

        if (nx < vx) {
            if (ny > vy) {
                return reitti + "YO ";
            }
            if (ny < vy) {
                return reitti + "YV ";
            }
        }
        if (nx > vx) {
            if (ny < vy) {
                return reitti + "AV ";
            }
            if (ny > vy) {
                return reitti + "AO ";
            }
        }

        if (nx > vx) {
            return reitti + "A ";
        }
        if (nx < vx) {
            return reitti + "Y ";
        }

        return reitti;

    }
}
