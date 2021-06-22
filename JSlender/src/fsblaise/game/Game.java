package fsblaise.game;

import fsblaise.Image;
import fsblaise.entity.Player;
import fsblaise.entity.Slenderman;
import fsblaise.map.Map;
import fsblaise.map.tiles.Paper;
import fsblaise.map.tiles.TileType;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**Ez az osztály felelős:
 * A különféle grafikus dolgok kirajzolásáért,
 * azaz ez az osztály végzi a renderelést,
 * illetve a játék futását is intézi.
 * Ezen kívül pedig példányosítja az osztályokat, hogy utána ki
 * tudja őket rajzolni, a megállási feltételeket tudja nézni.
 *
 * Összességében, ez felelős a játék futásáért, működéséért.
 */
public class Game extends Display implements Runnable {

    //input lassítás
    private long lastPressProcessed = 0;

    //az ablakhoz szükséges változók
    public int width;
    public int height;
    public String title;

    //egyszeri futtatáshoz, threadinghez szükséges változók
    private boolean running = false;
    private Thread thread;

    //Bevitel, keymanager
    private final KeyManager keyManager;

    //az egyszeri betöltéshez szükséges boolean változók
    private boolean kirajzolt = false;
    private boolean generalt = false;
    private boolean spawnplayer = false;
    private boolean spawnslenderman = false;

    //példányosítás
    private final BufferedImage[][] drawables = new BufferedImage[15][15];
    private final BufferedImage paperimg = TileType.Paper.img;
    private final BufferedImage playerimg = TileType.Player.img;
    private final BufferedImage slendermanimg = TileType.Slenderman.img;
    private final BufferedImage gameoverimg = Image.gameover;
    private final BufferedImage winimg = Image.win;
    private final BufferedImage sideimg = Image.sideboard;
    private final Paper[] papers = new Paper[8];
    private Player player;
    private Slenderman slenderman;

    private Map map;

    //Game konstruktora//
    public Game(String title, int width, int height) {
        super(title, width, height);
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    /**Ez a játék legfőbb függvénye, szerepe:
     * a játékban levő objektumok megjelenítése(renderelése), inicializálása,
     * a különféle objektumok függvényeit meghívja(player mozgatás, slenderman mozgatás, papírok generálási függvénye),
     * teszteli a játék végéhez vezető állapotokat,
     * futási időben érzékeli a billentyűk lenyomását.
     *
     * @throws IOException Ha nem tudja kirenderelni, akkor eldobja, ezt elkapja a run függvény
     */
    private void render() throws IOException {
        BufferStrategy bs = getCanvas().getBufferStrategy(); // Elmenti azt mit rajzolni kell, majd ezt átadja a képernyőnek később,
        if(bs == null){
            getCanvas().createBufferStrategy(3);
            return;
        }
        //rajzoló
        Graphics g = bs.getDrawGraphics(); //ecset, amivel rajzolunk, ez egy rajzolo kb.
        g.clearRect(0,0,width,height); //minden iteráció alatt törli a "rajzolót"

        //Map, tereptárgy-tileok betöltése.
        //Csak egyszer akarjuk betölteni ezeket.
        if(!kirajzolt){
            Upload upload = new Upload();
            map = new Map(upload.getLayout());
            for (int i = 0; i < map.mapGrid.length; i++) {
                for (int j = 0; j < map.mapGrid[i].length; j++) {
                    drawables[i][j] = (map.mapGrid[i][j].getType().img);
                }
            }
        }
        kirajzolt = true;
        //Viszont minden lefutás alatt kirajzoljuk a már betöltött tileokat, képeket.
        for (int i = 0; i < map.mapGrid.length; i++) {
            for (int j = 0; j < map.mapGrid[i].length; j++) {
                g.drawImage(drawables[i][j], map.mapGrid[i][j].getX(), map.mapGrid[i][j].getY(), map.mapGrid[i][j].getWidth(), map.mapGrid[i][j].getHeight(), null);
            }
        }

        //Papírlapok betöltése, randomizált koordinátájú példányokkal.
        //Működési elve ugyanaz, mint a map betöltésének.
        if(!generalt){
            for (int i = 0; i < 8; i++) {
                papers[i] = new Paper(TileType.Paper, map.mapGrid);
            }
            generalt = true;
        }

        //Player spawnolás, úgyanúgy csak egyszer fut le a betöltése,
        //de többször fogjuk kirajzolni.
        if(!spawnplayer){
            player = new Player(map.mapGrid, this);
            spawnplayer = true;
        }

        //Kirajzoljuk az oldalsávot.
        g.drawImage(sideimg, 960, 0, null);
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString(Integer.toString(player.getPaperCount()), 1246, 71); //Kiírja, hány papírlapunk van.

        //A player mozgatásáért felelős, lassítani is kell mivel gyors a program, de nem akarunk száguldani attól.
        if(System.currentTimeMillis() - lastPressProcessed > 100){
            keyManager.tick();
            lastPressProcessed = System.currentTimeMillis();
            //player mozgatása
            player.setPosxy(map.mapGrid);

            //Leteszteli, hogy a player nem ment-e rá arra a tile-ra, ahol a Slenderman tartózkodik.
            //Ha igen, akkor veszít a játékos.
            if(spawnslenderman)
                if(player.getPosx() == slenderman.getPosx() && player.getPosy() == slenderman.getPosy()){
                    g.drawImage(gameoverimg, 0, 0, null);
                    bs.show();
                    g.dispose();
                    stop();
                }

            //Itt mozgatjuk a Slendermant, mivel a player mozgását követve mozog/teleportál ő is.
            if(spawnslenderman && player.isMoved()){
                slenderman.move(player);
                player.setMoved(false);
            }
        }

        //Papír felvétele, renderelése, törlése, felvett papírok növelése.
        Paper.papersearch(map.mapGrid, player, papers);

        //Papírok kirajzolása, akkor és csak akkor, ha már megtaláltuk azokat.
        for (int i = 0; i < 8; i++) {
            if(papers[i].isDraw() && map.mapGrid[papers[i].getX()/64][papers[i].getY()/64].isContainingPaper())
                g.drawImage(paperimg, papers[i].getX(), papers[i].getY(), papers[i].getWidth(), papers[i].getHeight(), null);
        }

        //Kirajzoljuk a playert a mozgatása után.
        g.drawImage(playerimg, player.getPosx(), player.getPosy(), player.getSizex(), player.getSizey(), null);

        //Ha felvettük az első papírt lespawnol a Slenderman, de csakis akkor.
        if(player.getPaperCount() > 0 && !spawnslenderman){
            slenderman = new Slenderman();
            spawnslenderman = true; //spawn Slenderman
        }
        //Ha a Slenderman 3 távolságra van, látjuk, azaz kirendereljük.
        if(spawnslenderman)
            if(Math.abs(player.getPosx()/64 - slenderman.getPosx()/64) + Math.abs(player.getPosy()/64 - slenderman.getPosy()/64) <= 3)
                g.drawImage(slendermanimg, slenderman.getPosx(), slenderman.getPosy(), slenderman.getSizex(), slenderman.getSizey(), null);


        //Endingek tesztelése:
        //teszt-game-over
        if(spawnslenderman){

            //megnézi hogy ugyanazon a tileon van e a slenderman és a player, ha igen akkor meghaltál
            if(player.getPosx() == slenderman.getPosx() && player.getPosy() == slenderman.getPosy()){
                g.drawImage(gameoverimg, 0, 0, null);
                bs.show();
                g.dispose();
                stop();
            }
            //Megnézi, hogy a slenderman-nál a dead változó igaz-e (Akkor igaz, ha megtvalósult az, hogy bizonyos eséllyel pl. 66% elkapott minket)
            //Ha igaz, a player vesztett.
            if(slenderman.isDead()){
                g.drawImage(gameoverimg, 0, 0, null);
                bs.show();
                g.dispose();
                stop();
            }

        }
        //Ha a player összeszed 8 papírt, megnyeri a játékot.
        if(player.getPaperCount() == 8){
            g.drawImage(winimg, 0, 0, null);
            bs.show();
            g.dispose();
            stop();
        }

        //Rajzolás befejezése
        bs.show(); //megjeleníti
        g.dispose(); //kitörli a rajzolóból a dolgokat
    }

    /**Ez a függvény futtatja a játkot:
     * Lekorlátozza a játék sebességét, hogy ne legyen több 60 képkocka/másodpercnél, így minden számítógépen hasonlóan fut a játék.
     * Meghívja a render függvényt, ami kirajzol mindent, illetve kezeli a játék osztályait, példányait.
     * Kiszámolja az FPS-t is.
     */
    public void run(){
        getFrame().addKeyListener(keyManager);
        // ez a függvény felelős a program futásáért

        //Változók a kötött fps-hez, normális futáshoz
        //Ha nem lenne akkor minden gépen máshogy futna, ezt nem akarjuk
        int ticks = 60;
        double timePerTick = 1000000000d/ticks; //1sec / tick
        double delta = 0; //ez lesz a különbség, eltelt idő osztva a timepertick-kel
        long now; //most
        long lastTime = System.nanoTime(); //lekérdezi a rendszer idejét nanosecben
        long timer = 0; //időzítő
        int fps = 0; //fps counter

        //game loop//
        while(running){
            now = System.nanoTime(); //most=rendszer ideje
            delta += (now - lastTime) / timePerTick; //a mostani időből kivonjuk az előzőleg lementett időt, majd ezt osztjuk a timePerTick arányszámmal
            timer += now - lastTime; //a timert növeljük az eltelt idővel
            lastTime = now; //az előző idő most a jelenlegi lesz, mert a kövi iterációban a now nőni fog

            if(delta >= 1){ //csak akkor rajzol, ha nagyobb mint egy, ezzel akadályozzuk meg, hogy minden iterációban rendereljen, úgymond felzárkóztatja a lassabb képeket is
                try {
                    render();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fps++;
                delta--;
            }

            if (timer >= 1000000000){ //másodpercenként írja ki az fps-t, nyílván frames per sec, tehát másodpercenként kell megnézni mekkora a szám, különben fals eredményt kapnánk
                System.out.println("FPS:" + fps);
                fps = 0;
                timer = 0;
            }
        }
        stop();
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    /**Ez a függvény elindítja a játékot,
     * de csakis egyszer,
     * ha már fut, többet nem indul el.
     */
    public synchronized void start(){
        if(running) return; //ha már fut, nem akarjuk ismét futtatni

        running = true;
        thread = new Thread(this);
        thread.start(); //meghívja a run függvényt
    }

    /**Ez a függvény leállítja a játékot,
     * de csakis egyszer,
     * ha már leállt, többet nem fog leállni.
     */
    public synchronized void stop(){
        if(!running) return; // ha be van zárva, nem akarjuk megint bezárni

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
