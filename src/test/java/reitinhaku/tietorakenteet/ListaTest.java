package reitinhaku.tietorakenteet;

import org.junit.*;

import static org.junit.Assert.*;

import reitinhaku.algoritmit.Solmu;

public class ListaTest {

    @Test
    public void listaKasvaa() {
        Lista lista = new Lista(2);
        assertEquals(lista.koko(), 2);
        Solmu a = new Solmu(1, 2);
        Solmu b = new Solmu(2, 3);
        Solmu c = new Solmu(5, 5);
        lista.lisaa(a);
        lista.lisaa(b);
        assertEquals(lista.koko(), 2);
        lista.lisaa(c);
        assertEquals(lista.koko(), 4);
    }

    @Test
    public void naapuritLoytyy() {
        Lista ykkosenNaapurit = new Lista();
        ykkosenNaapurit.lisaa(new Solmu(2, 1));
        ykkosenNaapurit.lisaa(new Solmu(0, 1));
        ykkosenNaapurit.lisaa(new Solmu(1, 2));
        ykkosenNaapurit.lisaa(new Solmu(1, 0));
        ykkosenNaapurit.lisaa(new Solmu(2, 2));
        ykkosenNaapurit.lisaa(new Solmu(2, 0));
        ykkosenNaapurit.lisaa(new Solmu(0, 2));
        ykkosenNaapurit.lisaa(new Solmu(0, 0));

        Solmu n = new Solmu(1, 1);
        int i = 0;
        for (Solmu s : n.naapurit()) {
            assertEquals(s.getKoordinaatti().getX(), ykkosenNaapurit.nouda(i).getKoordinaatti().getX(), 0.001);
            assertEquals(s.getKoordinaatti().getY(), ykkosenNaapurit.nouda(i).getKoordinaatti().getY(), 0.001);
            i++;
        }
    }
}