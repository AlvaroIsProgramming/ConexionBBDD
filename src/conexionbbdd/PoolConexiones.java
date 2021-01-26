/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public void addColumna() {
        Statement sta;
        try {
            sta = conexion.createStatement();
            //executeUpdate para todo lo que no te devuelva info de la BBDD
            sta.executeUpdate("ALTER TABLE grupo ADD anno_creacion YEAR;");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Columna creada correctamente.");
        }

    }

    public void borrarTablaGrupo() {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("DROP table grupo;");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Tabla borrada correctamente.");
        }
    }

    public void crearTablaGrupo() {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("CREATE TABLE GRUPO(\n"
                    + "    id INT NOT NULL,\n"
                    + "    Nombre_Grupo varchar(30) DEFAULT NULL,\n"
                    + "    Discografica varchar(30) DEFAULT NULL,\n"
                    + "    Lider varchar(30) DEFAULT NULL,\n"
                    + "    PRIMARY KEY (id)\n"
                    + ");");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Tabla creada correctamente.");
        }
    }

    public void insertarCancion() {
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("INSERT INTO cancion VALUE(1, 'Brittle Bones Nicky', '3:17', '2018/01/01');");
            sta.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Cancion insertada correctamente.");
        }
    }
}
