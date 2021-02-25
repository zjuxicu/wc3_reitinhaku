package reitinhaku.kayttoliittyma;

import java.util.Scanner;
import java.io.File;

import reitinhaku.algoritmit.Koordinaatti;
import reitinhaku.logiikka.*;

public class Kayttoliittyma {

    private static Scanner lukija;

    /**
     * Käyttöliittymä
     * 
     * Käyttöliittymän kautta tapahtuu kartan ja tutkittavien koordinaattien
     * valinta. Mahdollisuus on myös tulostaa ohjeet ja listaus saatavilla olevista
     * kartoista.
     */
    public Kayttoliittyma() {

        lukija = new Scanner(System.in);
        Kartanlaturi laturi = new Kartanlaturi();
        System.out.println("Kirjoita \"ohjeet\", jos haluat nähdä komennot ja ohjeet.");
        System.out.println("Valitse haluamasi kartta:");
        String kartannimi = lukija.nextLine();

        if (kartannimi.equals("ohjeet")) {
            ohjeet();
            System.out.println("Valitse haluamasi kartta:");
            kartannimi = lukija.nextLine();
        }
        if (kartannimi.equals("lista")) {
            listaaKartat();
            System.out.println("Valitse haluamasi kartta:");
            kartannimi = lukija.nextLine();
        }
        if(kartannimi.equals("satunnainen")){
            kartannimi = arvoKartta();
        }
        if (kartannimi.equals("testeri")) { 
            Tester testeri = new Tester(arvoKartta());
            System.exit(0);
        }
        if(kartannimi.equals("aa")){ //käsintestaamisen nopeuttamista varten
            kartannimi = "testi";
            Kartta kartta = new Kartta(laturi.lataaKartta(kartannimi), kartannimi);
            kartta.asetaLahto(0, 0);
            kartta.asetaMaali(4, 4);
            AlgoritminValitsija av = new AlgoritminValitsija(kartta);
            System.exit(0);
        }
        Kartta kartta = new Kartta(laturi.lataaKartta(kartannimi), kartannimi);

        System.out.println("Valitse haluamasi lähdön x-koordinaatti:  ");
        int xLahto = lukija.nextInt();
        System.out.println("Valitse haluamasi lähdön y-koordinaatti:  ");
        int yLahto = lukija.nextInt();
        while (!kartta.asetaLahto(xLahto, yLahto)) {
            System.out.println("Valitse haluamasi lähdön x-koordinaatti:  ");
            xLahto = lukija.nextInt();
            System.out.println("Valitse haluamasi lähdön y-koordinaatti:  ");
            yLahto = lukija.nextInt();
        }

        System.out.println("Valitse haluamasi maalin x-koordinaatti:  ");
        int xMaali = lukija.nextInt();
        System.out.println("Valitse haluamasi maalin y-koordinaatti:  ");
        int yMaali = lukija.nextInt();
        while (!kartta.asetaMaali(xMaali, yMaali)) {
            System.out.println("Valitse haluamasi maalin x-koordinaatti:  ");
            xMaali = lukija.nextInt();
            System.out.println("Valitse haluamasi maalin y-koordinaatti:  ");
            yMaali = lukija.nextInt();
        }

        System.out.println("--------------------------------------------------------------");
        System.out.println();
        Koordinaatti lahto = new Koordinaatti(xLahto, yLahto);
        Koordinaatti maali = new Koordinaatti(xMaali, yMaali);
        if (!kartta.rajojenSisalla(lahto)) {
            System.out.println("Lähtökoordinaatti ei ole kartan rajojen sisällä.");
            System.exit(0);
        }
        if (!kartta.rajojenSisalla(maali)) {
            System.out.println("Maalikoordinaatti ei ole kartan rajojen sisällä.");
            System.exit(0);
        }

        System.out.println("Haetaan reittiä kartasta " + kartannimi + " väliltä (" + xLahto + "," + yLahto + ") --> ("
                + xMaali + "," + yMaali + ")");

        System.out.println("Linnuntietä matka on ~" + kartta.linnuntie(lahto, maali));
        System.out.println();
        System.out.println("--------------------------------------------------------------");
        AlgoritminValitsija av = new AlgoritminValitsija(kartta);

    }

    /**
     * Tulostaa ohjeet ja tarjoaa muutamat esimerkkiarvot.
     */
    public void ohjeet() {
        System.out.println();
        System.out.println("Valitessasi karttaa, kirjoita kartannimi ilman .map-päätettä");
        System.out.println("Toimivat arvot ovat esimerkiksi:");
        System.out.println("thecrucible (400,250) -> (312,400)");
        System.out.println("bootybay (96,55) -> (111,80)");
        System.out.println("gardenofwar (86,134) -> (266,270)");
        System.out.println("battleground (86,134) -> (432,225)");
        System.out.println("Kirjoita \"lista\", jos haluat listauksen kaikista kartoista.");
        System.out.println("Jos haluat ajaa suorituskykytestit, kirjoita \"testeri\"");
        System.out.println("Jos mikä tahansa kartta käy, kirjoita \"satunnainen\"");
        System.out.println();
    }

    /**
     * Tulostaa listauksen saatavilla olevista kartoista.
     */
    public void listaaKartat() {
        File f = new File("src/main/resources");
        File[] flista = f.listFiles();
        for (File file : flista) {
            System.out.println(file.getName());
        }
    }

    /**
     * Metodi arpoo kartan.
     * 
     * @return satunnainen kartta, joka ei ole "testi"
     */
    public String arvoKartta() {
        File f = new File("src/main/resources");
        File[] flista = f.listFiles();
        String kartta = "";

        for (int i = 1; i <= flista.length - 1; i++) {
            int k = (int) System.nanoTime() % i;
            if (k >= 0 && k <= flista.length) {
                if (!flista[k].getName().equals("testi.map")) {
                    kartta = flista[k].getName();
                }
            }

        }
        kartta = kartta.substring(0, kartta.length() - 4);
        return kartta;
    }

}
