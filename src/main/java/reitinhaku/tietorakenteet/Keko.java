package reitinhaku.tietorakenteet;

import reitinhaku.algoritmit.Koordinaatti;

public class Keko {

    int koko;
    Koordinaatti[] jono;
    Koordinaatti maali;
    int maaliX;
    int maaliY;
    int vika;

    public Keko(int koko, Koordinaatti maali) {
        this.koko = koko;
        this.maaliX = maali.getMaali().getX();
        this.maaliY = maali.getMaali().getY();
        this.maali = maali;
        jono = new Koordinaatti[koko];
        vika = 0;
    }

    public void lisaa(Koordinaatti s) {
        if (vika >= koko) {
            System.out.println("Täynnä");
            return;
        }
        vika++;
        jono[vika] = s;
        int i = vika;

        while (i > 1 && vertaa(jono[i], jono[vanhempi(i)]) < 0) {
            vaihda(i, vanhempi(i));
            i = vanhempi(i);
        }
    }

    public Koordinaatti nouda() {
        if (!tyhja()) {
            Koordinaatti k = jono[1];
            jono[1] = jono[vika];
            vika--;
            jarjesta(1);
            return k;
        }
        return null;
    }

    public boolean tyhja() {
        return vika < 0;
    }

    public int vasen(int i) {
        return 2 * i;
    }

    public int oikea(int i) {
        return 2 * i + 1;
    }

    public int vanhempi(int i) {
        return i / 2;
    }

    public void vaihda(int a, int b) {
        Koordinaatti k = jono[a];
        jono[a] = jono[b];
        jono[b] = k;
    }

    public void jarjesta(int i) {
        int pienin = -1;
        if (vasen(i) == 0) {
            return;
        }
        if (vasen(i) == vika) {
            pienin = vasen(i);
        } else {
            if ((vertaa(jono[vasen(i)], jono[oikea(i)]) < 0)) {
                pienin = vasen(i);
            } else {
                pienin = oikea(i);
            }
        }
        if (vertaa(jono[i], jono[pienin]) < 0) {
            vaihda(i, pienin);
            jarjesta(pienin);
        }

    }

    public double etaisyysMaalista(Koordinaatti k) {
        double erox = (double) (k.getX() - maaliX);
        double absx = erox > 0 ? erox : -erox;
        double eroy = (double) (k.getY() - maaliY);
        double absy = eroy > 0 ? eroy : -eroy;
        return Math.sqrt(absx * absx + absy * absy);
    }

    /**
     * Toteuttaa Koordinaattien vertailun A*-algoritmiä varten.
     * 
     * @param k1
     * @param k2
     * @return int Kumpi koordinaateista on oletetusti lähempänä maalia.
     */

    public int vertaa(Koordinaatti k1, Koordinaatti k2) {
        if (k2 == null) {
            return 1;
        }
        if(k1 == null){
            return -1;
        }
        //System.out.println("Verrataan " + k1 + " ja " + k2);
        double etaisyys1 = etaisyysMaalista(k1);
        double etaisyys2 = etaisyysMaalista(k2);

        if (etaisyys1 > etaisyys2) {
            // System.out.println("Voittaja " + k2);
            return 1;
        }
        if (etaisyys1 < etaisyys2) {
            // System.out.println("Voittaja " + k1);
            return -1;
        }
        // System.out.println("Yhtä kaukana");
        return 0;
    }

}
