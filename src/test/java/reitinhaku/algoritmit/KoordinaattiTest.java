package reitinhaku.algoritmit;

import org.junit.*;

import jdk.jfr.Timestamp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import reitinhaku.kayttoliittyma.Kartanlaturi;
import reitinhaku.logiikka.Kartta;

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
        b = new Koordinaatti(2, 10, a, 0.0);
    }

    @Test
    public void vanhempiLoytyy() {
        assertNull(a.getVanhempi());
        assertEquals(a, b.getVanhempi());
    }
/*
    @Test
    public void reittiKasvaaLomittain() {
        assertEquals("", a.getReitti());
        assertEquals(0.0, a.laskeReitinPituus(a.getReitti()), 0.00001);
        a = new Koordinaatti(a.getX(), a.getY(), a.getReitti() + "AO ");
        assertEquals("AO ", a.getReitti());
        assertEquals(1.414213, a.laskeReitinPituus(a.getReitti()), 0.00001);

    }
*/
/*
    @Test
    public void reittiKasvaaSuoraan() {
        assertEquals("", a.getReitti());
        assertEquals(0.0, a.laskeReitinPituus(a.getReitti()), 0.00001);
        a = new Koordinaatti(a.getX(), a.getY(), a.getReitti() + "A ");
        assertEquals("A ", a.getReitti());
        assertEquals(1.0, a.laskeReitinPituus(a.getReitti()), 0.00001);

    }
    */

    @Test
    public void naapuritLoytyy() {
        List<Koordinaatti> ykkosenNaapurit = new ArrayList<>();
        ykkosenNaapurit.add(new Koordinaatti(2, 1));
        ykkosenNaapurit.add(new Koordinaatti(0, 1));
        ykkosenNaapurit.add(new Koordinaatti(1, 2));
        ykkosenNaapurit.add(new Koordinaatti(1, 0));
        ykkosenNaapurit.add(new Koordinaatti(2, 2));
        ykkosenNaapurit.add(new Koordinaatti(2, 0));
        ykkosenNaapurit.add(new Koordinaatti(0, 2));
        ykkosenNaapurit.add(new Koordinaatti(0, 0));

        Koordinaatti n = new Koordinaatti(1, 1);
        int i = 0;
        for (Koordinaatti koordinaatti : n.naapurit()) {
            assertEquals(koordinaatti.getX(), ykkosenNaapurit.get(i).getX());
            assertEquals(koordinaatti.getY(), ykkosenNaapurit.get(i).getY());
            i++;
        }
    }

}