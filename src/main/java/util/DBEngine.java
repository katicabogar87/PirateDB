package util;

import modell.Captain;
import modell.DrunkLevel;
import modell.Pirate;
import modell.Ship;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBEngine {

    private Connection connection;

    //public List<Ship> shipsInDB = new ArrayList<>();

    public DBEngine() {
        connection = connect();
    }

    public boolean isConnected() {
        return (connection != null);
    }

    private Connection connect() {
        String url = "jdbc:mysql://localhost:3306/pirate_DB" +
                "?useUnicode=yes&characterEncoding=UTF-8";

        Properties properties = new Properties();
        properties.put("user", System.getenv("DB_USER"));
        properties.put("password", System.getenv("DB_PASSWORD"));

        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Ship> listAllShips() {
        String query = "SELECT * FROM ship";


        List<Ship> shipsInDB = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // getXXX("column_name_in_DB")
                long id = resultSet.getLong("id");        // resultSet.getLong(1);
                String name = resultSet.getString("name");
                int graphic = resultSet.getInt("graphic_id");
                int numberOfCannons = resultSet.getInt("number_of_cannons");
                int shipCondition = resultSet.getInt("ship_condition");

                Ship ship = new Ship(id, name, graphic, numberOfCannons,shipCondition);

                shipsInDB.add(ship);
            }

        } catch (SQLException e) {
            System.out.println("???");
            e.printStackTrace();
        }

        return shipsInDB;
    }

    public List<Pirate> listAllPirates() {
        String query = "SELECT * FROM pirate";


        List<Pirate> piratesInDB = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // getXXX("column_name_in_DB")
                long id = resultSet.getLong("id");        // resultSet.getLong(1);
                String name = resultSet.getString("name");
                int strength = resultSet.getInt("strength");
                int health = resultSet.getInt("health");
                String drunkLevelFromDB = resultSet.getString("drunk_level").toUpperCase();
                DrunkLevel drunkLevel = DrunkLevel.valueOf(drunkLevelFromDB);
                int shipID = resultSet.getInt("ship_id");
                Ship ship = findShipByID(shipID);


                Pirate pirate = new Pirate(id, name, strength, health, drunkLevel, ship);

                piratesInDB.add(pirate);
            }

        } catch (SQLException e) {
            System.out.println("???");
            e.printStackTrace();
        }

        return piratesInDB;
    }

    private Ship findShipByID(int shipID) {


        String query = "SELECT * FROM  ship  WHERE id = ?";

        Ship ship = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, shipID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // getXXX("column_name_in_DB")
                long id = resultSet.getLong("id");        // resultSet.getLong(1);
                String name = resultSet.getString("name");
                int graphic = resultSet.getInt("graphic_id");
                int numberOfCannons = resultSet.getInt("number_of_cannons");
                int shipCondition = resultSet.getInt("ship_condition");

                ship = new Ship(id, name, graphic, numberOfCannons,shipCondition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return ship;
    }

    private Pirate findPirateByID(int pirateID) {


        String query = "SELECT * FROM  pirate  WHERE id = ?";

        Pirate pirate = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pirateID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                long id = resultSet.getLong("id");        // resultSet.getLong(1);
                String name = resultSet.getString("name");
                int strength = resultSet.getInt("strength");
                int health = resultSet.getInt("health");
                String drunkLevelFromDB = resultSet.getString("drunk_level").toUpperCase();
                DrunkLevel drunkLevel = DrunkLevel.valueOf(drunkLevelFromDB);
                int shipID = resultSet.getInt("ship_id");
                Ship ship = findShipByID(shipID);


                pirate = new Pirate(id, name, strength, health, drunkLevel, ship);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return pirate;
    }

  public List<Captain> listAllCaptains() {
      String query = "SELECT*FROM pirate JOIN captain ON pirate.id = captain.pirate_id;";


      List<Captain> captainsInDB = new ArrayList<>();

      try {
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(query);

          while (resultSet.next()) {
              // getXXX("column_name_in_DB")
              long id = resultSet.getLong("captain.id");        // resultSet.getLong(1);
              String name = resultSet.getString("name");
              int strength = resultSet.getInt("strength");
              int health = resultSet.getInt("health");
              String drunkLevelFromDB = resultSet.getString("drunk_level").toUpperCase();
              DrunkLevel drunkLevel = DrunkLevel.valueOf(drunkLevelFromDB);
              int shipID = resultSet.getInt("ship_id");
              Ship ship = findShipByID(shipID);
              int rumOwned = resultSet.getInt("rum_owned");


              Captain captain = new Captain(id, name, strength, health, drunkLevel, ship, rumOwned);

              captainsInDB.add(captain);
          }

      } catch (SQLException e) {
          System.out.println("???");
          e.printStackTrace();
      }

      return captainsInDB;
  }

    public List<Pirate> listAllPiratesNoCaptains() {
        String query = "SELECT * FROM pirate where id != (SELECT pirate.id FROM pirate JOIN captain ON pirate.id = captain.pirate_id);";


        List<Pirate> piratesInDB = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // getXXX("column_name_in_DB")
                long id = resultSet.getLong("id");        // resultSet.getLong(1);
                String name = resultSet.getString("name");
                int strength = resultSet.getInt("strength");
                int health = resultSet.getInt("health");
                String drunkLevelFromDB = resultSet.getString("drunk_level").toUpperCase();
                DrunkLevel drunkLevel = DrunkLevel.valueOf(drunkLevelFromDB);
                int shipID = resultSet.getInt("ship_id");
                Ship ship = findShipByID(shipID);


                Pirate pirate = new Pirate(id, name, strength, health, drunkLevel, ship);

                piratesInDB.add(pirate);
            }

        } catch (SQLException e) {
            System.out.println("???");
            e.printStackTrace();
        }

        return piratesInDB;
    }
       // dragons element
       // ships crew

    public List<Pirate> findShipsCrew(long shipId) {
        String query = "SELECT * FROM pirate WHERE ship_id = ?";

        List<Pirate> crew = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, shipId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Pirate pirate = findPirateByID(id);
                crew.add(pirate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crew;
    }

}
