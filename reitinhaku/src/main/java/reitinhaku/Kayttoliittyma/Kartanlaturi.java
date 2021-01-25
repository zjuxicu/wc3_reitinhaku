package reitinhaku.Kayttoliittyma;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Kartanlaturi {

    Scanner lukija;
    // Kartta kartta;
    char[][] kartta;

    public Kartanlaturi() {
        //
    }

    public void lataaKartta(String nimi) {

        InputStream is = getClass().getClassLoader().getResourceAsStream(nimi + ".map");
        String rivitekstina = "";
        int rivi = 0;
        int sarake = 0;
        int korkeus = 0;
        int leveys = 0;
        boolean tiedot = false;

        try {
            Scanner lukija = new Scanner(is);

            while (lukija.hasNextLine()) {
                rivitekstina = lukija.nextLine();
                if (rivitekstina.contains("height")) {
                    korkeus = Integer.parseInt(rivitekstina.replaceAll("\\D", ""));
                }
                if (rivitekstina.contains("width")) {
                    leveys = Integer.parseInt(rivitekstina.replaceAll("\\D", ""));
                }
                if (rivitekstina.contains("map")) {
                    tiedot = true;
                    kartta = new char[korkeus][leveys];
                    System.out.println("Kartta löytyi!");
                    System.out.println("Kartan korkeus: " + korkeus);
                    System.out.println("Kartan leveys: " + leveys);

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
            lukija.close();

            System.out.println("Vasenta yläkulmaa vastaa 0,0 ja oikeaa alakulmaa " + rivi + "," + sarake);
        } catch (Exception e) {
            System.out.println("Ongelma karttaa ladatessa: " + e);
        }
        //tulostaKartta();
    }

    public void tulostaKartta() {
        for (char[] rivi : kartta) {
            System.out.println(Arrays.toString(rivi));

        }
    }

}
