package modell;

import util.Generator;

public class Pirate {

    private String name;
    private int strength;   //  0 -> 10
    private static int maxStrength = 10;
    private static int minStrength = 5;
    private int health;     //  0 -> 10
    private static int maxHealth = 10;
    private static int minHealth = 5;
    private static int fightThreshold = 40;
    private DrunkLevel drunkLevel;  //0.1, 0.5, 1, 1.1, 1.2
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void halveStrength(){
        strength = strength/2;
    }

    public int getHealth() {
        return health;}

    public void setHealth(int health) {
        this.health = health;
        if(health==0){
            ship.crewLoss.add(this);
            ship.crew.remove(this);
        }
    }

    public void decrementHealth(int amount){
            if(amount< health){
                health -= amount;}
            else{setHealth(0);
                System.out.println(name + " has died.");
            }
    }

    public DrunkLevel getDrunkLevel() {
        return drunkLevel;
    }

    public void setDrunkLevel(DrunkLevel drunkLevel) {
        this.drunkLevel = drunkLevel;
    }

    public void incrementDrunkLevel(int rumAmount) {
          if (0 < rumAmount && rumAmount <= 2) {
            drunkLevel = DrunkLevel.getDrunker(drunkLevel);
        } else if (rumAmount <= 3) {
            drunkLevel = DrunkLevel.getDrunker(drunkLevel);
            drunkLevel = DrunkLevel.getDrunker(drunkLevel);
        }
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
