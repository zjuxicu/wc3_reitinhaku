package reitinhaku.kartta;

import org.junit.*;
import static org.junit.Assert.*;

import reitinhaku.algoritmit.Koordinaatti;

public class KarttaTest {

    Kartanlaturi laturi;
    Kartta testikartta;

    public KarttaTest() {
    }

    @Before
    public void setUp() {
        laturi = new Kartanlaturi();
        String testikartannimi = "testi";
        testikartta = new Kartta(laturi.lataaKartta(testikartannimi), testikartannimi);
    }

    @Test
    public void karttaOnOlemassa() {
        assertEquals("testi", testikartta.getNimi());
    }

    @Test
    public void karttaAsettaaLahdon() {
        testikartta.asetaLahto(1, 1);
        assertEquals(1, testikartta.getAlkuX());
        assertEquals(1, testikartta.getAlkuY());
    }

    @Test
    public void karttaEiAsetaLahtoaAlleNollanEikaYliRajojen() {
        testikartta.asetaLahto(-1, 10000);
        assertEquals(0, testikartta.getAlkuX());
        assertEquals(0, testikartta.getAlkuY());
    }

    @Test
    public void karttaAsettaaMaalin() {
        testikartta.asetaMaali(1, 1);
        assertEquals(1, testikartta.getMaaliX());
        assertEquals(1, testikartta.getMaaliY());
    }

    @Test
    public void karttaEiAsetaMaaliaAlleNollanEikaYliRajojen() {
        testikartta.asetaMaali(-1, 10000);
        assertEquals(0, testikartta.getMaaliX());
        assertEquals(0, testikartta.getMaaliY());
    }

    @Test
    public void karttaTietaaRajansa() {
        Koordinaatti k1 = new Koordinaatti(0, 0);
        Koordinaatti k2 = new Koordinaatti(1, 1);
        Koordinaatti k3 = new Koordinaatti(-1, -1);
        Koordinaatti k4 = new Koordinaatti(10, 10);

        assertTrue(testikartta.rajojenSisalla(k1));
        assertTrue(testikartta.rajojenSisalla(k2));
        assertFalse(testikartta.rajojenSisalla(k3));
        assertFalse(testikartta.rajojenSisalla(k4));
    }

}
