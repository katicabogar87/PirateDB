package modell;

import module.BattleField;
import util.Generator;
import util.Printer;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private long id;
    public String name;
    public String graphic;
    private int numberOfCannons;            //former attackPower
    private static int maxNumberOfCannons = 12;
    private static int minNumberOfCannons = 6;
    private int condition;
    private static int maxCondition =100;
    private static int minCondition =70;
    public static String shipGraphic1 =
            "     ^  +~+~~\n" +
            "    ^   )`.).\n" +
            "      )``)``) .~~\n" +
            "      ).-'.-')|)\n" +
            "    |-).-).-'_'-/\n" +
            " ~~~\\ `o-o-o'  /\n" +
            "  ~~~'---.____/\n" +
            "\n";

    public static String shipGraphic2 =
            "    __|__ |___| |\\\n" +
            "    |o__| |___| | \\\n" +
            "    |___| |___| |o \\\n" +
            "   _|___| |___| |__o\\\n" +
            "  /...\\_____|___|____\\_/\n" +
            "  \\   o * o * * o o  /\n" +
            "~~~~~~~~~~~~~~~~~~~~~~~~~~\n" ;


    public List<Pirate> crew = new ArrayList<>();
    public List<Pirate> crewLoss = new ArrayList<>();



    public Ship() {
        name = Generator.generateShipName();
        numberOfCannons = (int) (Math.random()* (maxNumberOfCannons-minNumberOfCannons+1)+minNumberOfCannons);
        condition = (int) (Math.random()* (maxCondition-minCondition+1)+minCondition);
        int randomGraphic =  (int) (Math.random()*10);
        if (randomGraphic%2==0){graphic=shipGraphic1;}
        else {graphic=shipGraphic2;}
    }

    public Ship(long id, String name, int graphicID, int numberOfCannons, int shipCondition) {
        this.id = id;
        this.name = name;
        if (graphicID ==1){
            graphic = shipGraphic1;
        }else if(graphicID ==2){
            graphic = shipGraphic2;
        } else {graphic = String.valueOf(graphicID);}
        this.numberOfCannons =numberOfCannons;
        this.condition = shipCondition;
    }

    public double calcShipStrength() {
        return ((double) (10*numberOfCannons/maxNumberOfCannons)*(double) (10*condition/maxCondition));
    }

    public  boolean assessEnemy(Ship enemyShip){
        return (calcShipStrength() >= enemyShip.calcShipStrength());
    }

    public boolean flee(){
        return ((Math.random()*10)>7);
    }

    public int shotsFired(){
        return Math.min(crew.size(), numberOfCannons);
    }

    public void hitMagazine(Ship enemyShip){

/*        for (Pirate pirate:enemyShip.getCrew()) {
            pirate.setHealth(0);
        }*/
        for (int i = 0; i < enemyShip.getCrew().size(); i++) {
            enemyShip.getCrew().get(enemyShip.getCrew().size()-1-i).setHealth(0);
        }
        enemyShip.setCondition(0);
    }

    public void hitHull(Ship enemyShip){
        Captain captainOwn = (Captain) crew.get(0);
        Captain captainEnemy = (Captain) enemyShip.crew.get(0);

        captainEnemy.setName("former "+enemyShip.crew.get(0).getName());
        int rumSalvaged = captainEnemy.getRumOwned()/2;
        captainOwn.incrementRum(rumSalvaged);

        for (Pirate pirate: enemyShip.crew
        ) {crew.add(pirate);
            pirate.setShip(this);
        }
        enemyShip.crew.clear();
        enemyShip.setCondition(0);
    }
    public void hitCaptain(Ship enemyShip){
        Captain newCaptain = createNewCaptain();
        crew.get(0).setHealth(0);
        crew.add(0, newCaptain);
        for (Pirate pirate: crew){
            pirate.halveStrength();
        }
        System.out.println("New Captain of " + enemyShip.getName() + " is: " + newCaptain.getName());
    }

    public Pirate findStrongestPirate(){
        int max=crew.get(0).getStrength();
        Pirate strongest = crew.get(0);
        for (int i = 1; i < crew.size(); i++) {
            if(crew.get(i).getStrength()>max){
                max = crew.get(i).getStrength();
                strongest = crew.get(i);
            }
        }
        return strongest;
    }
    public Captain createNewCaptain(){
        Pirate captainToBe = findStrongestPirate();
        Captain deceasedCaptain = (Captain) crew.get(0);
        Captain newCaptain = new Captain(
                                        "Captain " + captainToBe.getName(),
                                        captainToBe.getStrength(),
                                        captainToBe.getHealth(),
                                        captainToBe.getDrunkLevel(),
                                        captainToBe.getShip(),
                                        deceasedCaptain.getRumOwned());
        return newCaptain;
    }


    public  Ship attack(Ship enemyShip) {
        int incomingHit = (int) (Math.random() * 100);
        if (incomingHit < 82) {
            System.out.println("MISS!");
            return null;
        } else {
            System.out.println("HIT!");
            int hitEvent = (int) (Math.random() * 100);
            if (hitEvent == 100) {
                Printer.captainLossMessage(enemyShip);
                hitCaptain(enemyShip);

            } else if (hitEvent >= 89) {
                Printer.hullMessage(this, enemyShip);
                hitHull(enemyShip);
                return this;

            } else if (hitEvent >= 84) {
                System.out.println("Ship's magazine got hit: Catastrophic destruction.");
                Printer.magazineMessage(enemyShip);
                hitMagazine(enemyShip);
                return this;

            } else {
                int generalDamage = (int) (Math.random() * (15 - 5 + 1) + 5);
                enemyShip.decrementCondition(generalDamage);
                System.out.println("Damage dealt: " + generalDamage);
                if(enemyShip.getCondition()==0){
                    return this;
                }else return null;
            }
        } return null;
    }

    public  void collateralDamage(){
        int manLoss = (int) (Math.random()* (crew.size()/3));
        System.out.println();
        System.out.println("Casualties on "+name+ ": "+manLoss);
        for (int i = 0; i < manLoss; i++) {
            int last = crew.size()-(1);
            System.out.println(crew.get(last).getName() + " ");
            crew.get(last).setHealth(0);
        }
        if(manLoss!=0) {
            System.out.println("R. I. P.");
        }
    }

    public int getMenPower(){
        return crew.size();
    }

    public String getName() {
        return name;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public void decrementCondition(int amount) {
        if (amount < condition) {
            condition -= amount;
        } else {
            condition = 0;
            System.out.println(name + " sank.");
            for (Ship ship : BattleField.shipsInBattle) {
                if (!ship.equals(this)) {
                    BattleField.winnerShip = ship;
                }
            }
        }
    }

    public int getNumberOfCannons() {
        return numberOfCannons;
    }

    public List<Pirate> getCrew() {return crew; }

    public List<Pirate> getCrewLoss() {
        return crewLoss;
    }

    public int getAvgHealth() {
        int sumHealth=0;
        for (Pirate pirate:crew) {
            sumHealth+= pirate.getHealth();
        }
        return sumHealth/crew.size();
    }

    public int getAvgStrength() {
        int sumStrength=0;
        for (Pirate pirate:crew) {
            sumStrength+= pirate.getHealth();
        }
        return sumStrength/crew.size();
    }

    @Override
    public String toString() {
        return id + " - " +
                name + ", " +"\n" +
                graphic  +
                "number of cannons: " + numberOfCannons + " " +
                "condition: " + condition;
    }
}
