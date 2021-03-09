package reitinhaku.kartta;

import reitinhaku.algoritmit.Koordinaatti;

/**
 * Kartta
 * 
 * Pitää sisällään valitun kartan tiedot ja värikoodit.
 */

public class Kartta {

    private char[][] taulukko;
    private String nimi;
    private int aX;
    private int aY;
    private int mX;
    private int mY;
    Kartanpiirtaja piirra;
    private int[] jps = { 255, 255, 0 };
    private int[] dijkstra = { 0, 255, 255 };
    private int[] astar = { 255, 0, 255 };
    private int[] vierailtu = { 255, 255, 255 };
    private int[] puu = { 10, 100, 10 };
    private int[] maa = { 130, 100, 10 };
    private int[] vesi = { 0, 0, 255 };
    private int[] este = { 0, 0, 0 };
    private int[] lahto = { 0, 255, 0 };
    private int[] maali = { 255, 0, 0 };

    public Kartta(char[][] taulukko, String nimi) {
        this.taulukko = taulukko;
        this.nimi = nimi;
        piirra = new Kartanpiirtaja(taulukko.length, taulukko[0].length);
    }

    /**
     * Asettaa kartalle käyttäjän valitseman lähtökoordinaatin.
     * 
     * @param aX Lähdön X-koordinaatti.
     * @param aY Lähdön Y-koordinaatti.
     * @return boolean Palauttaa arvon mikäli asettaminen onnistui.
     */
    public boolean asetaLahto(int aX, int aY) {
        if (aX < taulukko.length && aX >= 0 && aY < taulukko[0].length && aY >= 0) {
            this.aX = aX;
            this.aY = aY;
            return true;
        } else {
            System.out.println(
                    "Arvojen tulee olla väliltä: 0," + (taulukko.length - 1) + " ja 0," + (taulukko[0].length - 1));
            return false;
        }

    }

    /**
     * Asettaa kartalle käyttäjän valitseman maalikoordinaatin.
     * 
     * @param mX Maalin X-koordinaatti.
     * @param mY Maalin Y-koordinaatti.
     * @return boolean Palauttaa arvon mikäli asettaminen onnistui.
     */
    public boolean asetaMaali(int mX, int mY) {
        if (mX < taulukko.length && mX >= 0 && mY < taulukko[0].length && mY >= 0) {
            this.mX = mX;
            this.mY = mY;
            return true;
        } else {
            System.out.println(
                    "Arvojen tulee olla väliltä: 0," + (taulukko.length - 1) + " ja 0," + (taulukko[0].length - 1));
            return false;
        }

    }

    /**
     * Mittaa kahden koordinaatin x-arvojen välisen etäisyyden.
     * 
     * @param a
     * @param b
     * @return float
     */
    public float dX(Koordinaatti a, Koordinaatti b) {
        float x = a.getX() - b.getX();
        return x > 0 ? x : -x;

    }

    /**
     * Mittaa kahden koordinaatin y-arvojen välisen etäisyyden.
     * 
     * @param a
     * @param b
     * @return float
     */
    public float dY(Koordinaatti a, Koordinaatti b) {
        float y = a.getY() - b.getY();
        return y > 0 ? y : -y;
    }

    /**
     * Laskee kahden koordinaatin välisen euklidisen etäisyyden.
     * 
     * @param a
     * @param b
     * @return float Käyttäen Math.sqrt():tä.
     */
    public float linnuntie(Koordinaatti a, Koordinaatti b) {
        float x = dX(a, b);
        float y = dY(a, b);
        float pyorista = (float) Math.sqrt(x * x + y * y);
        return (float) (Math.floor(pyorista * 1000 + 0.5) / 1000);
    }

    /**
     * Lähtökoordinaatin X-arvon getteri.
     * 
     * @return int
     */
    public int getAlkuX() {
        return this.aX;
    }

    /**
     * Lähtökoordinaatin Y-arvon getteri.
     * 
     * @return int
     */
    public int getAlkuY() {
        return this.aY;
    }

    /**
     * Maalikoordinaatin X-arvon getteri.
     * 
     * @return int
     */
    public int getMaaliX() {
        return this.mX;
    }

    /**
     * Maalikoordinaatin Y-arvon getteri.
     * 
     * @return int
     */
    public int getMaaliY() {
        return this.mY;
    }

    /**
     * Kartan getteri char-taulukkona.
     * 
     * @return char[][]
     */
    public char[][] getTaulukko() {
        return this.taulukko;
    }

    /**
     * Kartan nimen getteri.
     * 
     * @return String
     */
    public String getNimi() {
        return this.nimi;
    }

    /**
     * Tarkistaa onko tutkittava Koordinaatti kartan rajojen sisäpuolella ja onko
     * koordinaatti kuljettavissa.
     * 
     * @param naatti Tutkittava koordinaatti.
     * @return boolean
     */
    public boolean rajojenSisalla(Koordinaatti naatti) {
        int x = naatti.getX();
        int y = naatti.getY();
        boolean kartalla = (x >= 0 && y >= 0 && x < taulukko[0].length && y < taulukko.length);
        if (kartalla) {
            char ch = taulukko[x][y];
            if (ch == '@' || ch == 'O' || ch == 'T' || ch == 'W') {
                kartalla = false;
            }
        }
        return kartalla;
    }

    /**
     * Tarkistaa onko tutkittavat arvot kartan rajojen sisäpuolella ja onko ne
     * vierailtavissa.
     * 
     * @param x
     * @param y
     * @return boolean
     */
    public boolean rajojenSisalla(int x, int y) {
        boolean kartalla = (x >= 0 && y >= 0 && x < taulukko[0].length && y < taulukko.length);
        if (kartalla) {
            char ch = taulukko[x][y];
            if (ch == '@' || ch == 'O' || ch == 'T' || ch == 'W') {
                kartalla = false;
            }
        }
        return kartalla;
    }

    /**
     * Piirtää kartalle ruksin merkatakseen lähtökoordinaattia.
     * 
     * @param x
     * @param y
     */
    public void piirraAlku(int x, int y) {
        piirra.kohde(x, y, lahto);
    }

    /**
     * Piirtää kartalle ruksin merkatakseen maalikoordinaattia.
     * 
     * @param x
     * @param y
     */
    public void piirraMaali(int x, int y) {
        piirra.kohde(x, y, maali);
    }

    /**
     * Piirtää kartalle JPS-algoritmin tuottaman polun.
     * 
     * @param x
     * @param y
     * @param i
     * @param j
     */
    public void piirraJPS(int x, int y, int i, int j) {
        piirra.viiva(x, y, i, j, jps);
    }

    /**
     * Piirtää kartalle Dijkstran tuottaman polun.
     * 
     * @param x
     * @param y
     * @param i
     * @param j
     */
    public void piirraDijkstra(int x, int y, int i, int j) {
        piirra.viiva(x, y, i, j, dijkstra);
    }

    /**
     * Piirtää kartalle A*-algoritmin tuottaman polun.
     * 
     * @param x
     * @param y
     * @param i
     * @param j
     */
    public void piirraAStar(int x, int y, int i, int j) {
        piirra.viiva(x, y, i, j, astar);
    }

    /**
     * Tallentaa kuvan.
     * 
     * @param nimi
     */
    public void tulosta(String nimi) {
        piirra.tulosta(nimi);
    }

    /**
     * Muodostaa ja piirtää kartan taulukosta graafisen esityksen joka myöhemmin
     * tallennetaan.
     */
    public void piirraKartta() {
        for (int i = 0; i < taulukko.length; i++) {
            for (int j = 0; j < taulukko[0].length; j++) {
                if (taulukko[i][j] == '@' || taulukko[i][j] == 'O') {
                    piirra.kohde(i, j, este);
                    continue;
                }
                if (taulukko[i][j] == 'T') {
                    piirra.kohde(i, j, puu);
                    continue;
                }
                if (taulukko[i][j] == 'W') {
                    piirra.kohde(i, j, vesi);
                    continue;
                }
                piirra.kohde(i, j, maa);
            }

        }
    }

    /**
     * Alustaa Kartanpiirtajan ja muodostaa kartan pohjan uudelleen.
     */
    public void nollaaPiirros() {
        piirra = new Kartanpiirtaja(taulukko.length, taulukko[0].length);
        piirraKartta();
    }
}
