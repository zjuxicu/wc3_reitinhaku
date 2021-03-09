package reitinhaku.algoritmit;

import org.junit.*;

import static org.junit.Assert.*;
import reitinhaku.kartta.*;

public class JPSTest {
    Kartanlaturi laturi;
    Kartta testikartta;
    JPS jps;
    Koordinaatti lahto;
    Koordinaatti maali;

    @Before
    public void setUp() {
        laturi = new Kartanlaturi();
        laturi = new Kartanlaturi();
        String testikartannimi = "testi";
        testikartta = new Kartta(laturi.lataaKartta(testikartannimi), testikartannimi);
        testikartta.asetaLahto(1, 1);
        testikartta.asetaMaali(24, 24);
        lahto = new Koordinaatti(1, 1);
        maali = new Koordinaatti(24, 24);
        jps = new JPS();

    }

    @Test
    public void reittiLoytyy() {
        jps.alusta(testikartta);
        boolean onnistui = jps.haku();
        assertTrue(onnistui);
    }
    @Test
    public void polkuLoytyy(){
        jps.alusta(testikartta);
        boolean onnistui = jps.haku();
        Solmu s = jps.loppuSolmu();
        String reitti = "";
        jps.luoPolku(s);
        assertNotEquals(reitti, jps.getReitti());
    }
    @Test
    public void reittiaEiLoydy() {
        testikartta.asetaMaali(3, 3);
        jps.alusta(testikartta);
        assertFalse(jps.haku());
    }
    @Test
    public void polkuKunEiReittia() {
        testikartta.asetaMaali(3, 3);
        jps.alusta(testikartta);
        boolean haku = jps.haku();
        assertFalse(haku);
        Solmu s = jps.loppuSolmu();
        try {
            jps.luoPolku(s);
        } catch (Exception e) {
            // null
        }
        assertNull(jps.getReitti());
    }
}
