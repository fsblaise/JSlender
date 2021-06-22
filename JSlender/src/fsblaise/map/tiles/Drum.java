package fsblaise.map.tiles;

/**Ez az osztály felelős:
 * A hordó adataiért, tulajdonságaiért.
 * A hordó solid, azaz nem járható be a játékos által.
 *
 * Továbbá ez az osztály gyereke a Tile osztálynak.
 */
public class Drum extends Tile{

    /**A konstrukora
     * Ezeket a paramétereket várja:
     *
     * @param x X koordináta
     * @param y Y koordináta
     * @param width Szélesség
     * @param height Magasság
     * @param type Tile típus, azaz, hogy milyen textúra legyen betöltve, ez lényegében majd egy képet fog átadni, mert a TileType képet tárol
     * @param container TartalmazHAT-e papírt, igaz-hamis változó
     * @param containingpaper Tartalmaz-e papírt, igaz-hamis változó
     */
    public Drum(int x, int y, int width, int height, TileType type, boolean container, boolean containingpaper) {
        super(x, y, width, height, type);
        super.solid = true;
        this.container = container;
        this.containingPaper = containingpaper;
    }
}
