package dal;

import bl.Producto;
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
    
    public boolean AgregarProducto(Producto producto) throws Exception{
        boolean existe = false;
        try {
            Connection cnx = csw.ObtenerConexion();
            CallableStatement cs = null;
            cs = cnx.prepareCall("{ call AgregarProducto (?,?,?,?,?,?,?)) }");
            cs.setString(1, producto.getCodigo().trim());
            cs.setString(2, producto.getNombre().trim());
            cs.setInt(3, producto.getId_Categoria());
            cs.setInt(4, producto.getStock());
            cs.setString(5, producto.getDescripcion().trim());
            cs.setFloat(6, producto.getPrecio());
            cs.setString(7, producto.getImagen());
            cs.execute();
            existe = true;
        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
        return existe;
    }
    
}
