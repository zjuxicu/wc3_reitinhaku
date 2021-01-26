package reitinhaku.kayttoliittyma;

import java.io.InputStream;
import java.util.Scanner;

public class Kartanlaturi {

    Scanner lukija;
    char[][] kartta;

    public Kartanlaturi() {
        //
    }

    public char[][] lataaKartta(String nimi) {

        InputStream is = getClass().getClassLoader().getResourceAsStream(nimi + ".map");
        String rivitekstina = "";
        int rivi = 0;
        int sarake = 0;
        int korkeus = 0;
        int leveys = 0;
        boolean tiedot = false;

        try {
            lukija = new Scanner(is);

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

            System.out.println("Vasenta yläkulmaa vastaa 0,0 ja oikeaa alakulmaa " + (rivi - 1) + "," + (sarake - 1));
        } catch (Exception e) {
            System.out.println("Ongelma karttaa ladatessa: " + e);
            System.exit(1);
        }

        return kartta;
    }

}
