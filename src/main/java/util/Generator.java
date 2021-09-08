package util;

import modell.Pirate;
import modell.Ship;
import module.BattleField;

import java.util.ArrayList;

public class Generator {
    public static ArrayList<String> shipNames = new ArrayList<>();
    public static ArrayList<String> pirateFirstNames = new ArrayList<>();
    public static ArrayList<String> pirateLastNames = new ArrayList<>();


    public static String generateShipName() {
        String name;

        int index = (int) (Math.random() * shipNames.size());
        name = shipNames.get(index);
        for (Ship ship : BattleField.shipsInBattle) {
            if (!ship.getName().equals(name)) {
                return name;
            } else {
                name = generateShipName();
            }
        }
        return name;
    }

    public static String generatePirateName() {
        String name;

        int index1 = (int) (Math.random() * pirateFirstNames.size());
        int index2 = (int) (Math.random() * pirateLastNames.size());

        String firstName = pirateFirstNames.get(index1);
        String lastName = pirateLastNames.get(index2);
        name = firstName + " " + lastName;

        for (Ship ship : BattleField.shipsInBattle) {
            for (Pirate pirate : ship.getCrew()) {
                if ((!pirate.getName().equals(name)) || (!pirate.getName().equals("Captain " + name))) {
                    return name;
                } else {
                    name = generatePirateName();
                }
            }
        }
        return name;
    }


}





