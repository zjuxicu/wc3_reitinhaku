package reitinhaku.tietorakenteet;

import reitinhaku.algoritmit.Solmu;

public class PrioriteettiJono {

    int koko;
    Solmu[] jono;
    int vika;

    public PrioriteettiJono(int koko) {
        this.koko = koko;
        jono = new Solmu[koko];
        vika = 0;
    }

    public void add(Solmu s) {
        if (taynna()) {
            System.out.println("Ei mahdu");
        }
        if (vika == 0) {
            jono[vika++] = s;
        } else {
            int i;
            for (i = vika - 1; i >= 0; i--) {
                if (s.getJaljella() > jono[i].getJaljella()) {
                    jono[i + 1] = jono[i];
                } else {
                    break;
                }
                jono[++i] = s;
                vika++;
            }
        }
    }

    public Solmu poll() {
        if (!tyhja()) {
            return jono[--vika];
        }
        return null;
    }

    public boolean tyhja() {
        return vika == 0;
    }

    public boolean taynna() {
        return vika == koko;
    }
}
