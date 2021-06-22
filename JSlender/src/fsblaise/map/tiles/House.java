package fsblaise.map.tiles;

/**Ez az osztály felelős:
 * A ház adataiért, tulajdonságaiért.
 *
 * Továbbá ez az osztály gyereke a Tile osztálynak.
 */
public class House extends Tile{

    /**A konstrukora
     * Ezeket a paramétereket várja:
     *
     * @param x X koordináta
     * @param y Y koordináta
     * @param width Szélesség
     * @param height Magasság
     * @param type Tile típus, azaz, hogy milyen textúra legyen betöltve, ez lényegében majd egy képet fog átadni, mert a TileType képet tárol
     * @param solid Solid-e, igaz-hamis változó (Ezzel lehet beállítani, hogy bejárható legyen a játékos által.)
     * @param container TartalmazHAT-e papírt, igaz-hamis változó
     * @param containingpaper Tartalmaz-e papírt, igaz-hamis változó
     */
    public House(int x, int y, int width, int height, TileType type, boolean solid, boolean container, boolean containingpaper) {
        super(x, y, width, height, type);
        super.solid = solid;
        super.container = container;
        this.containingPaper = containingpaper;
    }
}
