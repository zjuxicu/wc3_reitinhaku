package reitinhaku.algoritmit;

public class Solmu {

    private Koordinaatti k;
    private String reitti;
    private double reitinPituus; // Lähtökoordinaatista kuljetun reitin pituus.
    private Koordinaatti vanhempi;

    /**
     * Pitää kirjaa reitistä Stringinä ja reitin pituutta doublena.
     * 
     * @param x
     * @param y
     */
    public Solmu(int x, int y) {
        k = new Koordinaatti(x, y);
        reitti = "";
        reitinPituus = 0;
    }

    public Solmu(Koordinaatti k) {
        this.k = k;
    }

    public Koordinaatti getKoordinaatti() {
        return this.k;
    }

    /**
     * Dijkstran algoritmiä varten toteutettu reitinlaskuri.
     * 
     * @param reitti esim. "AO AO A AV A O O O".
     * @return double samalla esimerkillä tulostuisi ~9.24
     */
    public double laskeReitinPituus(String reitti) {
        if (reitti.length() == 0) {
            return 0;
        }
        double pituus = 0;
        String[] str = reitti.split(" ");
        double apu = Math.sqrt(2);
        for (int i = 0; i < str.length; i++) {
            if (str[i].length() == 1) {
                pituus++;
            } else {
                pituus += apu;
            }
        }

        return pituus;
    }

    @Override
    public String toString() {
        return this.k + " " + reitinPituus + " " + reitti;
    }
}
