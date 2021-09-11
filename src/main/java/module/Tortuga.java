package module;

import modell.Captain;
import modell.Pirate;
import modell.Ship;
import util.Printer;

public class Tortuga {
    public static int maxCrew = 30;
    public static int minCrew = 5;
    public static void setUpShip(){
        Ship ship = new Ship();

        Captain captain = new Captain();
        captain.setShip(ship);
        ship.getCrew().add(captain);        //Captain is at 0 index
        Printer.tortugaMessage(ship);
        Printer.pressKeyToContinue();

        int manPower = (int) (Math.random()* (maxCrew-minCrew+1)) +minCrew;

        generateCrewList(manPower, ship);

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
