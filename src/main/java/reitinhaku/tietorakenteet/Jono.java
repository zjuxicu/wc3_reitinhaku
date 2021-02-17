package reitinhaku.tietorakenteet;

import reitinhaku.algoritmit.Koordinaatti;

/**
 * Dijkstran algoritmin käyttämä jono.
 */
public class Jono {

    private Koordinaatti[] kl;
    private int lisatty;
    private int poistettu;

    /**
     * Jono on tyhjänä hurjan suuri, sillä parametri n on kartan leveys * korkeus.
     * Tämä on kuitenkin tarpeellista mahdollisten pitkien reittien etsimiseen.
     * 
     * @param n
     */
    public Jono(int n) {
        this.kl = new Koordinaatti[n];
        this.poistettu = 0;
        this.lisatty = 0;
    }

    /**
     * Lisää jonon viimeiseksi Koordinaatti k.
     * 
     * @param k
     */
    public void lisaa(Koordinaatti k) {
        kl[lisatty++] = k;
    }

    /**
     * Palauttaa ja poista jonosta ensimmäisen Koordinaatti.
     * 
     * @return Koordinaatti
     */
    public Koordinaatti poll() {
        this.poistettu++;
        return kl[poistettu - 1];
    }

    /**
     * Jono on tyhjä, jos sinne on lisätty ja sieltä on poistettu sama määrä
     * Koordinaatteja.
     * 
     * @return boolean
     */
    public boolean tyhja() {
        return lisatty == poistettu;
    }

}
