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
                    + "    Nombre_Album varchar(60) DEFAULT NULL,\n"
                    + "    Discografica varchar(30) DEFAULT NULL,\n"
                    + "    Lider varchar(30) DEFAULT NULL,\n"
                    + "    Genero varchar(40) DEFAULT NULL,\n"
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
                    + "    Nombre varchar(30) DEFAULT NULL,\n"
                    + "    Duracion varchar(30) DEFAULT NULL,\n"
                    + "    Release_Year varchar(5) DEFAULT NULL,\n"
                    + "	Album_cancion varchar(60) DEFAULT NULL,\n"
                    + "    PRIMARY KEY (Nombre),\n"
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
    public void insertarCancion(String nombre, String duracion, String id_Album) {
        Statement sta;
        int aux = 0;
        String puesto="";
        while (puesto.charAt(aux) != '-') {
            puesto = puesto + puesto.charAt(aux);
            aux++;
        }
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("INSERT INTO cancion(id_cancion, Nombre, Duracion, id_Album)"
                    + " VALUES(" + aux + ", '" + nombre + "', '" + duracion + "', '" + id_Album + "');");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Error al insertar Cancion");
        }
    }

    //INSERTAR ALBUM
    public void insertarAlbum(String nombre, String discografica, String lider, String genero) {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("INSERT INTO album VALUE('" + nombre + "', '" + discografica + "', '" + lider + "', '" + genero + "');");
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

    //EDITAR DATOS DE LA BBDD
    //ALBUM
    public void editarAlbum(String nombreas, String discografica, String lider, String genero) {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("");
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.toString());
        }

    }

    //CANCION
    public void editarCancion(String id, String nombre, String duracion) {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("UPDATE cancion SET Nombre = '" + nombre + "', Duracion = '" + duracion
                    + "' WHERE id_cancion = " + id + ";");
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.toString());
        }

    }

}
