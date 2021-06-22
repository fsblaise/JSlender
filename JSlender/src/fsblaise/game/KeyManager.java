package fsblaise.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private final boolean[] keys;
    public boolean w, a, s, d, space;

    public KeyManager() {
        this.keys = new boolean[256];
    }

    /**Ez az osztály felelős azért, hogy
     * mikor a lenyomunk egy gombot, akkor az arra vonatkozó boolean igaz legyen:
     * erre pedig később tesztelünk, ugyanis ezek alapján fog történni a játékos mozgatása,
     * illetve a papírlapok felvétele.
     */
    public void tick(){
        w = keys[KeyEvent.VK_W];
        a = keys[KeyEvent.VK_A];
        s = keys[KeyEvent.VK_S];
        d = keys[KeyEvent.VK_D];
        space = keys[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
