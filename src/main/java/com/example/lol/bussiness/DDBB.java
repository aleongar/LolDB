package com.example.lol.bussiness;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class DDBB {
    private static Connection connection;
    private static Statement query;
    private static final String URL = "jdbc:postgresql://localhost:5432/LoL?user=postgres&password=1234";

    public static boolean login(String user, String password){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "SELECT id FROM usuarios WHERE username = '"+user+"' AND password = '"+ password+"'";
            ResultSet result = query.executeQuery(sql);
            result.next();
            return result.getInt(1) > 0 ? true : false;

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
        return false;
    }

    public static void signup(String user, String password){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "INSERT INTO usuarios (username, password) values ('" + user + "', '" + password + "')";
            query.executeQuery(sql);
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

    public static ArrayList<ArrayList<String>> mostrarVistaUsuarios(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "SELECT * FROM users ";
            ResultSet result = query.executeQuery(sql);
            ArrayList<ArrayList<String>> datos = new ArrayList<>();
            while (result.next()) {
                ArrayList<String> user = new ArrayList<>();
                user.add(Integer.toString(result.getInt(1)));
                user.add(result.getString(2));
                user.add(result.getString(3));
                user.add(result.getString(4));
                user.add(Integer.toString(result.getInt(5)));
                user.add(result.getString(6));
                user.add(result.getString(7));
                datos.add(user);
            }
            for (ArrayList<String> array : datos) {
                for (String dato : array) {
                    System.out.print(dato + " ");
                }
                System.out.println();
            }
            return datos;

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

    public static void insertar(String username ,String name,
                                String surname, String phone, String email){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "INSERT INTO users (username, name, surname, phone, email, password) " +
                    "VALUES ('" + username + "', '" + name + "', '" + surname +
                    "', '" + phone + "', '" + email + "', " + "'e9b8f05816a9d5885af079d94b449167'" + ")" ;
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

    public static void actualizar(String username ,String name,
                                String surname, String phone, String email, int id){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL);
            query = connection.createStatement();
            String sql  = "update users \n" +
                    "set username = '"+ username + "',  name = '"+ name +"',\n" +
                    "surname = '"+ surname +"', phone = "+ phone +", email = '"+ email +"'\n" +
                    "where id = "+ id ;
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