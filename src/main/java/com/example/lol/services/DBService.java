package com.example.lol.services;

import com.example.lol.models.PlayerModel;
import com.example.lol.models.TeamModel;
import com.example.lol.models.UserModel;
import java.sql.*;
import java.util.ArrayList;

public class DBService {
    private static Connection connection;
    private static Statement query;
    public static final int NULL_ID = 0;
    private static final String URL = "jdbc:postgresql://localhost:5432/LolDB?user=postgres&password=1234";

    public static int login(String user, String password){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "SELECT id FROM usuarios WHERE username = '"+user+"' AND password = '"+ password+"'";
            ResultSet result = query.executeQuery(sql);
            result.next();
            return result.getInt(1);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("No se han podido obtener datos");
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
        return 0;
    }

    public static UserModel userLogged(String user, String password){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "SELECT * FROM usuarios WHERE username = '"+user+"' AND password = '"+ password+"'";
            ResultSet result = query.executeQuery(sql);
            result.next();
            return new UserModel(result.getInt(1), result.getString(2),
                result.getString(3), result.getBoolean(4));

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("No se han podido obtener datos");
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
        return null;
    }

    public static void signup(String user, String password){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "INSERT INTO usuarios (username, password) values ('" + user + "', '" + password + "')";
            query.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println();
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
    }

    public static ResultSet getChampionQuery(){
        try {
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql  = "SELECT * FROM campeones";
            ResultSet result = query.executeQuery(sql);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                connection.close();
            }catch (SQLException e){
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
    }


    public static int checkUser(String user, String id){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "SELECT id FROM usuarios WHERE username = '"+user+"' AND id = "+ id;
            ResultSet result = query.executeQuery(sql);
            result.next();
            return result.getInt(1);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("No se han podido obtener datos");
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
        return 0;
    }

    public static int checkUser(String user, String id, boolean admin, String password){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "SELECT id FROM usuarios WHERE username = '"+user+"' AND id = "+ id +
                    " AND admin =" + admin + " AND password ='" + password+"'";
            ResultSet result = query.executeQuery(sql);
            result.next();
            return result.getInt(1);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("No se han podido obtener datos");
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
        return 0;
    }

    public static void updatePassword(String user, String id, String password){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql = "UPDATE usuarios SET password = '" + password + "' WHERE username = '" + user + "' and id = " + id;
            query.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println();
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
    }

    public static int getChampionsCount() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql = "SELECT count(*) FROM campeones";
            ResultSet result = query.executeQuery(sql);
            result.next();
            return result.getInt(1);
        } catch (SQLException e) {
            System.err.println();
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido establecer la conexion");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
        return 0;
    }

    public static void updateChamps(String q, String w, String e, String r, String campeon){
        try {
            System.out.println("Si lees esto, es porque se está modificando los campeones");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "UPDATE campeones SET habilidades = '" + q + ", " + w + ", " + e +
                    ", " + r + "' WHERE nombre = '" + campeon + "'";
            ResultSet result = query.executeQuery(sql);
            result.next();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("No se han podido obtener datos");
        } catch (ClassNotFoundException ex) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
    }

    public static void insertChamp(String nombre, String q, String w, String e, String r, String dano) throws SQLException{
        try {
            System.out.println("Si lees esto, es porque se está insertando los campeones");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "INSERT INTO campeones VALUES ('"+ nombre + "', '" + q + ", " + w + ", " + e +
                    ", " + r + "', '" + dano +"')";
            query.execute(sql);
        } catch (ClassNotFoundException ex) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
    }

    public static ArrayList<PlayerModel> getNewerPlayersView(){
        ArrayList<PlayerModel> arrayList = new ArrayList<>();
        try {
            System.out.println("Si lees esto, es porque se está obteniendo la lista de la versión actual");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql = "SELECT last_version() ,j.* FROM jugadores j";
            ResultSet result = query.executeQuery(sql);
            while(result.next()){
                String sql2 = "SELECT * from obtener_mejor_campeon('" + result.getString(2) + "')";
                Statement query2 = connection.createStatement();
                ResultSet result2 = query2.executeQuery(sql2);
                result2.next();
                arrayList.add(new PlayerModel(result.getString(1), result2.getString(1),
                        result.getString(2), result2.getString(2), result.getString(3),
                        result.getString(4), result.getString(6), result.getString(5)));
            }
            return arrayList;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("No se han podido obtener datos");
        } catch (ClassNotFoundException ex) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
        return null;
    }

    public static ArrayList<PlayerModel> getOlderPlayer(){
        ArrayList<PlayerModel> arrayList = new ArrayList<>();
        try {
            System.out.println("Si lees esto, es porque se está obteniendo la lista de la versión antigua");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql = "SELECT ultima_version, a.*, i.campeon, j.equipo, j.nombre, j.apellido, j.nacionalidad " +
                    "from (SELECT apodo, max(maestria) from info_jugadores_maestria group by apodo) a," +
                    " info_jugadores_maestria i, jugadores j WHERE a.max = i.maestria AND j.apodo = a.apodo";
            ResultSet result = query.executeQuery(sql);
            while(result.next()){
                arrayList.add(new PlayerModel(result.getString(1), result.getString(3),
                        result.getString(2), result.getString(4), result.getString(6),
                        result.getString(7), result.getString(5), result.getString(8)));
            }
            return arrayList;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("No se han podido obtener datos");
        } catch (ClassNotFoundException ex) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
        return null;
    }
    public static String insertPlayer(String name, String surname, String username, String team,
                                      String mastery, String bestChamp){
        try {
            System.out.println("Si lees esto, es porque se está insertando los jugadores");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "INSERT INTO jugadores (nombre, apellido, apodo, equipo) VALUES ('" + name + "', '" + surname +
                    "', '" + username + "', '" + team + "')";
            query.execute(sql);
            sql = "INSERT INTO dominar values ('" + username + "', '" + bestChamp + "', " + mastery + ")";
            query.execute(sql);
            return "Jugador añadido";
        } catch (SQLException e) {
            System.err.println("No se han podido obtener datos");
            return e.getMessage();
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
        return "Fallo en la base de datos";
    }

    public static void deletePlayer(String username){
        try {
            System.out.println("Si lees esto, es porque se está eliminando los jugadores");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "DELETE FROM dominar WHERE jugador = '" + username +"'";
            query.execute(sql);
            sql = "DELETE FROM jugadores WHERE apodo = '" + username +"'";
            query.execute(sql);
        } catch (SQLException e) {
            System.err.println("No se han podido obtener datos");
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
    }

    public static ArrayList<TeamModel> getTeams(){
        ArrayList<TeamModel> equipo = new ArrayList<>();
        try {
            System.out.println("se están cargando los equipos");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "SELECT * FROM equipos";
            ResultSet result = query.executeQuery(sql);
            while(result.next()){
                equipo.add(new TeamModel(result.getString(1),
                        result.getString(2), result.getString(3)));
            }
        } catch (SQLException e) {
            System.err.println("No se han podido obtener datos");
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
        return equipo;
    }

    public static void deleteTeam(String name, String shortName, String nation){
        try {
            System.out.println("Si lees esto, es porque se está eliminando un equipo");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "DELETE FROM equipos WHERE id = '" + shortName +
                    "' AND nombre = '" + name + "'AND nacion = '" + nation + "'" ;
            query.execute(sql);
        } catch (SQLException e) {
            System.err.println("No se han podido obtener datos");
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
    }

    public static void addTeam(String name, String shortName, String nation){
        try {
            System.out.println("Si lees esto, es porque se está insertando un equipo");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "INSERT INTO equipos (id, nombre, nacion) values ('" +shortName + "', '" +
                    name + "', '" + nation + "')";
            query.execute(sql);
        } catch (SQLException e) {
            System.err.println("No se han podido obtener datos");
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
    }

    public static void updateTeam(String id, String nombre, String nacion){
        try {
            System.out.println("Si lees esto, es porque se está actualizando un equipo");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "UPDATE equipos SET nombre = '" + nombre +
                    "', nacion = '" + nacion + "' WHERE id = '" + id + "'";
            query.execute(sql);
        } catch (SQLException e) {
            System.err.println("No se han podido obtener datos");
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido establecer la conexion");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println("No se ha podido cerrar la conexion");
            }
        }
    }
}