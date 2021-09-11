package module;

import modell.Captain;
import modell.Pirate;
import modell.Ship;
import util.DBEngine;
import util.Printer;

public class Tortuga{

    public static int maxCrew = 30;
    public static int minCrew = 5;

    public static void setUpShip(){
        DBEngine engine = new DBEngine();

        Ship ship = new Ship();
        engine.addShipToDB(ship);

        Captain captain = new Captain();
        captain.setShip(ship);
        engine.addCaptainToDB(captain);
        ship.getCrew().add(captain);        //Captain is at 0 index

        Printer.tortugaMessage(ship);
        Printer.pressKeyToContinue();

        int manPower = (int) (Math.random()* (maxCrew-minCrew+1)) +minCrew;
        generateCrewList(manPower, ship);
        for (int i = 1; i < ship.getCrew().size(); i++) {
            engine.addPirateToDB(ship.getCrew().get(i));
        }


        BattleField.shipsInBattle.add(ship);
        Printer.printShip(ship);
        Printer.pressKeyToContinue();

        captain.crewInspection();
        Printer.pressKeyToContinue();
    }

    private static void generateCrewList(int num, Ship ship) {

        for (int i = 0; i < num; i++) {
            Pirate pirate = new Pirate();
            pirate.setShip(ship);
            ship.getCrew().add(pirate);
        }
    }
}