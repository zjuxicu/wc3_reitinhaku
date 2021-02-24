package reitinhaku.algoritmit;

import org.junit.*;

import static org.junit.Assert.*;

import reitinhaku.kayttoliittyma.*;
import reitinhaku.logiikka.Kartta;

public class AstarTest {
    Kartanlaturi laturi;
    Kartta testikartta;
    Astar a;
    Koordinaatti lahto;
    Koordinaatti maali;

    public AstarTest() {

    }

    @Before
    public void setUp() {
        laturi = new Kartanlaturi();
        String testikartannimi = "testi";
        testikartta = new Kartta(laturi.lataaKartta(testikartannimi), testikartannimi);
        testikartta.asetaMaali(4, 4);
        lahto = new Koordinaatti(0, 0);
        maali = new Koordinaatti(4, 4);
        a = new Astar();

    }

    @Test
    public void reittiLoytyy() {
        a.alusta(testikartta);
        assertTrue(a.haku());

    }
    @Test
    public void reittiaEiLoydy(){
        testikartta.asetaMaali(3, 3);
        a.alusta(testikartta);
        boolean haku = a.haku();
        assertFalse(haku);
    }
}
