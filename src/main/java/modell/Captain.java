package modell;

import lombok.Getter;
import lombok.Setter;
import util.Printer;

public class Captain extends Pirate {

    @Getter
    @Setter
    public long captainId;

    @Getter
    @Setter
    public int rumOwned;
    public static int minRumOwned = 50;
    public static int maxRumOwned = 100;


    public Captain() {
        super.setName("Captain "+super.getName());
        rumOwned = (int) (Math.random()* (maxRumOwned-minRumOwned+1)+minRumOwned);
    }

    public Captain(String name, int strength, int health, DrunkLevel drunkLevel,  Ship ship, int rumOwned) {
        super(name, strength, health, drunkLevel, ship);
        this.rumOwned = rumOwned;
    }

    public Captain(long id, String name, int strength, int health, DrunkLevel drunkLevel, Ship ship, int rumOwned) {
        super(id, name, strength, health, drunkLevel, ship);
        this.rumOwned = rumOwned;
    }

    public boolean decrementRum(int amount) {
        if (amount <= rumOwned) {
            rumOwned -= amount;
            if(rumOwned == 0){
                System.out.println(getName()+" has no rum left!!!");
            }
            return true;
        }
        return false;
    }

    public void incrementRum(int amount) {
        rumOwned += amount;
    }

    public void giveRumToPirate(Pirate happy, int rumAmount){
            if(decrementRum(rumAmount)){
            happy.incrementDrunkLevel(rumAmount);
            }
            else {
                decrementRum(rumOwned);
                happy.incrementDrunkLevel(rumOwned);
            }
        }


    public void executePoorPirate(Pirate poor){
        System.out.println("\"" +poor.getName() + " U tew drank! U go wis sharx!!! DED!\"");
        poor.setHealth(0);
    }

    public void crewInspection() {

        Printer.rumDistributionMessage(super.getShip());

        int i = 1;
        while (rumOwned!=0 && i < super.getShip().getMenPower()){
        //for (int i = 1; i < super.getShip().getMenPower(); i++) {
            Pirate happy = super.getShip().getCrew().get(i);

            if (happy.getDrunkLevel().equals(DrunkLevel.SOBER) ||
                    happy.getDrunkLevel().equals(DrunkLevel.TIPSY)) {
                int rumAmount = (int) (Math.random() * 3);
                giveRumToPirate(happy, rumAmount);
            }
            i++;
        }

        Printer.executionMessage(super.getShip());
        for (i = 1; i < super.getShip().getMenPower(); i++) {
            Pirate poor = super.getShip().getCrew().get(i);
            if (poor.getDrunkLevel().equals(DrunkLevel.WASTED)) {
                int executionChance = (int) (Math.random() * 10);
                if (executionChance % 3 == 0) {
                    executePoorPirate(poor);
                }
            }
        }
    }


    @Override
    public void fight(Pirate enemy) {
        if (super.isCanFight()) {
            int strengthDifference = Math.abs(super.getStrength() - enemy.getStrength());
            System.out.println(getName()+" shoots at "+enemy.getName());
            enemy.decrementHealth(strengthDifference);
            if(enemy.getHealth()!=0){
                System.out.println("\t"+enemy.getName() + " is hurt. Remaining health: " + enemy.getHealth());
            }
        }
    }


  @Override public void incrementDrunkLevel(int rumAmount) {
        if (rumAmount <= 5) {
            super.setDrunkLevel(DrunkLevel.getDrunker(super.getDrunkLevel())) ;
        } else if (rumAmount <= 10) {
            super.setDrunkLevel(DrunkLevel.getDrunker(super.getDrunkLevel())) ;
            super.setDrunkLevel(DrunkLevel.getDrunker(super.getDrunkLevel())) ;

        }
    }


    @Override
    public String toString() {
        return super.getPirateId() + " - " +
                super.getName() + ", " +"\n" +
                "strength: " + super.getStrength() + " " +
                "health: " + super.getHealth() + " " +
                "drunkLevel: " + super.getDrunkLevel() +"\n"+
                "Captain of "+super.getShip().getName()+
                "\n";
    }

}
