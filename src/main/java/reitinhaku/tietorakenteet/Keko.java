package reitinhaku.tietorakenteet;

import reitinhaku.algoritmit.Solmu;

public class Keko {
    /**
     * Keko on minimikeko.
     */
    int koko;
    Solmu[] keko;
    Solmu maali;
    int maaliX;
    int maaliY;
    int vika;

    public Keko(int koko, Solmu maali) {
        this.koko = koko;
        this.maaliX = maali.getKoordinaatti().getX();
        this.maaliY = maali.getKoordinaatti().getY();
        this.maali = maali;
        this.keko = new Solmu[koko];
        vika = 0;
    }

    /**
     * Lisää kekoon Solmun S oikealle paikalleen.
     * 
     * @param s
     */
    public void lisaa(Solmu s) {
        if (vika + 1 >= keko.length) {
            kasvata();
        }
        vika++;
        keko[vika] = s;
        int i = vika;

        while (i > 1 && keko[i].compareTo(keko[vanhempi(i)]) < 0) {
            vaihda(i, vanhempi(i));
            i = vanhempi(i);
        }
    }

    /**
     * Kopioi keon vanhat arvot ja tekee keosta kaksi kertaa suuremman.
     */
    public void kasvata() {
        Solmu[] keko2 = new Solmu[koko * 2];
        for (int i = 0; i < koko; i++) {
            keko2[i] = keko[i];
        }
        this.koko = 2 * koko;
        this.keko = keko2;
    }

    /**
     * Nouda palauttaa keosta ensimmäisen solmun.
     * 
     * @return Solmu
     */
    public Solmu nouda() {
        Solmu k = keko[1];
        keko[1] = keko[vika];
        vika--;
        jarjesta(1);
        return k;

    }

    /**
     * @return boolean
     */
    public boolean tyhja() {
        return vika <= 0;
    }

    /**
     * Vasemman lapsen paikka.
     * 
     * @param i
     * @return int
     */
    public int vasen(int i) {
        if (2 * i > vika) {
            return 0;
        }
        return 2 * i;
    }

    /**
     * Oikean lapsen paikka.
     * 
     * @param i
     * @return int
     */
    public int oikea(int i) {
        if (2 * i + 1 > vika) {
            return 0;
        }
        return 2 * i + 1;
    }

    /**
     * Vanhemman paikka.
     * 
     * @param i
     * @return int
     */
    public int vanhempi(int i) {
        return i / 2;
    }

    /**
     * Vaihtaa kaksi Solmua päittäin keossa.
     * 
     * @param a
     * @param b
     */
    public void vaihda(int a, int b) {
        Solmu k = keko[a];
        keko[a] = keko[b];
        keko[b] = k;
    }

    /**
     * Järjestää keon aina kekoon lisätessä.
     * 
     * @param i
     */
    public void jarjesta(int i) {
        int pienin = -1;
        if (vasen(i) == 0) {
            return;
        }
        if (vasen(i) == vika) {
            pienin = vasen(i);
        } else {
            if (keko[vasen(i)].compareTo(keko[oikea(i)]) < 0) {
                pienin = vasen(i);
            } else {
                pienin = oikea(i);
            }
        }
        if (keko[i].compareTo(keko[pienin]) > 0) {
            vaihda(i, pienin);
            jarjesta(pienin);
        }

    }

    /**
     * @return int
     */
    public int getKoko() {
        return this.koko;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        for (Solmu solmu : keko) {
            System.out.println(solmu);
        }
        return "";
    }
}
