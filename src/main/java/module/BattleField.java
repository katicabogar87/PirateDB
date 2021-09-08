package module;

import modell.Ship;
import util.Printer;

import java.util.ArrayList;
import java.util.List;

public class BattleField {
    public static List<Ship> shipsInBattle = new ArrayList<>();
    public static Ship winnerShip;

    public static Ship phaseOne(){
        Ship winner;

        Ship attackerShip = shipsDetectEachOther(shipsInBattle.get(0), shipsInBattle.get(1));
        Ship defenderShip;
        if(attackerShip == null){return winnerShip;}
       else{
        if(attackerShip.equals(shipsInBattle.get(0))){defenderShip = shipsInBattle.get(1);}
        else {defenderShip = shipsInBattle.get(0);}

        System.out.println("A cruel fight breaks out between the ships!");
        Printer.pressKeyToContinue();

            winner = cannonsFired(attackerShip, defenderShip);
            if (winner == null) {
                attackerShip.collateralDamage();
                defenderShip.collateralDamage();

                Printer.statusEndPhaseOne();
                Printer.pressKeyToContinue();
            } else {
                System.out.println("Winner is: " + winner.getName());
            }
        }

        return winner;
    }

    private static Ship shipsDetectEachOther (Ship ship1, Ship ship2){
        System.out.println("The two ships detect each other ...");
        System.out.println("From afar, they try to measure up their chances.");
        if(ship1.assessEnemy(ship2)){
            System.out.println(ship1.getName()+"'s Captain decides to attack:");          ;
            return ship1;
        }else{
            System.out.println(ship1.getName()+"'s Captain decides to run make a for it.");
            if(ship1.flee()){
                System.out.println(ship1.getName()+" managed to escape the conflict undamaged.");
                winnerShip=ship2;
                System.out.println("Winner is: " +winnerShip.getName());

                return null;

            }else{
                System.out.println("...but "+ship2.getName()+ " has gained upon them.");
                return ship2;}

        }
    }

    private static Ship cannonsFired(Ship attackerShip, Ship defenderShip) {
        Ship winner=null;

        /*System.out.println(attackerShip.getName()+" has "+ attackerShip.shotsFired() + " shots.");
        System.out.println(defenderShip.getName()+" has "+ defenderShip.shotsFired() + " shots.");*/
        System.out.println(attackerShip.getName()+" has "+ attackerShip.shotsFired() + " operable cannons onboard.");
        System.out.println(defenderShip.getName()+" has "+ defenderShip.shotsFired() + " operable cannons onboard.");

        for (int i = 0; i < Math.max(attackerShip.shotsFired(), defenderShip.shotsFired()); i++) {
            if (i <= attackerShip.shotsFired()){
                System.out.println(attackerShip.getName()+ " (Cond.: "+ attackerShip.getCondition() +"%)'s " + (i+1)+". cannon fires:");
               winner=attackerShip.attack(defenderShip);
               if (winner != null){
                   return winner;
               }
            }else{
                System.out.println(attackerShip.getName()+" has no more shots.");
            }
            if (i <= defenderShip.shotsFired()){
                System.out.println(defenderShip.getName()+ " (Cond.: "+ defenderShip.getCondition() +"%)'s " + (i+1)+". cannon fires:");
                winner = defenderShip.attack(attackerShip);
                if (winner != null){
                    return winner;
                }
            }else{
                System.out.println(defenderShip.getName()+" has no more shots left.");
            }
        }
return null;
    }


    public static Ship phaseTwo() {
        Ship winner;
        if(winnerShip==null) {

            Ship ship1 = shipsInBattle.get(0);
            Ship ship2 = shipsInBattle.get(1);


            for (int i = 0; i < Math.max(ship1.getCrew().size(), ship2.getCrew().size()); i++) {
                if (ship1.getCrew().size() > i) {
                    ship1.getCrew().get(i).fight(ship2.getCrew().get((int) (Math.random() * (ship2.getCrew().size() - 1))));
                }

                if (ship2.getCrew().size() > i) {
                    ship2.getCrew().get(i).fight(ship1.getCrew().get((int) (Math.random() * (ship1.getCrew().size() - 1))));
                }
            }
            Printer.statusEndPhaseTwo();

            winner = getWinner();
            if (winner != null) {
                Printer.pressKeyToContinue();
                System.out.println("Winner is: " + winner.getName());
                return winner;
            }
        }
        return null;
    }

    public static Ship getWinner(){
        Ship ship1 = BattleField.shipsInBattle.get(0);
        Ship ship2 = BattleField.shipsInBattle.get(1);

        int points1=0;
        int points2=0;
        Ship winner;

        if(ship1.getCondition()>ship2.getCondition()){
            points1++;
        }else if(ship1.getCondition()<ship2.getCondition()){
            points2++;
        }

        if(ship1.getAvgHealth()>ship2.getAvgHealth()){
            points1++;
        }else if(ship1.getAvgHealth()<ship2.getAvgHealth()){
            points2++;
        }

        if(ship1.getAvgStrength()>ship2.getAvgStrength()){
            points1++;
        }else if(ship1.getAvgStrength()<ship2.getAvgStrength()){
            points2++;
        }

        if(ship1.getMenPower()>ship2.getMenPower()){
            points1++;
        }else if(ship1.getMenPower()<ship2.getMenPower()){
            points2++;
        }

        if(points1>points2){
            winner = ship1;
        }else if(points1==points2){
            System.out.println("Draw: the parties agreed to a ceasefire.");
            winner = null;
        }else{
            winner=ship2;
        }
        return winner;
    }
}
