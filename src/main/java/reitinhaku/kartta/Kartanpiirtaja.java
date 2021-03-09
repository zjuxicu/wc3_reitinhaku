package reitinhaku.kartta;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Kartanpiirtaja {
    private final double xMax, yMax;
    private BufferedImage buffIm;
    private WritableRaster wr;

    public Kartanpiirtaja(float xM, float yM) {
        xMax = xM;
        yMax = yM;
        buffIm = new BufferedImage((int) xMax, (int) yMax, BufferedImage.TYPE_INT_RGB);
        wr = buffIm.getRaster();
    }

    /**
     * Piirtää kartalle rastin jolla merkataan lähtöä ja maalia.
     * 
     * @param x
     * @param y
     * @param vari
     */
    public void kohde(int x, int y, int[] vari) {
        int[][] naapurit = { { x + 1, y }, { x - 1, y }, { x, y + 1, }, { x, y - 1 }, { x + 1, y + 1 },
                { x - 1, y - 1 }, { x + 1, y - 1 }, { x - 1, y + 1 }, { x + 2, y + 1 }, { x - 2, y - 1 },
                { x + 2, y - 1 }, { x - 2, y + 1 }, { x + 2, y + 2 }, { x - 2, y - 2 }, { x + 2, y - 2 },
                { x - 2, y + 2 } };
        for (int i = 0; i < naapurit.length; i++) {
            try {
                pikseli(naapurit[i][0], naapurit[i][1], vari);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Piirtää kartalle yhden pikselin halutulla värillä.
     * 
     * @param x
     * @param y
     * @param vari
     */
    public void pikseli(float x, float y, int[] vari) {
        wr.setPixel((int) x, (int) y, vari);
    }

    /**
     * Tallentaa kuvan annetulla nimellä.
     * 
     * @param nimi
     */
    public void tulosta(String nimi) {
        try {
            ImageIO.write(buffIm, "png", new File(nimi + ".png"));
        } catch (IOException e) {
            System.err.println("Virhe kuvan tallennuksessa.");
        }
    }

    /**
     * Piirtää halutun värisen viivan kartalle, käytetään polun rakentamisessa.
     * 
     * @param x
     * @param y
     * @param i
     * @param j
     * @param vari
     */
    public void viiva(int x, int y, int i, int j, int[] vari) {
        if (y == j) {
            if (x < i) {
                while (x != i) {
                    pikseli(x, y, vari);
                    x++;
                }
            } else {
                while (x != i) {
                    pikseli(x, y, vari);
                    x--;
                }
            }
        } else if (x == i) {
            if (y < j) {
                while (y != j) {
                    pikseli(x, y, vari);
                    y++;
                }
            } else {
                while (y != j) {
                    pikseli(x, y, vari);
                    y--;
                }
            }
        } else if (x < i) {
            if (y < j) {
                while (x != i && y != j) {
                    pikseli(x, y, vari);
                    y++;
                    x++;
                }
            } else {
                while (x != i && y != j) {
                    pikseli(x, y, vari);
                    y--;
                    x++;
                }
            }
        } else {
            if (y < j) {
                while (x != i && y != j) {
                    pikseli(x, y, vari);
                    y++;
                    x--;
                }
            } else {
                while (x != i && y != j) {
                    pikseli(x, y, vari);
                    y--;
                    x--;
                }
            }
        }
    }
}