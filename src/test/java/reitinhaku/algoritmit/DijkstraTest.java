package reitinhaku.algoritmit;

import org.junit.*;

import static org.junit.Assert.*;

import reitinhaku.kartta.*;

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
    public void polkuLoytyy(){
        d.alusta(testikartta);
        boolean onnistui = d.haku();
        Solmu s = d.loppuSolmu();
        String reitti = "";
        d.luoPolku(s);
        assertNotEquals(reitti, d.getReitti());
    }
    @Test
    public void reittiaEiLoydy() {
        testikartta.asetaMaali(3, 3);
        d.alusta(testikartta);
        assertFalse(d.haku());
    }
    @Test
    public void polkuKunEiReittia() {
        testikartta.asetaMaali(3, 3);
        d.alusta(testikartta);
        boolean haku = d.haku();
        assertFalse(haku);
        Solmu s = d.loppuSolmu();
        try {
            d.luoPolku(s);
        } catch (Exception e) {
            // null
        }
        assertNull(d.getReitti());
    }
}
