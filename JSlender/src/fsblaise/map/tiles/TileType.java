package fsblaise.map.tiles;

import fsblaise.Image;

import java.awt.image.BufferedImage;

/**Ez az enumeráció felelős:
 * A különféle képek, Tile-ok elnevezéséért, a későbbi könnyű hivatkozás miatt.
 * Később tudunk a Tile típusára hivatkozni, ami ezeket az enum tagokat fogja jelenteni.
 * Lényegében itt a típusban csak egy képet tárolunk.
 * Itt a névhez kapcsolunk egy képfájlt, amit az Image osztály fog betölteni.
 */
public enum TileType {
    //Tile textúrák
    Grass(Image.grass),
    Tree(Image.smalltree),
    Paper(Image.paper),
    Player(Image.player),
    Slenderman(Image.slenderman),
    //BigTree = BT
    BT1(Image.BT1),
    BT2(Image.BT2),
    BT3(Image.BT3),
    BT4(Image.BT4),
    //Rock = R
    R1(Image.R1),
    R2(Image.R2),
    R3(Image.R3),
    R4(Image.R4),
    R5(Image.R5),
    R6(Image.R6),
    R7(Image.R7),
    R8(Image.R8),
    R9(Image.R9),
    //Drum-Horizontal = DH
    DH1(Image.DH1),
    DH2(Image.DH2),
    DH3(Image.DH3),
    DH4(Image.DH4),
    DH5(Image.DH5),
    DH6(Image.DH6),
    DH7(Image.DH7),
    DH8(Image.DH8),
    //Drum-Vertical = DV
    DV1(Image.DV1),
    DV2(Image.DV2),
    DV3(Image.DV3),
    DV4(Image.DV4),
    DV5(Image.DV5),
    DV6(Image.DV6),
    DV7(Image.DV7),
    DV8(Image.DV8),
    //Car-Horizontal = CH
    CH1(Image.CH1),
    CH2(Image.CH2),
    CH3(Image.CH3),
    CH4(Image.CH4),
    CH5(Image.CH5),
    CH6(Image.CH6),
    //Car-Vertical = CV (pun-intended)
    CV1(Image.CV1),
    CV2(Image.CV2),
    CV3(Image.CV3),
    CV4(Image.CV4),
    CV5(Image.CV5),
    CV6(Image.CV6),
    //Truck-Horizontal = TH
    TH1(Image.TH1),
    TH2(Image.TH2),
    TH3(Image.TH3),
    TH4(Image.TH4),
    TH5(Image.TH5),
    TH6(Image.TH6),
    TH7(Image.TH7),
    TH8(Image.TH8),
    TH9(Image.TH9),
    TH10(Image.TH10),
    TH11(Image.TH11),
    TH12(Image.TH12),
    TH13(Image.TH13),
    TH14(Image.TH14),
    TH15(Image.TH15),
    //Truck-Vertical = TV
    TV1(Image.TV1),
    TV2(Image.TV2),
    TV3(Image.TV3),
    TV4(Image.TV4),
    TV5(Image.TV5),
    TV6(Image.TV6),
    TV7(Image.TV7),
    TV8(Image.TV8),
    TV9(Image.TV9),
    TV10(Image.TV10),
    TV11(Image.TV11),
    TV12(Image.TV12),
    TV13(Image.TV13),
    TV14(Image.TV14),
    TV15(Image.TV15),
    //House-Pieces = H
    H1(Image.H1),
    H2(Image.H2),
    H3(Image.H3),
    H4(Image.H4),
    H5(Image.H5),
    H6(Image.H6),
    H7(Image.H7),
    H8(Image.H8),
    H9(Image.H9),
    H10(Image.H10),
    H11(Image.H11),
    H12(Image.H12);
    //Tile textúrák vége

    //visszaadja a képfájt, amit a típusba töltötünk
    public BufferedImage img;

    //A konstruktor minden egyes enum tagnak ad egy BufferedImage-et, azaz egy betöltött képet.
    TileType(BufferedImage img) {
        this.img = img;
    }
}
