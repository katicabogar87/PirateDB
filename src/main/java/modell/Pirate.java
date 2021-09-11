package modell;

import lombok.Getter;
import lombok.Setter;
import util.Generator;

public class Pirate {


    @Getter @Setter
    private long pirateId;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int strength;   //  0 -> 10
    private static final int maxStrength = 10;
    private static final int minStrength = 5;
    @Getter
    private int health;     //  0 -> 10
    private static final int maxHealth = 10;
    private static final int minHealth = 5;
    private static final int fightThreshold = 40;
    @Getter @Setter
    private DrunkLevel drunkLevel;  //0.1, 0.5, 1, 1.1, 1.2
    @Getter @Setter
    private Ship ship;

    public Pirate() {

        name = Generator.generatePirateName();
        strength = (int) (Math.random()* (maxStrength-minStrength+1)+minStrength);
        health = (int) (Math.random()* (maxHealth-minHealth+1)+minHealth);
        drunkLevel = DrunkLevel.randomDrunkLevel();

    }

    public Pirate(String name, int strength, int health, DrunkLevel drunkLevel,  Ship ship) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.drunkLevel = drunkLevel;
        this.ship = ship;
    }

    public Pirate(long pirateId, String name, int strength, int health, DrunkLevel drunkLevel, Ship ship) {
        this.pirateId = pirateId;
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.drunkLevel = drunkLevel;
        this.ship = ship;
    }

    public boolean isCanFight(){
        return (getFightValue() > fightThreshold);
    }
    public double getFightValue(){return ((double)(strength * health) * drunkLevel.getCoefficient());}

    public void fight(Pirate enemy){
        if (isCanFight()){

            System.out.println(name + " (S: "+strength+") (H: "+health+") (D: "+drunkLevel+") "+" vs "
                    + enemy.getName() + " (S: "+enemy.getStrength()+") (H: "+enemy.getHealth()+") (D: "+enemy.getDrunkLevel()+")");
            int strengthDifference = Math.abs(strength - enemy.getStrength());
            if ((getFightValue()) >= (enemy.getFightValue())) {
                enemy.decrementHealth(strengthDifference);
                if(enemy.getHealth()!=0) {
                    System.out.println("\t"+enemy.getName() + " is hurt. Remaining health:  " + enemy.getHealth());
                }
            } else {
                decrementHealth(strengthDifference);
                if(health!=0) {
                    System.out.println("\t"+name + " is hurt. Remaining health: " + health);
                }
            }
        }else{
            System.out.println(name + " is unable to fight.");
        }
    }


    public void halveStrength(){
        strength = strength/2;
    }



    public void setHealth(int health) {
        this.health = health;
        if(health==0){
            System.out.println(name + " has died.");
            ship.crewLoss.add(this);
            ship.crew.remove(this);
        }
    }

    public void decrementHealth(int amount){
            if(amount< health){
                health -= amount;}
            else{setHealth(0);
            }
    }


    public void incrementDrunkLevel(int rumAmount) {
          if (0 < rumAmount && rumAmount <= 2) {
            drunkLevel = DrunkLevel.getDrunker(drunkLevel);
        } else if (rumAmount <= 3) {
            drunkLevel = DrunkLevel.getDrunker(drunkLevel);
            drunkLevel = DrunkLevel.getDrunker(drunkLevel);
        }
    }


    @Override
    public String toString() {

            return pirateId + " - " +
                    name + ", " + "\n" +
                    "strength: " + strength + " " +
                    "health: " + health + " " +
                    "drunkLevel: " + drunkLevel + "\n" +
                    "member of "+ship.getName()+"\'s crew" +
                    "\n";

    }
}
