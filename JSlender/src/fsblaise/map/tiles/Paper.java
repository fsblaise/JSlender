package fsblaise.map.tiles;

import fsblaise.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**Ez az osztály felelős:
 * A papírok legenerálásáért,
 * Illetve felkereséséért.
 */
public class Paper extends Tile {

    private boolean draw = false;
    private final Map<Integer, Integer> invalidPosCache = new HashMap<>();

    /**Ez a függvény legenerálja a papírokat
     * random helyre, aminek van bejárható szomszédja.
     * Egy tereptárgyra csak egyet generál.
     *
     * @param mapGrid Vár egy mapgridet, ami egy Tile-okból álló mátrix.
     *                Azért van rá szükség, mert meg kell nézni, hogy a mátrix adott
     *                pozícióján lévő Tile tartalmazhat-e papírt, stb.
     */
    public void generatePaper(Tile[][] mapGrid){
        Random rnd = new Random();
        boolean valid = false;
        int x,y;

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if(!mapGrid[i][j].isContainer()){
                    invalidPosCache.put(i,j);
                }
            }
        }

        while(!valid){
            x = rnd.nextInt(15);
            y = rnd.nextInt(15);
            while(invalidPosCache.get(x) != null && invalidPosCache.get(x) == y){
                x = rnd.nextInt(15);
                y = rnd.nextInt(15);
            }

            if(mapGrid[x][y].isContainer()) { // ha a random mezőnk container akkor mehetünk tovább
                outer:for (int i = x - 1; i <= x + 1; i++) { //megnézzük a mező 1 sugarú környezetét
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (j > 14 || i > 14 || j < 0 || i < 0) { //ezek túlindexelnék a tömböt, le kell kezelni
                            continue;
                        }
                        if(!mapGrid[i][j].isSolid()){//ha találunk a környezetben legalább egy nem szolid mezőt, akkor generálhat papírt
                            int bal = 0;
                            int felso = 0;
                            //megkeresi az adott tereptárgy bal felső sarkát
                            if(!(mapGrid[x][y] instanceof Grass)){
                                for (int k = x; k >= 0; k--) { //bal széléig megy
                                    if(mapGrid[k][y].getClass().equals(mapGrid[x][y].getClass())){
                                        bal = k;
                                    }
                                    if(!mapGrid[k][y].getClass().equals(mapGrid[x][y].getClass())) break;
                                }
                                for (int k = y; k >= 0; k--) { //tetejéig megy
                                    if(mapGrid[x][k].getClass().equals(mapGrid[x][y].getClass())){
                                        felso = k;
                                    }
                                    if(!mapGrid[x][k].getClass().equals(mapGrid[x][y].getClass())) break;
                                }
                                for (int k = bal; k < 15; k++) {
                                    for (int l = felso; l < 15; l++) {
                                        if(mapGrid[k][l].getClass().equals(mapGrid[x][y].getClass())){
                                            mapGrid[k][l].setContainer(false);
                                            invalidPosCache.put(k,l);
                                        }
                                        else{
                                            break;
                                        }
                                    }
                                }
                            }

                            valid = true;
                            mapGrid[x][y].setContainingPaper(true);
                            invalidPosCache.put(x,y);

                            this.x = x*64;
                            this.y = y*64;

                            break outer;
                        }
                    }
                }
            }

        }
    }

    public Paper(TileType type, Tile[][] mapGrid) {
        super(0, 0, 64, 64, type);
        generatePaper(mapGrid);
    }

    /**Ez a függvény végzi el a játékoshoz viszonított papírkeresést.
     * Ha talál papírt kirajzolja, ha felvesszük el is tünteti utána a megtalált papírt.
     *
     * @param mapGrid Megkapja a pályát, erre azért van szükség, mert a Tile-okban eltároljuk, hogy van-e rajta papír.
     * @param player Megkapja a játékost, erre azért van szükség, mert a játékos pozíciójához viszonyítva keresünk papírokat.
     * @param papers Megkapja a papírok tömböt, amire azért van szükség, mert végignézzük, hogy melyik papír van a játékoshoz közel.
     *               Csakis azt szeretnénk megjeleníteni.
     */
    public static void papersearch(Tile[][] mapGrid, Player player, Paper[] papers){
        int x = player.getPosx()/64;
        int y = player.getPosy()/64;

        for (int i = y-1; i <= y+1; i++) {
            for (int j = x-1; j <= x+1 ; j++) {
                if (j > 14 || i > 14 || j < 0 || i < 0) {
                    continue;
                }
                //Ha talál papírt a szomszédjaiban, akkor...
                if(mapGrid[j][i].isContainingPaper()){
                    //Megnézi, hogy melyik papír az a szomszéd, amit megtaláltunk,
                    //majd kirajzolja azt.
                    for (int k = 0; k < 8; k++) {
                        if(papers[k].getY() == i*64 && papers[k].getX() == j*64){
                            papers[k].setDraw(true);
                        }
                    }
                    //Ha felvettük a papírt, azaz növeltük azt a változót, akkor
                    //ez a mező többé nem tartalmaz papírt.
                    if(player.setPaperCount()){
                        //System.out.println(player.getPaperCount());
                        mapGrid[j][i].setContainingPaper(false);
                    }
                }
            }
        }
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public boolean isDraw() {
        return draw;
    }
}
