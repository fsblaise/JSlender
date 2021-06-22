package fsblaise;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**Ez az osztály felelős a képek betöltéséért.
 * Ezt a betöltést, csak egyszer végzi el, így nem fog futási problémákat okozni.
 * Minden 1x1-esnél nagyobb tereptárgyat darabonként tárol el a program, rendszerint
 * a tereptárgy bal felső sarkából halad a jobb alsó sarka felé.
 */
public class Image {

    //BufferedImage adattagok
    //Textúrák
    public static final BufferedImage gameover = loadImage("images/gameover2.png");
    public static final BufferedImage win = loadImage("images/win.png");
    public static final BufferedImage sideboard = loadImage("images/board.png");
    public static final BufferedImage grass = loadImage("images/grass.png");
    public static final BufferedImage paper = loadImage("images/paper.png");
    public static final BufferedImage player = loadImage("images/player.png");
    public static final BufferedImage slenderman = loadImage("images/slenderman.png");
    public static final BufferedImage smalltree = loadImage("images/smalltree.png");
    public static final BufferedImage BT1 = loadImage("images/bigtree/1.png");
    public static final BufferedImage BT2 = loadImage("images/bigtree/2.png");
    public static final BufferedImage BT3 = loadImage("images/bigtree/3.png");
    public static final BufferedImage BT4 = loadImage("images/bigtree/4.png");
    public static final BufferedImage R1 = loadImage("images/rock/1.png");
    public static final BufferedImage R2 = loadImage("images/rock/2.png");
    public static final BufferedImage R3 = loadImage("images/rock/3.png");
    public static final BufferedImage R4 = loadImage("images/rock/4.png");
    public static final BufferedImage R5 = loadImage("images/rock/5.png");
    public static final BufferedImage R6 = loadImage("images/rock/6.png");
    public static final BufferedImage R7 = loadImage("images/rock/7.png");
    public static final BufferedImage R8 = loadImage("images/rock/8.png");
    public static final BufferedImage R9 = loadImage("images/rock/9.png");
    public static final BufferedImage DH1 = loadImage("images/drumhorizontal/1.png");
    public static final BufferedImage DH2 = loadImage("images/drumhorizontal/2.png");
    public static final BufferedImage DH3 = loadImage("images/drumhorizontal/3.png");
    public static final BufferedImage DH4 = loadImage("images/drumhorizontal/4.png");
    public static final BufferedImage DH5 = loadImage("images/drumhorizontal/5.png");
    public static final BufferedImage DH6 = loadImage("images/drumhorizontal/6.png");
    public static final BufferedImage DH7 = loadImage("images/drumhorizontal/7.png");
    public static final BufferedImage DH8 = loadImage("images/drumhorizontal/8.png");
    public static final BufferedImage DV1 = loadImage("images/drumvertical/1.png");
    public static final BufferedImage DV2 = loadImage("images/drumvertical/2.png");
    public static final BufferedImage DV3 = loadImage("images/drumvertical/3.png");
    public static final BufferedImage DV4 = loadImage("images/drumvertical/4.png");
    public static final BufferedImage DV5 = loadImage("images/drumvertical/5.png");
    public static final BufferedImage DV6 = loadImage("images/drumvertical/6.png");
    public static final BufferedImage DV7 = loadImage("images/drumvertical/7.png");
    public static final BufferedImage DV8 = loadImage("images/drumvertical/8.png");
    public static final BufferedImage CH1 = loadImage("images/carhorizontal/1.png");
    public static final BufferedImage CH2 = loadImage("images/carhorizontal/2.png");
    public static final BufferedImage CH3 = loadImage("images/carhorizontal/3.png");
    public static final BufferedImage CH4 = loadImage("images/carhorizontal/4.png");
    public static final BufferedImage CH5 = loadImage("images/carhorizontal/5.png");
    public static final BufferedImage CH6 = loadImage("images/carhorizontal/6.png");
    public static final BufferedImage CV1 = loadImage("images/carvertical/1.png");
    public static final BufferedImage CV2 = loadImage("images/carvertical/2.png");
    public static final BufferedImage CV3 = loadImage("images/carvertical/3.png");
    public static final BufferedImage CV4 = loadImage("images/carvertical/4.png");
    public static final BufferedImage CV5 = loadImage("images/carvertical/5.png");
    public static final BufferedImage CV6 = loadImage("images/carvertical/6.png");

    public static final BufferedImage TH1 = loadImage("images/truckhorizontal/1.png");
    public static final BufferedImage TH2 = loadImage("images/truckhorizontal/2.png");
    public static final BufferedImage TH3 = loadImage("images/truckhorizontal/3.png");
    public static final BufferedImage TH4 = loadImage("images/truckhorizontal/4.png");
    public static final BufferedImage TH5 = loadImage("images/truckhorizontal/5.png");
    public static final BufferedImage TH6 = loadImage("images/truckhorizontal/6.png");
    public static final BufferedImage TH7 = loadImage("images/truckhorizontal/7.png");
    public static final BufferedImage TH8 = loadImage("images/truckhorizontal/8.png");
    public static final BufferedImage TH9 = loadImage("images/truckhorizontal/9.png");
    public static final BufferedImage TH10 = loadImage("images/truckhorizontal/10.png");
    public static final BufferedImage TH11 = loadImage("images/truckhorizontal/11.png");
    public static final BufferedImage TH12 = loadImage("images/truckhorizontal/12.png");
    public static final BufferedImage TH13 = loadImage("images/truckhorizontal/13.png");
    public static final BufferedImage TH14 = loadImage("images/truckhorizontal/14.png");
    public static final BufferedImage TH15 = loadImage("images/truckhorizontal/15.png");

    public static final BufferedImage TV1 = loadImage("images/truckvertical/1.png");
    public static final BufferedImage TV2 = loadImage("images/truckvertical/2.png");
    public static final BufferedImage TV3 = loadImage("images/truckvertical/3.png");
    public static final BufferedImage TV4 = loadImage("images/truckvertical/4.png");
    public static final BufferedImage TV5 = loadImage("images/truckvertical/5.png");
    public static final BufferedImage TV6 = loadImage("images/truckvertical/6.png");
    public static final BufferedImage TV7 = loadImage("images/truckvertical/7.png");
    public static final BufferedImage TV8 = loadImage("images/truckvertical/8.png");
    public static final BufferedImage TV9 = loadImage("images/truckvertical/9.png");
    public static final BufferedImage TV10 = loadImage("images/truckvertical/10.png");
    public static final BufferedImage TV11 = loadImage("images/truckvertical/11.png");
    public static final BufferedImage TV12 = loadImage("images/truckvertical/12.png");
    public static final BufferedImage TV13 = loadImage("images/truckvertical/13.png");
    public static final BufferedImage TV14 = loadImage("images/truckvertical/14.png");
    public static final BufferedImage TV15 = loadImage("images/truckvertical/15.png");

    public static final BufferedImage H1 = loadImage("images/house/1.png");
    public static final BufferedImage H2 = loadImage("images/house/2.png");
    public static final BufferedImage H3 = loadImage("images/house/3.png");
    public static final BufferedImage H4 = loadImage("images/house/4.png");
    public static final BufferedImage H5 = loadImage("images/house/5.png");
    public static final BufferedImage H6 = loadImage("images/house/6.png");
    public static final BufferedImage H7 = loadImage("images/house/7.png");
    public static final BufferedImage H8 = loadImage("images/house/8.png");
    public static final BufferedImage H9 = loadImage("images/house/9.png");
    public static final BufferedImage H10 = loadImage("images/house/10.png");
    public static final BufferedImage H11 = loadImage("images/house/11.png");
    public static final BufferedImage H12 = loadImage("images/house/12.png");

    /**Ez a függvény olvassa be a képfájlt,
     * ha nem találja meg a fájlt, akkor elkapja a hibát,
     * majd kilép 1-es hibakóddal a programból.
     *
     * @param path Paraméterben várj a fájl elérési útvonalát.
     * @return Visszatér egy BufferedImage objektummal, azaz egy képpel, ami be van bufferezve.
     *         Ezt a képet fogjuk később kirajzolni a játékban.
     */
    public static BufferedImage loadImage (String path){
        try {
            //Beolvassa a fájlt a path útvonalról
            return ImageIO.read(Image.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
