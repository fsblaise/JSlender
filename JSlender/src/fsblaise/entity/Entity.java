package fsblaise.entity;

import fsblaise.map.tiles.Tile;

/**Ez az osztály felelős az entitásokért.
 * Ilyen a játékos és a Slenderman.
 * Ezeket az entitásokat jellemző változókat tárol ez az osztály,
 * illetve a player mozgatását megvalósító függvényt.
 */
public class Entity {
    protected final int sizex = 64;
    protected final int sizey = 64;
    protected int posx;
    protected int posy;

    public int getSizex() {
        return sizex;
    }

    public int getSizey() {
        return sizey;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    /**Ez a függvény mozgatja a playert
     *
     * @param x egy x koordináta, ahova szeretnénk, hogy kerüljön a játékos
     * @param y egy y koordináta, ahova szeretnénk, hogy kerüljön a játékos
     * @param mapgrid vár egy mapgrid-et is, ami ugye a különböző tile-okat tartalmazza, illetve azok adatait
     *                pl.: solid, van-e rajta papír
     *                     Itt a solid-ra van szükség, ugyanis, a player nem léphet solid tile(mező)-ra
     * @return Igaz-hamissal tér vissza, ugyanis ha a player pozícióját változtatjuk, akkor
     * a Slendermannak is kell változtatni a pozícióját.
     */
    public boolean setPosxy(int x, int y, Tile[][] mapgrid) {
        if (x > 14 || y > 14 || y < 0 || x < 0)
            return false;
        if(!mapgrid[x][y].isSolid()){
            this.posy = y*64;
            this.posx = x*64;
            return true;
        }
        return false;
    }
}
