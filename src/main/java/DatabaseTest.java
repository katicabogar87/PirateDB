import util.DBEngine;

public class DatabaseTest {

        public static void main(String[] args) {

            DBEngine engine = new DBEngine();

            System.out.println("connection: "+engine.isConnected());

            System.out.println(engine.listAllShips());
            System.out.println(engine.listAllPirates());
            System.out.println(engine.listAllCaptains());
            System.out.println(engine.listAllPiratesNoCaptains());
            System.out.println(engine.findShipsCrew(1));



        }

}
