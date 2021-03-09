package reitinhaku.tietorakenteet;

import reitinhaku.algoritmit.Solmu;

/**
 * Lista.
 */
public class Lista {

    private int koko = 8;
    private Solmu[] sl;
    private int lisatty;

    public Lista() {
        this.lisatty = 0;
        this.sl = new Solmu[koko];
    }

    public Lista(int koko) {
        this.lisatty = 0;
        this.koko = koko;
        this.sl = new Solmu[koko];
    }

    /**
     * Lisaa lisää tietorakenteeseen Solmun ja int lisatty pitää järjestystä yllä.
     * 
     * @param s Listaan lisättävä Solmu.
     */
    public void lisaa(Solmu s) {
        if (lisatty + 1 > sl.length) {
            kasvata();
        }
        sl[lisatty++] = s;
    }

    /**
     * Tarpeen vaatiessa kopioi ja kasvattaa listan kaksinkertaiseksi.
     */
    public void kasvata() {
        Solmu[] lista2 = new Solmu[sl.length * 2];
        for (int i = 0; i < sl.length; i++) {
            lista2[i] = sl[i];
        }

        this.sl = lista2;
    }

    /**
     * Palauttaa solmun halutulta paikalta i.
     * 
     * @param i
     * @return Solmu
     */
    public Solmu nouda(int i) {
        return sl[i];
    }

    /**
     * Helpompi palauttaa koko rakenne kuin toteuttaa erikseen iterointi koko
     * rakenteen läpi.
     * 
     * @return Solmu[]
     */
    public Solmu[] palauta() {
        return this.sl;
    }

    /**
     * @return String
     */
    public String toString() {
        for (int i = lisatty - 1; i >= 0; i--) {
            System.out.print(sl[i] + ",");
        }
        System.out.println();
        return " ";
    }

    /**
     * @return int
     */
    public int koko() {
        return sl.length;
    }
}
