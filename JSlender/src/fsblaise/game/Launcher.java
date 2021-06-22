package fsblaise.game;

/**JSlender by Blaise
 * @author Oláh Balázs
 * @version 1.0
 */
public class Launcher {
    /**Ez az osztály mindössze annyit csinál, hogy:
     * Inicializál egy game objektumot, ami a játékunk futását intézi el.
     * Majd meghívja ennek az osztálynak a start függvényét, ami elindítja a játékot.
     */
    public static void main(String[] args){
        Game game = new Game("JSlender", 1280, 960);
        game.start();
    }
}
