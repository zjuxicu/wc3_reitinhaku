package reitinhaku.algoritmit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Koordinaatti implements Comparator<Koordinaatti> {

    int x;
    int y;
    String reitti;
    double reitinPituus;
    Koordinaatti vanhempi;
    Koordinaatti maali;

    public Koordinaatti(Koordinaatti maali) {
        this.maali = maali;
    }

    public Koordinaatti(int x, int y) {
        this.x = x;
        this.y = y;
        vanhempi = null;
        reitti = "";
        reitinPituus = 0;
    }

    public Koordinaatti(Koordinaatti lapsi, Koordinaatti vanhempi, double matka) {
        this.x = lapsi.x;
        this.y = lapsi.y;
        this.vanhempi = vanhempi;
        this.reitinPituus = matka;
    }

    public Koordinaatti(int x, int y, Koordinaatti vanhempi) {
        this.x = x;
        this.y = y;
        this.vanhempi = vanhempi;
    }

    public Koordinaatti(int x, int y, String reitti) {
        this.x = x;
        this.y = y;
        this.reitti = reitti;
    }

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

    public List<Koordinaatti> naapurit() {
        List<Koordinaatti> naapurit = new ArrayList<>();
        naapurit.add(new Koordinaatti(x + 1, y, reitti + "A "));
        naapurit.add(new Koordinaatti(x - 1, y, reitti + "Y "));
        naapurit.add(new Koordinaatti(x, y + 1, reitti + "O "));
        naapurit.add(new Koordinaatti(x, y - 1, reitti + "V "));
        naapurit.add(new Koordinaatti(x + 1, y + 1, reitti + "AO "));
        naapurit.add(new Koordinaatti(x + 1, y - 1, reitti + "AV "));
        naapurit.add(new Koordinaatti(x - 1, y + 1, reitti + "YO "));
        naapurit.add(new Koordinaatti(x - 1, y - 1, reitti + "YV "));

        return naapurit;
    }

    public String toString() {
        return "[" + x + "," + y + "]";
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Koordinaatti getVanhempi() {
        return this.vanhempi;
    }

    public double laskeMatka(Koordinaatti k) {
        double x = k.x - maali.x;
        double absx = x > 0 ? x : -x;
        double y = k.y - maali.y;
        double absy = y > 0 ? y : -y;
        return Math.sqrt(absx * absx + absy * absy);
    }

    @Override
    public int compare(Koordinaatti k1, Koordinaatti k2) {

        if (laskeMatka(k1) > laskeMatka(k2)) {
            return 1;
        }
        if (laskeMatka(k1) < laskeMatka(k2)) {
            return -1;
        }
        return 0;
    }

}
