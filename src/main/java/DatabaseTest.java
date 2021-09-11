import util.DBEngine;

public class DatabaseTest {

        public static void main(String[] args) {

            DBEngine engine = new DBEngine();

            System.out.println("connection: "+engine.isConnected());

            System.out.println("ships: \n" + engine.listAllShips());
            System.out.println("all pirates: \n" + engine.listAllPirates());
            System.out.println("captains: \n" + engine.listAllCaptains());
           // System.out.println("only pirates (no captains): \n" + engine.listAllPiratesNoCaptains());
            System.out.println("ships' crew: \n" + engine.findShipsCrew(3));



        }

}
