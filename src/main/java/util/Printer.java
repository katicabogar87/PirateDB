package util;

import modell.*;
import module.BattleField;

public class Printer {

    public static void printShip(Ship ship){

        System.out.println(ship.getName());
        System.out.println();
        System.out.println(ship.graphic);
        printCrew(ship);

    }


    public static void printCrew(Ship ship){
        printCaptain(ship);
        System.out.println("Crew:");
        for (int i = 1; i <ship.getCrew().size()-2 ; i++) {
            if(i %5  ==0){System.out.println();}
            System.out.print(ship.getCrew().get(i).getName()+ ", ");
        }
        System.out.print(ship.getCrew().get(ship.getCrew().size()-1).getName());
        System.out.println();
    }

    public static void manLossMessage(Ship ship){
        System.out.println(ship.getName()+" lost from her crew:");
        for (Pirate pirate: ship.getCrewLoss()
             ) {
            System.out.println(pirate.getName());
        }
        System.out.println("R. I. P.");
    }

    public static void printCaptain(Ship ship){
        Captain captain = (Captain) ship.getCrew().get(0);
        System.out.println("_^_ Captain of " + ship.getName()+ " is: " +captain.getName());
        System.out.println("_^_ He owns "+ captain.rumOwned + " barrels of RUM.");
        System.out.println("_^_ His health is: "+ captain.getHealth()+" his level of drunkenness is: "+ captain.getDrunkLevel()+" his strength is: " + captain.getStrength());


    }

    public static void printPirate(Pirate pirate){
        System.out.println(pirate.getName());
        System.out.println("Health: "+ pirate.getHealth());
        System.out.println("Level of drunkenness: "+ pirate.getDrunkLevel());
        System.out.println("Strength: " + pirate.getStrength());
        System.out.println("Is able to fight? " + pirate.isCanFight());

    }


    public static void hullMessage(Ship attacker, Ship defender){
        System.out.println("Critical hit: Water breaks into "+defender.getName()+" 's belly.\n" +
                " "+defender.getName()+" sinks. Remaining crew (of "+defender.getCrew().size()+") surrenders, joins " +attacker.getName()+"'s crew. \n" +
                " Half of the RUM can be saved from the ship, it is added to "+attacker.getCrew().get(0).getName()+"'s RUM stock.");
    }

    public static void magazineMessage(Ship defender){
        System.out.println(defender.getName()+" 's magazine got hit. "+defender.getName()+" blows up with a huge fireball.");
        System.out.println("               *    *\n" +
                "   *         '       *       .  *   '     .           * *\n" +
                "                                                               '\n" +
                "       *                *'          *          *        '\n" +
                "   .           *               |               /\n" +
                "               '.         |    |      '       |   '     *\n" +
                "                 \\*        \\   \\             /\n" +
                "       '          \\     '* |    |  *        |*                *  *\n" +
                "            *      `.       \\   |     *     /    *      '\n" +
                "  .                  \\      |   \\          /               *\n" +
                "     *'  *     '      \\      \\   '.       |\n" +
                "        -._            `                  /         *\n" +
                "  ' '      ``._   *                           '          .      '\n" +
                "   *           *\\*          * .   .      *\n" +
                "*  '        *    `-._                       .         _..:='        *\n" +
                "             .  '      *       *    *   .       _.:--'\n" +
                "          *           .     .     *         .-'         *\n" +
                "   .               '             . '   *           *         .\n" +
                "  *       ___.-=--..-._     *                '               '\n" +
                "                                  *       *\n" +
                "                *        _.'  .'       `.        '  *             *\n" +
                "     *              *_.-'   .'            `.               *\n" +
                "                   .'                       `._             *  '\n" +
                "   '       '                        .       .  `.     .\n" +
                "       .                      *                  `\n" +
                "               *        '             '                          .\n" +
                "     .                          *        .           *  *\n" +
                "             *        .                                    '");
        System.out.println(" All souls and rum aboard evaporates. May they rest in seases.");
    }

    public static void captainLossMessage(Ship defender){
        System.out.println( defender.getName()+" 's Captain ("+defender.getCrew().get(0).getName()+") was decapitated by a cannonball. \n" +
                "Morale of his crew remaining drops significantly."
        );
    }


    public static void pressKeyToContinue() { // Enter key

        System.out.println("                                               Press \'ENTER\' to continue...");

        try {
            System.in.read();
        } catch (
                Exception e) {
        }
    }


    public static void tortugaMessage(Ship ship) {
        System.out.println("In a noisy pub on the island of Tortuga, "+ship.getCrew().get(0).getName()+"\n " +
                " recruits pirates for his ship, the \"" + ship.getName()+"\"");
    }

    public static void rumDistributionMessage(Ship ship){
        System.out.println(ship.getCrew().get(0).getName()+ " gives some rum to his pirates, who are "+ DrunkLevel.SOBER.toString().toLowerCase()+" or \n" +
                ""+DrunkLevel.TIPSY.toString().toLowerCase()+" to make them brave enough for the battle.");
    }

    public static void executionMessage(Ship ship) {
        System.out.println("But beware! Those who know no limit, may be facing \n" +
               ship.getCrew().get(0).getName()+"\'s rage, and must walk along the pallet and swim with the sharks!!! ");
        System.out.println();
    }

    public static void statusEndPhaseOne() {
        System.out.println();
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        System.out.println("End of phase One");
        for (Ship ship: BattleField.shipsInBattle) {
            shipStatus(ship);
        }


    }
    public static void shipStatus(Ship ship){
        System.out.println(ship.graphic);
        System.out.println(ship.getName()+"\' s status:");
        System.out.println("condition: "+ship.getCondition()+", manpower: "+ ship.getMenPower()+",\n" +
                " average health: "+ship.getAvgHealth()+", average strength: "+ship.getAvgStrength());
        System.out.println();
    }

    public static void statusEndPhaseTwo() {
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        System.out.println("End of phase Two");
        for (Ship ship: BattleField.shipsInBattle) {
            shipStatus(ship);
        }
    }
}
