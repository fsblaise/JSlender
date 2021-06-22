package fsblaise.game;

import javax.swing.*;
import java.awt.*;

/**Ez az osztály létrehozza az ablakot, illetve
 * az abban elhelyezkedő vásznat, amire később
 * megtörténik a kirajzolás.
 */
public class Display {
    private JFrame frame; //ablak
    private Canvas canvas; //erre fogok rajzolni dolgokat

    private final String title; //ablak címe
    private final int width;
    private final int height;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    /**Ez a függvény létrehozza:
     * az ablakot,
     * azon belül pedig a vásznat, amire rajzolunk.
     * Az ablak nem lesz átméretezhető, illetve a mérete is fix.
     * Ezeken kívül pedig indításkor fókuszba kerül, így nem kell belekattintani.
     */
    private void createDisplay(){
        ///////////////JFRAME/////////////////
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //normálisan bezáródik
        frame.setResizable(false); //ne legyen átméretezhető
        frame.setLocationRelativeTo(null); //középre tegyük
        frame.setVisible(true);

        ///////////////CANVAS////////////////
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }
}
