package reitinhaku.algoritmit;

import java.util.ArrayList;
import java.util.List;

public class Koordinaatti {

    int x;
    int y;
    String reitti;
    Koordinaatti vanhempi;

    public Koordinaatti(int x, int y) {
        this.x = x;
        this.y = y;
        vanhempi = null;
        reitti = "";
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
    public double laskeReitinPituus(String reitti){
        double pituus = 0;
        String str[] = reitti.split(" ");
        for (int i = 0; i < str.length; i++) {
            if(str[i].length() == 1){
                pituus++;
            } else{
                pituus += Math.sqrt(1*1 + 1*1);
            }
        }
        System.out.print("Reitin pituus: ");
        return pituus;
    }
    public List<Koordinaatti> naapurit() {
        List<Koordinaatti> naapurit = new ArrayList<>();
        naapurit.add(new Koordinaatti(x + 1, y, reitti + "A "));
        naapurit.add(new Koordinaatti(x + 1, y + 1, reitti + "AO "));
        naapurit.add(new Koordinaatti(x + 1, y - 1, reitti + "AV "));
        naapurit.add(new Koordinaatti(x - 1, y, reitti + "Y "));
        naapurit.add(new Koordinaatti(x - 1, y + 1, reitti + "YO "));
        naapurit.add(new Koordinaatti(x - 1, y - 1, reitti + "YV "));
        naapurit.add(new Koordinaatti(x, y + 1, reitti + "O "));
        naapurit.add(new Koordinaatti(x, y - 1, reitti + "V "));
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
}
