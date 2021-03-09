package reitinhaku.tietorakenteet;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import reitinhaku.algoritmit.Solmu;

public class KekoTest {
    Keko keko;
    Solmu a;
    Solmu b;
    Solmu c;
    Solmu d;
    Solmu maali;

    @Before
    public void setUp() {
        this.maali = new Solmu(4, 4);
        this.keko = new Keko(2, maali);
        this.a = new Solmu(1, 1);
        this.b = new Solmu(1, 2);
        this.c = new Solmu(2, 3);
        this.d = new Solmu(5, 5);
    }

    @Test
    public void kasvatusToimii() {
        assertEquals(keko.getKoko(), 2);
        keko.lisaa(a);
        keko.lisaa(b);
        assertEquals(keko.getKoko(), 4);
        keko.lisaa(c);
        assertEquals(keko.getKoko(), 4);

    }
}
