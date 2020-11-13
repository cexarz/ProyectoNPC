package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author ferpm
 */
public class InformacionDal {
    
    private ConexionServidor csw = new ConexionServidor();
    
    
    public String ObtenerTextoIndex() throws Exception {
        String texto = "";
        try {
            CallableStatement cs = null;
            Connection cnx = csw.ObtenerConexion();
            cs = cnx.prepareCall("{ call ObtenerTextoIndex}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                texto = rs.getString(1);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
        return texto;
    }
    
    public boolean ActualizarTextoIndex(String texto) throws Exception {
        boolean exito = false;
        try {
            Connection cnx = csw.ObtenerConexion();
            CallableStatement cs = null;
            cs = cnx.prepareCall("{ call ActualizarTextoIndex (?) }");
            cs.setString(1, texto);
            cs.execute();
            exito = true;
        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
        return exito;
    }
    
}
