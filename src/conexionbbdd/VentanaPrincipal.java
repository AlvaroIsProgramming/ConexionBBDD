/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbbdd;

/**
 *
 * @author aghsk
 */
public class VentanaPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PoolConexiones gestor = new PoolConexiones();

        gestor.cerrarConexion();
        /*
       if(retorno == 0){
           System.out.println("Desconectado de la BBDD");
       }else{
           System.out.println("Error en la desconexionF");
       }*/
    }

}
