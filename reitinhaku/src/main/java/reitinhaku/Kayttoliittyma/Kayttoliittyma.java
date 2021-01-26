package reitinhaku.kayttoliittyma;

import java.util.Scanner;

public class Kayttoliittyma {

    private Scanner lukija;

    public Kayttoliittyma() {

        lukija = new Scanner(System.in);
        Kartanlaturi kl = new Kartanlaturi();
        
        System.out.println("Valitse haluamasi kartta:");
        String kartannimi = lukija.nextLine();
        Kartta kartta = new Kartta(kl.lataaKartta(kartannimi), kartannimi);
        
        System.out.println("Valitse haluamasi lähdön x-koordinaatti:  ");
        int xLahto = lukija.nextInt();
        System.out.println("Valitse haluamasi lähdön y-koordinaatti:  ");
        int yLahto = lukija.nextInt();
        kartta.asetaLahto(xLahto, yLahto);

        System.out.println("Valitse haluamasi maalin x-koordinaatti:  ");
        int xMaali = lukija.nextInt();
        System.out.println("Valitse haluamasi maalin y-koordinaatti:  ");
        int yMaali = lukija.nextInt();
        kartta.asetaMaali(xMaali, yMaali);

        System.out.println("Haetaan reittiä kartasta " + kartannimi + " väliltä (" + xLahto + "," + yLahto + ") --> (" + xMaali + "," + yMaali +")");
        System.out.println("Linnuntietä matka on ~" + kartta.linnuntie());
        AlgoritminValitsija av = new AlgoritminValitsija(kartta);

    }
}
