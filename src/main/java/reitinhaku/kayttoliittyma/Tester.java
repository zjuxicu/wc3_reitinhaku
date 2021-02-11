package reitinhaku.kayttoliittyma;

import reitinhaku.logiikka.*;

public class Tester {

    public Tester(String kartannimi) {
        Kartanlaturi laturi = new Kartanlaturi();
        System.out.println("Täällä suoritetaan suorituskykytestejä.");
        System.out.println("Arvottu kartta : " + kartannimi);

        Kartta kartta = new Kartta(laturi.lataaKartta(kartannimi), kartannimi);

    }
}
