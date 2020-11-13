package dal;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConexionServidor {
    
    private Connection conn = null;
    
    //Metodo que obtiene la conexion con el pool de conexiones que se encuentra en el servidor GlashFish
    public Connection ObtenerConexion(){
    
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (javax.sql.DataSource) ctx.lookup("jdbc/npc");
            conn = ds.getConnection();
           
        } catch (Exception e) {
            System.err.println("No hay conexion con el servidor: " + e.toString());
        }
      return conn;
      
    }
    
    //Metodo que cierra la conexion con el pool de conexiones en GlashFish
    public void CerrarConexion(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar conexión "+ ex.toString());
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}