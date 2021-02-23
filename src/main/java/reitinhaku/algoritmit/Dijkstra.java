package reitinhaku.algoritmit;

import reitinhaku.logiikka.Kartta;
import reitinhaku.tietorakenteet.Jono;

/**
 * Lyhyimmän reitin haku Dijkstran algoritmillä.
 */
public class Dijkstra {
    private Solmu alku;
    private Solmu maali;
    private Kartta kartta;
    private boolean[][] vierailtu;
    private char[][] taulukko;
    private int korkeus;
    private int leveys;
    private double pituus;
    private String reitti;
    private Jono jono;

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
        this.alku = new Solmu(kartta.getAlkuX(), kartta.getAlkuY());
        this.maali = new Solmu(kartta.getMaaliX(), kartta.getMaaliY());
        this.jono = new Jono(korkeus * leveys);
        jono.lisaa(alku);
        vierailtu[alku.getKoordinaatti().getX()][alku.getKoordinaatti().getY()] = true;

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
        while (!jono.tyhja()) {
            Solmu n = jono.poll();
            int x = n.getKoordinaatti().getX();
            int y = n.getKoordinaatti().getY();
            char ch = taulukko[x][y];

            if (ch == '@' || ch == 'O') { // nyt vain out of boundsit rajana
                continue;
            }
            if (x == maali.getKoordinaatti().getX() && y == maali.getKoordinaatti().getY()) {
                this.reitti = n.getKoordinaatti().getReitti();
                pituus = n.laskeReitinPituus(reitti);
                return true;
            }
            for (Koordinaatti naapuri : n.getKoordinaatti().naapurit()) {
                tarkistaNaapuri(naapuri);
            }
        }
        return false;
    }

    /**
     * tarkistaNaapuri tarkistaa onhan annettu parametri kartan rajojen sisäpuolella
     * ja lisää sen tarvittaessa käsiteltävien solmujen tietorakenteeseen.
     * 
     * @param naapuri Käsiteltävä solmu.
     */
    public void tarkistaNaapuri(Koordinaatti naapuri) {
        if (kartta.rajojenSisalla(naapuri)) {
            int nx = naapuri.getX();
            int ny = naapuri.getY();
            if (vierailtu[nx][ny]) {
                return;
            }
            jono.lisaa(new Solmu(naapuri));
            vierailtu[nx][ny] = true;
        }
    }
}
