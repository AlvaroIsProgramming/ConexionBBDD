/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aghsk
 */
public class GestorBBDD {

    Connection conexion;

    public GestorBBDD() {

        String urlBBDD = "jdbc:mysql://localhost:3306/discografica?serverTimezone=UTC";
        String usuario = "root";
        String password = "root";

        try {
            conexion = DriverManager.getConnection(urlBBDD, usuario, password);
            if (conexion != null) {
                System.out.println("Conectado a la BBDDD Centro de Estudios");
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int cerrarConexion() {
        try {
            conexion.close();
            
            return 0;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return -1;
        }
    }
}
