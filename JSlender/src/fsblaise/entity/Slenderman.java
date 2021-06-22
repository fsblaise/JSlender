package fsblaise.entity;

import java.util.Random;

/**Ez az osztály felelős:
 * A slendermannal kapcsolatos adatok tárolásáért.
 * A Slenderman mozgatásáért
 */
public class Slenderman extends Entity {

    private int movecount = 0;
    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;
    private boolean dead = false;

    //lespawnolja egy random pozícióra a slendermant
    public Slenderman() {
        randomTp();
    }

    /**Ez a függvény hívja meg a teleportálós függvényeket a különféle távolság-limitekkel.
     * Továbbá itt valósul meg az, hogy bizonyos eséllyel elkapja a Slenderman a játékost.
     * Továbbá, hogyha az 5. teleportálásnál tart a Slenderman, akkor meghívja a randomTp() függvényt.
     * Külön minden egyes papírszámhoz van egy-egy lépésszámláló, ami azért felelős, hogyha megtörtént a 3. lépés és egy a távolság,
     * történjen meg a "sorsolás", ami eldönti, elkapta-e a Slenderman a játékost vagy nem.
     * Ha nem kapja el, vagy nincs egy távolságra, akkor csak nullázza a lépésmérőt.
     *
     * @param player Vár egy játékos példányt, hogy meg tudja nézni, milyen messze van tőle, és az alapján helyezkedjen a Slenderman
     */
    public void move(Player player){
        //Ha az 5. lépésnél vagyunk, akkor random teleportál.
        if(movecount == 4){
            randomTp();
            movecount = 0;
            return;
        }
        if (player.getPaperCount() < 2){
            teleporter(player, -5);
            movecount++;
            return;
        }
        if (player.getPaperCount() < 4){
            teleporter(player, 5);
            movecount++;
            count1++;
            if(count1 == 3){
                if(oneTileAway(player)){
                    Random rnd = new Random();
                    int r = rnd.nextInt(3);
                    //0-1-2-t dob a random, azaz 1/3 = 33% esély van
                    if (r == 0)
                        dead = true;
                }
                count1 = 0;
            }
            return;
        }
        if (player.getPaperCount() < 6){
            teleporter(player, 4);
            movecount++;
            count2++;
            if(count2 == 3){
                if(oneTileAway(player)){
                    Random rnd = new Random();
                    int r = rnd.nextInt(2);
                    //0-1-et dob a random, ebből csak a 0 a jó, azaz 50% esély van
                    if (r == 0)
                        dead = true;
                }
                count2 = 0;
            }
            return;
        }
        if (player.getPaperCount() >= 6){
            teleporter(player, 3);
            movecount++;
            count3++;
            if(count3 == 3){
                if(oneTileAway(player)){
                    Random rnd = new Random();
                    int r = rnd.nextInt(3);
                    //0-1-2-t dob, ebből jó a 0 vagy az 1, azaz 66% az esély
                    if (r == 0 || r == 1)
                        dead = true;
                }
                count3 = 0;
            }
        }
    }

    /**Ez a függvény egy random koordinátára teleportálja a Slendermant
     * Nincs visszatérési értéke.
     */
    private void randomTp(){
        Random rnd = new Random();

        int x = rnd.nextInt(15);
        int y = rnd.nextInt(15);
        this.posx = x*64;
        this.posy = y*64;
    }

    /**Ez a függvény végzi a teleportálást:
     *
     * @param player Vár egy játékos példányt, ugyanis a játékos pozíciója kell ahhoz, hogy a teleportálás távolságát megkapjuk.
     * @param limit Vár egy limitet, ami azt határozza meg, hogy a Slenderman milyen távolságon belül legyen a játékostól.
     *              Van egy kivétel, ami -5 -tel lett jelölve, ez azért van, mert 2 papír alatt a Slenderman legalább 5
     *              távolságra kell, hogy legyen. Ez a -5 át lesz alakítva +5-té.
     */
    private void teleporter(Player player, int limit){
        Random rnd = new Random();

        int x = rnd.nextInt(15);
        int y = rnd.nextInt(15);

        if (limit == -5){
            while(!((Math.abs(player.getPosx()/64-x) + Math.abs(player.getPosy()/64-y)) >= Math.abs(limit))){
                x = rnd.nextInt(15);
                y = rnd.nextInt(15);
            }
            this.posx = x*64;
            this.posy = y*64;
            return;
        }

        while(!((Math.abs(player.getPosx()/64-x) + Math.abs(player.getPosy()/64-y)) < limit)){
            x = rnd.nextInt(15);
            y = rnd.nextInt(15);
        }
        this.posx = x*64;
        this.posy = y*64;
    }

    /**Ez a függvény kiszámolja, hogy a játékostól egy távolságra van-e a Slenderman.
     *
     * @param player Vár egy játékos példányt, aminek koordinátáihoz képest nézi a távolságot.
     * @return Ha igaz: később megtörténik a "sorsolás"
     *         Ha hamis: nem sorsolunk, hanem csak a lépéssszámlálót 0-ra állítjuk (move() függvény)
     */
    private boolean oneTileAway(Player player){
        return (Math.abs(player.getPosx() / 64 - this.posx / 64) + Math.abs(player.getPosy() / 64 - this.posy / 64) == 1);
    }

    public boolean isDead() {
        return dead;
    }
}
