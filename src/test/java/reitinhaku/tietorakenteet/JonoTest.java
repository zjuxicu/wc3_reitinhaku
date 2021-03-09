package reitinhaku.tietorakenteet;

import reitinhaku.algoritmit.Solmu;
import org.junit.*;

import static org.junit.Assert.*;

public class JonoTest {
    @Test
    public void jonoKasvaa() {
        Jono jono = new Jono(2);
        assertEquals(jono.koko(), 2);
        Solmu a = new Solmu(1, 2);
        Solmu b = new Solmu(2, 3);
        Solmu c = new Solmu(5, 5);
        jono.lisaa(a);
        jono.lisaa(b);
        assertEquals(jono.koko(), 2);
        jono.lisaa(c);
        assertEquals(jono.koko(), 4);
    }

    @Test
    public void pollaaOikean(){
        Jono jono = new Jono(2);
        assertTrue(jono.tyhja());
        Solmu a = new Solmu(1, 2);
        Solmu b = new Solmu(2, 3);
        Solmu c = new Solmu(5, 5);
        jono.lisaa(a);
        jono.lisaa(b);
        assertEquals(jono.poll(), a);
        jono.lisaa(c);
        assertEquals(jono.poll(), b);
        assertFalse(jono.tyhja());
        assertEquals(jono.poll(), c);
        assertTrue(jono.tyhja());
    }
}
