package reitinhaku.algoritmit;

import org.junit.*;

import static org.junit.Assert.*;

import reitinhaku.kartta.*;

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
        testikartta.asetaMaali(0, 0);
        lahto = new Koordinaatti(4, 4);
        maali = new Koordinaatti(0, 0);
        a = new Astar();

    }

    @Test
    public void reittiLoytyy() {
        a.alusta(testikartta);
        assertTrue(a.haku());

    }

    @Test
    public void polkuLoytyy() {
        a.alusta(testikartta);
        boolean onnistui = a.haku();
        Solmu s = a.loppuSolmu();
        String reitti = "";
        a.luoPolku(s);
        assertNotEquals(reitti, a.getReitti());
    }

    @Test
    public void reittiaEiLoydy() {
        testikartta.asetaMaali(3, 3);
        a.alusta(testikartta);
        boolean haku = a.haku();
        assertFalse(haku);
    }

    @Test
    public void polkuKunEiReittia() {
        testikartta.asetaMaali(3, 3);
        a.alusta(testikartta);
        boolean haku = a.haku();
        assertFalse(haku);
        Solmu s = a.loppuSolmu();
        try {
            a.luoPolku(s);
        } catch (Exception e) {
            // null
        }
        assertNull(a.getReitti());
    }
}
