package fsblaise.map;

import fsblaise.map.tiles.*;

/**Ez az osztály felelős a játék mezőinek adatiért, tulajdonságaiért,
 * illetve egy Tile-okból álló mátrixot hoz létre.
 */
public class Map{
    public Tile[][] mapGrid;

    /**Ez a függvény a Tile-okból álló mátrixot hozza létre,
     * méretet, pozíciót ad a Tile-oknak,
     * ebbe fogja feltölteni a különféle TileType-okat,
     * azaz a különféle textúrákat.
     *
     * @param newMap Az osztály konstruktora továbbá vár egy int-ekből álló mátrixot, ami
     *               vagy a felhasználó által megadott fájlból származik, vagy az
     *               alapértelmezett int mátrix.
     *
     *               Ebben a mátrixban minden Tile-nak különböző id-je van,
     *               ezeket kezeli a switch a konstruktorban úgy, hogy végigjárja a mátrixot és
     *               az id alapján példányosítja a Tile-okat.
     *               Ha ismeretlen számot talál, alapértelmezetten füvet rak le a játék.
     *               (Fű példányt hoz létre.)
     *
     */
    public Map(int[][] newMap) {
        //Eme osztaly csinalja a map letrehozasaval kapcsolatos dolgokat
        //Fixen 15x15 a map, nem lehet se kisebb, se nagyobb, ezért final lesz
        int sizex = 15;
        int sizey = 15;
        mapGrid = new Tile[sizex][sizey];
        for (int i = 0; i < mapGrid.length; i++) {
            for (int j = 0; j < mapGrid[i].length; j++) {
                //Ez a switch felelős a számkódok kezeléséért, hibás input esetén fű lesz lerakva
                switch (newMap[j][i]){
                    //Small Tree//
                    case 1: mapGrid[i][j] = new SmallTree(i*64,j*64, 64, 64, TileType.Tree); break;
                    //Big Tree//
                    case 11: mapGrid[i][j] = new BigTree(i*64,j*64, 64, 64, TileType.BT1, false); break;
                    case 12: mapGrid[i][j] = new BigTree(i*64,j*64, 64, 64, TileType.BT2, false); break;
                    case 13: mapGrid[i][j] = new BigTree(i*64,j*64, 64, 64, TileType.BT3, false); break;
                    case 14: mapGrid[i][j] = new BigTree(i*64,j*64, 64, 64, TileType.BT4, false); break;
                    //Rock//
                    case 21: mapGrid[i][j] = new Rock(i*64,j*64, 64, 64, TileType.R1, true, false); break;
                    case 22: mapGrid[i][j] = new Rock(i*64,j*64, 64, 64, TileType.R2, true, false); break;
                    case 23: mapGrid[i][j] = new Rock(i*64,j*64, 64, 64, TileType.R3, true, false); break;
                    case 24: mapGrid[i][j] = new Rock(i*64,j*64, 64, 64, TileType.R4, true, false); break;
                    case 25: mapGrid[i][j] = new Rock(i*64,j*64, 64, 64, TileType.R5, false, false); break;
                    case 26: mapGrid[i][j] = new Rock(i*64,j*64, 64, 64, TileType.R6, true, false); break;
                    case 27: mapGrid[i][j] = new Rock(i*64,j*64, 64, 64, TileType.R7, true, false); break;
                    case 28: mapGrid[i][j] = new Rock(i*64,j*64, 64, 64, TileType.R8, true, false); break;
                    case 29: mapGrid[i][j] = new Rock(i*64,j*64, 64, 64, TileType.R9, true, false); break;
                    //Drum-horizontal//
                    case 31: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DH1, true, false ); break;
                    case 32: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DH2, true, false ); break;
                    case 33: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DH3, true, false ); break;
                    case 34: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DH4, true, false ); break;
                    case 35: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DH5, true, false); break;
                    case 36: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DH6, true, false); break;
                    case 37: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DH7, true, false); break;
                    case 38: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DH8, true, false); break;
                    //Drum-vertical//
                    case 41: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DV1, true, false); break;
                    case 42: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DV2, true, false); break;
                    case 43: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DV3, true, false); break;
                    case 44: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DV4, true, false); break;
                    case 45: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DV5, true, false); break;
                    case 46: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DV6, true, false); break;
                    case 47: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DV7, true, false); break;
                    case 48: mapGrid[i][j] = new Drum(i*64,j*64, 64, 64, TileType.DV8, true, false); break;
                    //Car-horizontal//
                    case 51: mapGrid[i][j] = new Car(i*64,j*64, 64, 64, TileType.CH1, true, false); break;
                    case 52: mapGrid[i][j] = new Car(i*64,j*64, 64, 64, TileType.CH2, true, false); break;
                    case 53: mapGrid[i][j] = new Car(i*64,j*64, 64, 64, TileType.CH3, true, false); break;
                    case 54: mapGrid[i][j] = new Car(i*64,j*64, 64, 64, TileType.CH4, true, false); break;
                    case 55: mapGrid[i][j] = new Car(i*64,j*64, 64, 64, TileType.CH5, true, false); break;
                    case 56: mapGrid[i][j] = new Car(i*64,j*64, 64, 64, TileType.CH6, true, false); break;
                    //Car-vertical//
                    case 61: mapGrid[i][j] = new Car(i*64,j*64, 64, 64, TileType.CV1, true, false); break;
                    case 62: mapGrid[i][j] = new Car(i*64,j*64, 64, 64, TileType.CV2, true, false); break;
                    case 63: mapGrid[i][j] = new Car(i*64,j*64, 64, 64, TileType.CV3, true, false); break;
                    case 64: mapGrid[i][j] = new Car(i*64,j*64, 64, 64, TileType.CV4, true, false); break;
                    case 65: mapGrid[i][j] = new Car(i*64,j*64, 64, 64, TileType.CV5, true, false); break;
                    case 66: mapGrid[i][j] = new Car(i*64,j*64, 64, 64, TileType.CV6, true, false); break;
                    //Truck-horizontal//
                    case 701: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH1, true, false); break;
                    case 702: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH2, true, false); break;
                    case 703: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH3, true, false); break;
                    case 704: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH4, true, false); break;
                    case 705: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH5, true, false); break;
                    case 706: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH6, true, false); break;
                    case 707: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH7, false, false); break;
                    case 708: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH8, false, false); break;
                    case 709: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH9, false, false); break;
                    case 710: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH10, true, false); break;
                    case 711: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH11, true, false); break;
                    case 712: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH12, true, false); break;
                    case 713: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH13, true, false); break;
                    case 714: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH14, true, false); break;
                    case 715: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TH15, true, false); break;
                    //Truck-vertical//
                    case 801: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV1, true, false); break;
                    case 802: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV2, true, false); break;
                    case 803: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV3, true, false); break;
                    case 804: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV4, true, false); break;
                    case 805: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV5, false, false); break;
                    case 806: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV6, true, false); break;
                    case 807: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV7, true, false); break;
                    case 808: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV8, false, false); break;
                    case 809: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV9, true, false); break;
                    case 810: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV10, true, false); break;
                    case 811: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV11, false, false); break;
                    case 812: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV12, true, false); break;
                    case 813: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV13, true, false); break;
                    case 814: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV14, true, false); break;
                    case 815: mapGrid[i][j] = new Truck(i*64,j*64, 64, 64, TileType.TV15, true, false); break;
                    //House-pieces//
                    case 901: mapGrid[i][j] = new House(i*64,j*64, 64, 64, TileType.H1, true, false, false); break;
                    case 902: mapGrid[i][j] = new House(i*64,j*64, 64, 64, TileType.H2, true, false, false); break;
                    case 903: mapGrid[i][j] = new House(i*64,j*64, 64, 64, TileType.H3, true, false, false); break;
                    case 904: mapGrid[i][j] = new House(i*64,j*64, 64, 64, TileType.H4, true, false, false); break;
                    case 905: mapGrid[i][j] = new House(i*64,j*64, 64, 64, TileType.H5, true, false, false); break;
                    case 906: mapGrid[i][j] = new House(i*64,j*64, 64, 64, TileType.H6, true, false, false); break;
                    case 907: mapGrid[i][j] = new House(i*64,j*64, 64, 64, TileType.H7, true, false, false); break;
                    case 908: mapGrid[i][j] = new House(i*64,j*64, 64, 64, TileType.H8, true, false, false); break;
                    case 909: mapGrid[i][j] = new House(i*64,j*64, 64, 64, TileType.H9, true, false, false); break;
                    case 910: mapGrid[i][j] = new House(i*64,j*64, 64, 64, TileType.H10, true, false, false); break;
                    case 911: mapGrid[i][j] = new House(i*64,j*64, 64, 64, TileType.H11, true, false, false); break;
                    case 912: mapGrid[i][j] = new House(i*64,j*64, 64, 64, TileType.H12, false, true, false); break;
                    default: mapGrid[i][j] = new Grass(i*64,j*64, 64, 64, TileType.Grass, false); break;
                }
            }
        }
    }
}
