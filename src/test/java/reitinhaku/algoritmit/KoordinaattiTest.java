package reitinhaku.algoritmit;

import org.junit.*;

import static org.junit.Assert.*;

import reitinhaku.kartta.*;

/**
 * KoordinaattiTest
 */
public class KoordinaattiTest {

    Kartanlaturi laturi;
    Kartta testikartta;
    Koordinaatti a;
    Koordinaatti b;

    public KoordinaattiTest() {

    }

    @Before
    public void setUp() {
        laturi = new Kartanlaturi();
        String testikartannimi = "testi";
        testikartta = new Kartta(laturi.lataaKartta(testikartannimi), testikartannimi);
        a = new Koordinaatti(1, 10);
        b = new Koordinaatti(2, 10, a, 0);
    }

    @Test
    public void vanhempiLoytyy() {
        assertNull(a.getVanhempi());
        assertEquals(a, b.getVanhempi());
    }
}