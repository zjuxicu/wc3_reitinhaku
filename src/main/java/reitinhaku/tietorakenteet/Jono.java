package reitinhaku.tietorakenteet;

import reitinhaku.algoritmit.Solmu;

/**
 * Dijkstran algoritmin käyttämä jono.
 */
public class Jono {

    private Solmu[] sl;
    private int lisatty;
    private int poistettu;

    /**
     * Jono on tyhjänä hurjan suuri, sillä parametri n on kartan leveys * korkeus.
     * Tämä on kuitenkin tarpeellista mahdollisten pitkien reittien etsimiseen.
     * 
     * @param n
     */
    public Jono(int n) {
        this.sl = new Solmu[n];
        this.poistettu = 0;
        this.lisatty = 0;
    }

    /**
     * Lisää jonon viimeiseksi Solmu s.
     * 
     * @param s
     */
    public void lisaa(Solmu s) {
        sl[lisatty++] = s;
    }

    /**
     * Palauttaa ja poista jonosta ensimmäisen Solmun.
     * 
     * @return Solmu
     */
    public Solmu poll() {
        this.poistettu++;
        return sl[poistettu - 1];
    }

    /**
     * Jono on tyhjä, jos sinne on lisätty ja sieltä on poistettu sama määrä
     * Solmuja.
     * 
     * @return boolean
     */
    public boolean tyhja() {
        return lisatty == poistettu;
    }

}
