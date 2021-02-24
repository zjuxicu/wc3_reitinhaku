package reitinhaku.algoritmit;

import org.junit.*;

import static org.junit.Assert.*;

import reitinhaku.kayttoliittyma.*;
import reitinhaku.logiikka.Kartta;

public class DijkstraTest {
    Kartanlaturi laturi;
    Kartta testikartta;
    Dijkstra d;
    Koordinaatti lahto;
    Koordinaatti maali;

    public DijkstraTest() {

    }

    @Before
    public void setUp() {
        laturi = new Kartanlaturi();
        String testikartannimi = "testi";
        testikartta = new Kartta(laturi.lataaKartta(testikartannimi), testikartannimi);
        testikartta.asetaMaali(4, 4);
        lahto = new Koordinaatti(0, 0);
        maali = new Koordinaatti(4, 4);
        d = new Dijkstra();

    }

    @Test
    public void reittiLoytyy() {
        d.alusta(testikartta);
        assertTrue(d.haku());

    }

    @Test
    public void reittiaEiLoydy() {
        testikartta.asetaMaali(3, 3);
        d.alusta(testikartta);
        assertFalse(d.haku());
    }

}
