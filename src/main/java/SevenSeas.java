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

        System.out.println("done");

        Tortuga.setUpShip();
        Tortuga.setUpShip();

       /* for (Ship ship: BattleField.shipsInBattle) {
            if(engine.addShipToDB(ship)){
                engine.addCaptainToDB((Captain) ship.getCrew().get(0));
            for (int i = 1; i < ship.getCrew().size(); i++) {
                engine.addPirateToDB(ship.getCrew().get(i));
            }}
        }*/

       /* System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        System.out.println(engine.listAllShips());
        System.out.println(engine.listAllPirates());
        System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");*/

       /* BattleField.winnerShip = BattleField.phaseOne();
        BattleField.winnerShip = BattleField.phaseTwo();*/

        System.out.println("THE END");
    }
}
