package reitinhaku.tietorakenteet;

import reitinhaku.algoritmit.Koordinaatti;

/**
 * Listaus naapureista, koko on aina 8.
 */
public class Lista {

    private int koko = 8;
    private Koordinaatti[] kl;
    private int lisatty;

    public Lista() {
        this.kl = new Koordinaatti[koko];
    }

    /**
     * Lisaa lisää tietorakenteeseen Koordinaatin ja int lisatty pitää järjestystä
     * yllä.
     * 
     * @param koordinaatti Listaan lisättävä koordinaatti.
     */
    public void lisaa(Koordinaatti koordinaatti) {
        kl[lisatty++] = koordinaatti;
    }

    /**
     * Helpompi palauttaa koko rakenne kuin toteuttaa erikseen iterointi koko rakenteen
     * läpi.
     * 
     * @return Koordinaatti[]
     */
    public Koordinaatti[] palauta() {
        return this.kl;
    }

}
