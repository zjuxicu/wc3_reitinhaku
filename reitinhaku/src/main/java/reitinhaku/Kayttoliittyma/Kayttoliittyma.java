package reitinhaku.Kayttoliittyma;

import java.util.ArrayList;
import java.util.Scanner;

public class Kayttoliittyma {

    private static Scanner lukija;

    public Kayttoliittyma() {

        Scanner lukija = new Scanner(System.in);
        Kartanlaturi kl = new Kartanlaturi();

        System.out.println("Valitse haluamasi kartta:");
        String kartta = lukija.nextLine();
        kl.lataaKartta(kartta);
        /*
        System.out.println("Valitse haluamasi lähtö x-koordinaatti:  ");
        int xLahto = lukija.nextInt();
        System.out.println("Valitse haluamasi lähtö y-koordinaatti:  ");
        int yLahto = lukija.nextInt();

        System.out.println("Valitse haluamasi maali x-koordinaatti:  ");
        int xMaali = lukija.nextInt();
        System.out.println("Valitse haluamasi maali y-koordinaatti:  ");
        int yMaali = lukija.nextInt();

        System.out.println("Valitse haluamasi algoritmi: ");
        */
    }
}
