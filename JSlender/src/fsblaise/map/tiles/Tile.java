package fsblaise.map.tiles;

/**Ez az osztály felelős a Tile-ok tulajdonságaiért, műveleteiért.
 * Ebből fog öröklődni az összes tereptárgy, illetve a Papír osztály is.
 * Csak adattagokat, settereket és gettereket tartalmaz.
 */
public class Tile {

    protected int x,y, width, height;
    protected TileType type; //ez a tile típusa, lényegében hivatkozik, majd a képfájlra
    protected boolean solid;
    protected boolean container;
    protected boolean containingPaper;

    public Tile(int x, int y, int width, int height, TileType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isContainer() {
        return container;
    }

    public boolean isContainingPaper() {
        return containingPaper;
    }

    public void setContainingPaper(boolean containingPaper) {
        this.containingPaper = containingPaper;
    }

    public void setContainer(boolean container) {
        this.container = container;
    }
}
