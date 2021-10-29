import modell.Captain;
import modell.Pirate;
import modell.Ship;
import module.BattleField;
import module.Tortuga;
import util.DBEngine;
import util.Reader;

public class SevenSeas {
    public static void main(String[] args) {

       // DBEngine engine = new DBEngine();

        //System.out.println("connection: "+engine.isConnected());

        Reader.readFiles();

        Tortuga.setUpShip();
        Tortuga.setUpShip();
        BattleField.winnerShip = BattleField.phaseOne();
        BattleField.winnerShip = BattleField.phaseTwo();

        System.out.println("THE END");
    }
}
