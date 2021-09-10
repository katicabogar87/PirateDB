import module.BattleField;
import module.Tortuga;
import util.DBEngine;
import util.Reader;

public class SevenSeas {
    public static void main(String[] args) {

        DBEngine engine = new DBEngine();

        System.out.println("connection: "+engine.isConnected());

        System.out.println(engine.listAllShips());
        System.out.println(engine.listAllPirates());
        System.out.println(engine.listAllCaptains());


       /* Reader.readFiles();

        System.out.println("done");

        Tortuga.setUpShip();
        Tortuga.setUpShip();

        BattleField.winnerShip = BattleField.phaseOne();
        BattleField.winnerShip = BattleField.phaseTwo();


        System.out.println("THE END");*/
    }
}
