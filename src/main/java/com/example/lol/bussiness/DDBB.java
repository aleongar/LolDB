package com.example.lol.bussiness;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class DDBB {
    private static Connection connection;
    private static Statement query;
    private static final String URL = "jdbc:postgresql://192.168.1.74:5432/LoL?user=postgres&password=1234";

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
            query = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql  = "SELECT * FROM campeones ORDER BY nombre";
            ResultSet result = query.executeQuery(sql);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    public static void delete(int id){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "delete from users where id = "+ id ;
            ResultSet result = query.executeQuery(sql);
            result.next();

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
    }

}