package fsblaise.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Upload {
    private final int[][] layout;
    private int[][] uploaded = new int[15][15];

    /**Az upload class, először meghívja a read függvényt, ami beolvassa a felhasználó saját pályáját, vagy null-ra álíltja azt, ha hibás az input.
     * Ha az input nem null, akkor a felhasználó pályája lesz betöltve.
     * Ha viszont null, akkor az alapértelmezett pályát fogja betölteni a játék.
     */
    public Upload() {
        read();
        if(uploaded != null){
            this.layout = uploaded;
        }
        else {
            this.layout = new int[][]{
                    {21 , 22 , 23 , 0  , 0  , 0  , 51 , 52 , 53 , 0  , 0  , 1  , 0  , 0  , 0  },
                    {24 , 25 , 26 , 1  , 0  , 0  , 54 , 55 , 56 , 0  , 0  , 0  , 0  , 1  , 0  },
                    {27 , 28 , 29 , 0  , 0  , 0  , 0  , 0  , 904, 901, 908, 901, 901, 905, 0  },
                    {0  , 0  , 0  , 31 , 32 , 33 , 34 , 0  , 912, 912, 902, 912, 912, 902, 0  },
                    {1  , 0  , 0  , 35 , 36 , 37 , 38 , 0  , 902, 912, 902, 912, 912, 902, 0  },
                    {0  , 0  , 0  , 0  , 0  , 0  , 0  , 1  , 902, 912, 902, 912, 912, 902, 1  },
                    {0  , 0  , 0  , 0  , 0  , 0  , 0  , 0  , 902, 912, 902, 912, 912, 902, 0  },
                    {21 , 22 , 23 , 0  , 0  , 0  , 61 , 62 , 902, 912, 912, 912, 912, 902, 0  },
                    {24 , 25 , 26 , 0  , 0  , 0  , 63 , 64 , 907, 901, 901, 901, 912, 906, 0  },
                    {27 , 28 , 29 , 0  , 0  , 0  , 65 , 66 , 0  , 0  , 0  , 0  , 0  , 11 , 12 },
                    {0  , 0  , 1  , 0  , 0  , 11 , 12 , 0  , 0  , 801, 802, 803, 0  , 13 , 14 },
                    {0  , 0  , 0  , 0  , 0  , 13 , 14 , 0  , 0  , 804, 805, 806, 0  , 0  , 0  },
                    {0  , 0  , 0  , 0  , 1  , 0  , 0  , 0  , 0  , 807, 808, 809, 1  , 0  , 0  },
                    {11 , 12 , 0  , 0  , 11 , 12 , 0  , 0  , 0  , 810, 811, 812, 1  , 0  , 0  },
                    {13 , 14 , 0  , 0  , 13 , 14 , 0  , 0  , 0  , 813, 814, 815, 0  , 0  , 0  },
            };
        }
    }


    /**Ha saját pályát szeretnél készíteni:
     * 0, vagy bármilyen ismeretlen szám = fű
     * 1 = kis méretű fa
     * 11-14 = nagy méretű fa (bal felső saroktól a jobb alsóig való haladás szükséges! (2-2 szám soronként = 2x2))
     * 21-29 = Kő (bal felső saroktól a jobb alsóig halad, (3x3))
     * 31-38 = Vízszintes hordó (4x2) -> 4 van egy sorban, és 2 sor van
     * 41-48 = Függőleges hordó (2x4)
     * 51-56 = Vízszintes autó (3x2)
     * 61-66 = Függőleges autó (2x3)
     * 701-715 = Vízszintes teherautó (5x3)
     * 801-815 = Függőleges teherautó (3x5)
     * HÁZ:
     * 901 = Vízszintes fal
     * 902 = Függőleges fal
     * 903 = Keresztfal
     * 904 = Bal felső sarokelem
     * 905 = Jobb felső sarokelem
     * 906 = Jobb alsó sarokelem
     * 907 = Bal alsó sarokelem
     * 908 = T fal, a szára lefelé néz
     * 909 = T fal, a szára balra néz
     * 910 = T fal, a szára felfelé néz
     * 911 = T fal, a szára jobbra néz
     * 912 = Járható mező a házban


     *Ez a függvény a megadott fájlt ellenőrzi:
     * Ha nem létezik, vagy nem txt, akkor visszadob egy hibát, alapértelmezett értékkel indul a játék
     * (a feltöltött tömböt null-ra állítja, később ez alapján lesz betöltve az alapértelmezett pálya).
     * Ha nem számok vannak benne (ha fájlt készítenél, csakis számok és vesszők lehetnek, semmiféle whitespace karakter nem lehet benne),
     * akkor visszadob egy hibát, alapértelmezett értékkel indul a játék.
     * Egyéb esetben, ha minden optimális, akkor a felhasználó pályáját inicializálja, azaz beolvassa a fájl tartalmát.
     *
     * Ha véletlen hosszabb lenne egy sor, azaz 15-nél több mezőt szeretnénk beolvasatni, akkor az extra mezőket figyelmen kívül hagyja a program.
     * Ha pedig rövidebb, akkor a pálya fennmaradó része fűből fog állni.
     * Ha kevesebb sor van szintén fűvel pótol a játék.
     */
    private void read(){
        FileChooser file = new FileChooser();
        String path = file.getPath();
        if(path.equals("")){
            System.err.println("Nem választott ki fájlt, az alapértelmezett pálya fog betöltődni.");
            uploaded = null;
            return;
        }
        if(!path.contains("txt")){
            System.err.println("A fájl nem txt formátumú, az alapértelmezett pálya fog betöltődni.");
            uploaded = null;
            return;
        }
        Scanner sc;
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            System.err.println("Fájl nem található, az alapértelmezett pálya fog betöltődni.");
            uploaded = null;
            return;
        }
        //Beolvassa a fájlban lévő sorokat, ahol az adatok vesszővel vannak elválasztva, egy mátrixba.
        while(sc.hasNextLine()){
            for (int i = 0; i < uploaded.length; i++) {
                String[] line;
                try{
                    line = sc.nextLine().trim().split(",");
                }catch (NoSuchElementException e){
                    return;
                }
                for (int j = 0; j < line.length; j++) {
                    try{
                        uploaded[i][j] = Integer.parseInt(line[j]);
                    }catch (NumberFormatException e){
                        System.err.println("A fájlban nem számok vannak! \nAz alapértelmezett pálya fog betöltődni.");
                        uploaded = null;
                        return;
                    }
                }
            }
        }
    }

    public int[][] getLayout() {
        return layout;
    }
}
