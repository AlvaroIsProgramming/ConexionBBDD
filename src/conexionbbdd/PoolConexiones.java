/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author aghsk
 */
public class PoolConexiones {

    Connection conexion = null;

    public PoolConexiones() {
        //Se establece la conexion pero no te conectas aún (SET)
        BasicDataSource bdSource = new BasicDataSource();
        bdSource.setUrl("jdbc:mysql://localhost:3306/discografica?serverTimezone=UTC");
        bdSource.setUsername("root");
        bdSource.setPassword("");
        try {
            conexion = bdSource.getConnection();
            if (conexion != null) {
                System.out.println("Conexion creada");
            } else {
                System.out.println("Conexion no creada");
            }
        } catch (Exception e) {
            System.err.println("Error");
        }
    }

    //Metodo para conectarte a la BBDD
    public void conection() {
        try {
            String url = "jdbc:mysql://localhost:3306/discografica?serverTimezone=UTC";
            String user = "root";
            String password = "";

            conexion = DriverManager.getConnection(url, user, password);
            if (conexion != null) {
                System.out.println("Conectado con exito Discográfica!");
            }

        } catch (Exception e) {
            System.out.println("Error en la conexion");
        }
    }

    //Metodo para desconectarte de la BBDD
    public void cerrarConexion() {
        try {
            conexion.close();
            System.out.println("Desconectado de Discográfica");
            //return 0;
        } catch (SQLException ex) {
            System.out.println("Error en la desconexión");
            // return -1;
        }
    }

    //ALTER TABLE
    public void cambiarDato() {
        Statement sta;
        try {
            sta = conexion.createStatement();
            //executeUpdate para todo lo que no te devuelva info de la BBDD
            sta.executeUpdate("ALTER TABLE album ADD anno_creacion YEAR;");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Columna creada correctamente.");
        }

    }

    //DROP TABLE ALBUM
    public void borrarTablaAlbum() {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("DROP table album;");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println(" error al borrar la tabla album.");
        }
    }
    
     //DROP TABLE CANCION
    public void borrarTablaCancion() {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("DROP table cancion;");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println(" error al borrar la tabla album.");
        }
    }

    //CREATE TABLE ALBUM
    public void crearTablaAlbum() {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("CREATE TABLE ALBUM(\n"
                    + "    id INT NOT NULL,\n"
                    + "    Nombre_Album varchar(30) DEFAULT NULL,\n"
                    + "    Discografica varchar(30) DEFAULT NULL,\n"
                    + "    Lider varchar(30) DEFAULT NULL,\n"
                    + "    PRIMARY KEY (Nombre_Album)\n"
                    + "    \n"
                    + ");");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Error al crear tabla Album.");
        }
    }
    //CREATE TABLE CANCION

    public void crearTablaCancion() {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("CREATE TABLE CANCION(\n"
                    + "    id INT NOT NULL,\n"
                    + "    Nombre varchar(30) DEFAULT NULL,\n"
                    + "    Duracion varchar(30) DEFAULT NULL,\n"
                    + "    Release_Date date DEFAULT NULL,\n"
                    + "	Album_cancion varchar(30) DEFAULT NULL,\n"
                    + "    PRIMARY KEY (id),\n"
                    + "    FOREIGN KEY (Album_cancion) references ALBUM(Nombre_Album)\n"
                    + ");");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Error al crear tabla canción.");
        }
    }

    //MOSTRAR TABLAS
    public ResultSet mostrarTabla(String query) {
        Statement sta;
        ResultSet datos = null;
        try {
            sta = conexion.createStatement();
            datos = sta.executeQuery(query);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return datos;
    }

    //INSERTAR CANCION
    public void insertarCancion(String id, String nombre, String duracion, String fecha_release, String nombre_album) {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("INSERT INTO cancion VALUE('" + id + "', '" + nombre + "', '" + duracion + "', '" + fecha_release + "', '" + nombre_album + "');");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Error al insertar Cancion");
        }
    }

    //INSERTAR CANCION
    public void insertarAlbum(String id, String nombre, String discografica, String lider) {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("INSERT INTO album VALUE('" + id + "', '" + nombre + "', '" + discografica + "', '" + lider + "');");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Error al insertar album.");
        }
    }

    //ELIMINAR CANCION
    public void eliminarCancion(String cancion) {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Error al eliminar canción.");
        }
    }

    //ELIMINAR ALBUM
    public void eliminarAlbum(String album) {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Error al eliminar Album.");
        }
    }
}
