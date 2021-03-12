package reitinhaku.algoritmit;

import org.junit.*;

import static org.junit.Assert.*;

import java.io.File;

import reitinhaku.kartta.*;

public class VertailuTest {
    Kartanlaturi laturi;
    Kartta testikartta;
    Dijkstra d;
    Astar a;
    JPS j;
    Koordinaatti lahto;
    Koordinaatti maali;

    public VertailuTest() {

    }

    @Before
    public void setUp() {
        laturi = new Kartanlaturi();
        String testikartannimi = arvoKartta();
        testikartta = new Kartta(laturi.lataaKartta(testikartannimi), testikartannimi);
        char[][] taulu = testikartta.getTaulukko();
        int rajaX = taulu.length - 1;
        int rajaY = taulu[0].length - 1;
        int xLahto = arvo(rajaX);
        int yLahto = arvo(rajaY);
        while (!testikartta.rajojenSisalla(xLahto, yLahto)) {
            xLahto = arvo(rajaX);
            yLahto = arvo(rajaY);
        }
        int xMaali = arvo(rajaX);
        int yMaali = arvo(rajaY);
        while (!testikartta.rajojenSisalla(xMaali, yMaali)) {
            xMaali = arvo(rajaX);
            yMaali = arvo(rajaY);
        }
        lahto = new Koordinaatti(xLahto, yLahto);
        maali = new Koordinaatti(xMaali, yMaali);
        testikartta.asetaLahto(xLahto, yLahto);
        testikartta.asetaMaali(xMaali, yMaali);
        lahto = new Koordinaatti(0, 0);
        maali = new Koordinaatti(4, 4);
        d = new Dijkstra();
        a = new Astar();
        j = new JPS();

    }

    @Test
    public void aStarLoydettyPolkuYhtaPitkaKuinDijkstra() {
        float aPituus = 0;
        float dPituus = 0;
        a.alusta(testikartta);
        if(a.haku()){
            aPituus = a.getPituus();
        }
        d.alusta(testikartta);
        if(d.haku()){
            dPituus = d.getPituus();
        }
        assertEquals(aPituus, dPituus, 0.001);

    }
    @Test
    public void jpsLoydettyPolkuYhtaPitkaKuinDijkstra() {
        float jPituus = 0;
        float dPituus = 0;
        j.alusta(testikartta);
        if(j.haku()){
            jPituus = a.getPituus();
        }
        d.alusta(testikartta);
        if(d.haku()){
            dPituus = d.getPituus();
        }
        assertEquals(jPituus, dPituus, 0.001);

    }
    private int arvo(int raja) {
        int kello = (int) System.nanoTime();
        int tulos = kello % raja;
        int abstulos = tulos > 0 ? tulos : -tulos;
        if (abstulos >= raja) {
            kello = (int) System.nanoTime();
            arvo(raja);
        }
        return abstulos;
    }
    public String arvoKartta() {
        File f = new File("src/main/resources");
        File[] flista = f.listFiles();
        String kartta = "";

        for (int i = 1; i <= flista.length - 1; i++) {
            int k = (int) System.nanoTime() % i;
            if (k >= 0 && k <= flista.length) {
                if (!flista[k].getName().equals("testi.map")) {
                    kartta = flista[k].getName();
                }
            }

        }
        kartta = kartta.substring(0, kartta.length() - 4);
        return kartta;
    }
}