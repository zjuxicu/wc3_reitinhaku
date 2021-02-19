package reitinhaku.algoritmit;

public class Solmu {

    private Koordinaatti k;
    private String reitti;
    private double reitinPituus; // Lähtökoordinaatista kuljetun reitin pituus.
    private double jaljella; // Oletettu etäisyys maalista
    private Koordinaatti vanhempi;
    private Solmu lahto;
    private Koordinaatti maali;

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

    /**
     * Somujen naapureiden läpikäymisen käytetty konstruktori, jota Dijkstran
     * algoritmissä hyödynnetään. Katso naapurit().
     * 
     * @param x
     * @param y
     * @param reitti
     */
    public Solmu(Koordinaatti k, String reitti) {
        this.k = k;
        this.jaljella = k.etaisyysMaalista(k);
        this.reitti = reitti;
    }

    public Solmu(Koordinaatti k) {
        this.k = k;
    }

	public Solmu(Koordinaatti koordinaatti, Koordinaatti vanhempi, double reitinPituus) {
        this.k = koordinaatti;
        this.vanhempi = vanhempi;
        this.reitinPituus = reitinPituus;
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
        String str[] = reitti.split(" ");
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

    /**
     * Palauttaa Solmun vanhemman.
     * 
     * @return Solmu
     */
    public Koordinaatti getVanhempi() {
        return this.vanhempi;
    }

    /**
     * Palauttaa Reitin.
     * 
     * @return
     */
    public String getReitti() {
        return this.reitti;
    }

    /**
     * Palauttaa reitinPituuden lähdöstä kyseiseen Solmuun.
     * 
     * @return double
     */
    public double getReitinPituus() {
        return this.reitinPituus;
    }

    public double getJaljella() {
        return this.jaljella;
    }

    /**
     * Palauttaa jäljellä olevan etäisyyden arvion maalista.
     * 
     * @param k Maaliin vertailtava koordinaatti.
     * @return double Euklidinen etäisyys.
     */
 
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Solmu s = (Solmu) o;
        return this.getKoordinaatti() != s.getKoordinaatti();

    }

    @Override
    public int hashCode() {
        int hash = 1337;
        return hash;
    }

    @Override
    public String toString() {
        return this.k + ""; // + " " + jaljella + " " + reitinPituus + " " + reitti;
    }
}
