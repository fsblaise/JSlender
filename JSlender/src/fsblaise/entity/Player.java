package fsblaise.entity;

import fsblaise.game.Game;
import fsblaise.map.tiles.*;

import java.util.Random;

/**Ez az osztály felelős:
 * A player fontos adatainak tárolásáért (mozgott-e, hány papírja van).
 * A konstruktora lerakja a játékost egy megfelelő sarokba, ami nem solid.
 * Ha minden sarok foglalt, akkor random leteszi valahova, ami nem solid.
 * Felelős még a player mozgatásáért.
 * Illetve a papírlapok mennyiségének számolásáért.
 */
public class Player extends Entity {
    private int paperCount;
    private final Game game;
    private boolean moved;

    public Player(Tile[][] mapgrid, Game game) {
        //valszeg a 0,0 pozin fog letrejonni
        this.game = game;
        this.paperCount = 0;

        if(!mapgrid[0][0].isSolid()){
            this.posx = 0;
            this.posy = 0;
            return;
        }
        if(!mapgrid[14][0].isSolid()){
            this.posx = 14*64;
            this.posy = 0;
            return;
        }
        if(!mapgrid[0][14].isSolid()){
            this.posx = 0;
            this.posy = 14*64;
            return;
        }
        if(!mapgrid[14][14].isSolid()) {
            this.posx = 14 * 64;
            this.posy = 14 * 64;
            return;
        }

        //Ha nincs szabad sarok, akkor random lerakja valahova a játékost.
        Random rnd = new Random();
        while(true){
            int x = rnd.nextInt(15);
            int y = rnd.nextInt(15);
            if(!mapgrid[x][y].isSolid()){
                this.posx = x * 64;
                this.posy = y * 64;
                break;
            }
        }
    }

    /**Ez a függvény felelős, azért, hogy a playert mozgassuk a gombnyomásoknak köszönhetően
     *
     * @param mapgrid Vár egy mapgridet, ami a tile-okat és azok tulajdonságait tárolja, itt most a solid fontos csak,
     *                mert a játékos csak nem solid mezőkre léphet.
     */
    public void setPosxy(Tile[][] mapgrid) {
        if(game.getKeyManager().w){
            setMoved(this.setPosxy((posx)/64, (posy-64)/64, mapgrid));
        }
        if(game.getKeyManager().a){
            setMoved(this.setPosxy((posx-64)/64, (posy)/64, mapgrid));
        }
        if(game.getKeyManager().s){
            setMoved(this.setPosxy((posx)/64, (posy+64)/64, mapgrid));
        }
        if(game.getKeyManager().d){
            setMoved(this.setPosxy((posx+64)/64, (posy)/64, mapgrid));
        }
    }

    ////////////////////Getter-Setter///////////////////////////////
    public int getPaperCount() {
        return paperCount;
    }

    /**Ez a függvény növeli a papírlapok számát, ha felvettük azt.
     *
     * @return Visszatér egy igazzal vagy hamissal, ugyanis ez alapján fogjuk a papírlapot eltüntetni az adott mezőről.
     */
    public boolean setPaperCount() {
        if(game.getKeyManager().space){
            this.paperCount++;
            return true; //ha felvette, akkor igazzal tér vissza
        }
        return false;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean isMoved() {
        return moved;
    }

    ////////////////////////////////////////////////////////////////
}
