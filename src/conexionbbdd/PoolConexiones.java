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
        } catch (SQLException ex) {
            System.out.println("Error en la desconexión");
        }
    }

    //ALTER TABLE
    //PARA ALBUM
    public void crearAtributoAlbum(String atributo) {
        Statement sta;
        String tipo = "";
        if (atributo.equals("release_year")) {
            tipo = "date";
        } else {
            tipo = "int";
            try {
                sta = conexion.createStatement();
                //executeUpdate para todo lo que no te devuelva info de la BBDD
                sta.executeUpdate("ALTER TABLE album ADD " + atributo + " " + tipo + ";");
                sta.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
                System.out.println("Error al crear columna.");
            }
        }
    }

    //PARA CANCION
    public void crearAtributoCancion(String atributo) {
        Statement sta;
        String tipo = "";
        if (atributo.equals("Year")) {
            tipo = "date";
        } else {
            tipo = "VARCHAR(30)";
        }
        try {
            sta = conexion.createStatement();
            //executeUpdate para todo lo que no te devuelva info de la BBDD
            sta.executeUpdate("ALTER TABLE cancion ADD " + atributo + " " + tipo + ";");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Columna creada correctamente.");
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

    //CREATE TABLE GRUPO
    public void crearTablaGrupo() {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("CREATE TABLE GRUPO(\n"
                    + "    guitarrista varchar(60) DEFAULT NULL,\n"
                    + "    bateria varchar(60) DEFAULT NULL,\n"
                    + "    flauta varchar(60) DEFAULT NULL,\n"
                    + "    Genero varchar(60) DEFAULT NULL,\n"
                    + "    PRIMARY KEY (guitarrista)\n"
                    + "    \n"
                    + ");");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Error al crear tabla GRUPO.");
        }
    }

    //MOSTRAR TABLAS
    public ResultSet mostrarTabla(String query) {
        Statement sta;
        ResultSet rs = null;
        try {
            sta = conexion.createStatement();
            rs = sta.executeQuery(query);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return rs;
    }

    //INSERTAR CANCION
    public void insertarCancion(String nombre, String duracion, String id_Album) {
        Statement sta;
        int aux = 11;
        String puesto = "";
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
    public void insertarAlbum(String id, String nombre, String discografica, String lider, String genero) {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("INSERT INTO album VALUE('" + id + "', '" + nombre + "', '" + discografica + "', '" + lider + "', '" + genero + "');");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Error al insertar album.");
        }
    }

    //ELIMINAR CANCION
    public void eliminarCancion(String id_cancion) {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("DELETE FROM cancion WHERE id_cancion = " + id_cancion + ";");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Error al eliminar canción.");
        }
    }

    //ELIMINAR ALBUM
    public void eliminarAlbum(String id_album) {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("DELETE FROM album WHERE id = " + id_album + ";");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Error al eliminar Album.");
        }
    }

    //EDITAR DATOS DE LA BBDD
    //ALBUM
    public void editarAlbum(String id_Album, String nombre_Album, String discografica, String lider, String genero) {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("UPDATE album SET Nombre_Album = '" + nombre_Album + "', Discografica = '" + discografica
                    + "', lider = '" + lider
                    + "', genero = '" + genero
                    + "' WHERE id = " + id_Album + ";");
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

    //PARA HACER BUSQUEDAS POR EL USUARIO
    public void buscarAlbum(String album) {
        Statement sta;
        ResultSet rs = null;
        try {
            sta = conexion.createStatement();
            rs = sta.executeQuery("SELECT album.* from album WHERE id=" + album);
            System.out.println(sta);
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.toString());
        }
    }
}
