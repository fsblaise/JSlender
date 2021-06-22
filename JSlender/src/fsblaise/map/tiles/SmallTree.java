package fsblaise.map.tiles;

/**Ez az osztály felelős:
 * A nagy méretű fa adataiért, tulajdonságaiért.
 * A nagy méretű fa nem solid, azaz bejárható a játékos által.
 * Alapértelmezetten NEM tartalmazhat, és nem is tartalmaz papírt.
 *
 * Továbbá ez az osztály gyereke a Tile osztálynak.
 */
public class SmallTree extends Tile{

    /**A konstrukora
     * Ezeket a paramétereket várja:
     *
     * @param x X koordináta
     * @param y Y koordináta
     * @param width Szélesség
     * @param height Magasság
     * @param type Tile típus, azaz, hogy milyen textúra legyen betöltve, ez lényegében majd egy képet fog átadni, mert a TileType képet tárol
     */
    public SmallTree(int x, int y, int width, int height, TileType type) {
        super(x, y, width, height, type);
        super.solid = false;
        super.container = false;
        this.containingPaper = false;
    }
}
