package reitinhaku.kartta;

import java.io.InputStream;
import java.util.Scanner;

public class Kartanlaturi {

    Scanner lukija;
    char[][] kartta;
    String rivitekstina;
    int rivi;
    int sarake;
    int korkeus;
    int leveys;
    boolean tiedot;

    public Kartanlaturi() {
        nollaa();
    }

    public void nollaa() {
        rivitekstina = "";
        rivi = 0;
        sarake = 0;
        korkeus = 0;
        leveys = 0;
        tiedot = false;
    }

    /**
     * Lukee tiedostosta kartan, ja muokkaa sen char[][] muotoon.
     * 
     * @param nimi Jos nimeä ei löydy resursseista, metodi sulkee ohjelman.
     * @return char[][]
     */
    public char[][] lataaKartta(String nimi) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(nimi + ".map");

        try {
            lukija = new Scanner(is);

            while (lukija.hasNextLine()) {
                rivitekstina = lukija.nextLine();
                lueRivi(rivitekstina);
            }
            lukija.close();

        } catch (Exception e) {
            System.out.println("Ongelma karttaa ladatessa: " + e);
            System.exit(0);
        }

        return kartta;
    }

    /**
     * Metodi lukee kartasta perustiedot, jonka jälkeen täyttää char[][] taulukon.
     * 
     * @param rivitekstina
     */
    public void lueRivi(String rivitekstina) {
        if (rivitekstina.contains("height")) {
            korkeus = Integer.parseInt(rivitekstina.replaceAll("\\D", ""));
        }
        if (rivitekstina.contains("width")) {
            leveys = Integer.parseInt(rivitekstina.replaceAll("\\D", ""));
        }
        if (rivitekstina.contains("map")) {
            tiedot = true;
            kartta = new char[korkeus][leveys];
        }
        if (tiedot && !rivitekstina.contains("map")) {
            sarake = 0;
            for (char ch : rivitekstina.toCharArray()) {
                kartta[rivi][sarake] = ch;
                sarake++;
            }
            rivi++;
        }
    }

}
