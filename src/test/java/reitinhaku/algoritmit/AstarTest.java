package reitinhaku.algoritmit;

import org.junit.*;

import jdk.jfr.Timestamp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
        maali = new Koordinaatti(3,3);
        a.aloita(testikartta);
        assertFalse(a.haku());
    }
}
