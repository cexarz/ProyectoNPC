package dal;

import bl.Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
            cs = cnx.prepareCall("{ call AgregarProducto (?,?,?,?,?,?) }");
            cs.setString(1, producto.getCodigo().trim());
            cs.setString(2, producto.getNombre().trim());
            cs.setInt(3, producto.getId_Categoria());
            cs.setInt(4, producto.getStock());
            cs.setString(5, producto.getDescripcion().trim());
            cs.setFloat(6, producto.getPrecio());
            cs.execute();
            existe = true;
        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
        return existe;
    }
    
    public void AgregarImagenProducto(String imagen, int idProducto) throws Exception {
        try {
            Connection cnx = csw.ObtenerConexion();
            CallableStatement cs = null;
            cs = cnx.prepareCall("{ call AgregarImagenProducto (?,?) }");
            cs.setString(1, imagen.trim());
            cs.setInt(2, idProducto);
            cs.execute();

        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
    }
    
    public int TomarUltimoProducto() throws Exception {
        int ultimaSolicitud = 0;
        try {
            CallableStatement cs = null;
            Connection cnx = csw.ObtenerConexion();
            cs = cnx.prepareCall("{ call TomarUltimoProducto }");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ultimaSolicitud = rs.getInt(1);
            }
            rs.close();

        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
        return ultimaSolicitud;
    }
   
    public List<Producto> retornaProductos() throws Exception {

        List<Producto> listaProductos = new ArrayList();
        try {
            CallableStatement cs = null;
            Connection cnx = csw.ObtenerConexion();
            cs = cnx.prepareCall("{ call retornaProductos }");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setNombre(rs.getString(1));
                producto.setDescripcion(rs.getString(2));
                producto.setPrecio(rs.getFloat(3));

                listaProductos.add(producto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
        return listaProductos;
    }   
    
    public List<Producto> ObtenerProductosCateg(int id_Producto, int id_Categoria) throws Exception {

        List<Producto> listaS = new ArrayList();
        try {
            CallableStatement cs = null;
            Connection cnx = csw.ObtenerConexion();
            cs = cnx.prepareCall("{ call retornaProductosPorCategoria (?)}");
            cs.setInt(1, id_Producto);
            cs.setInt(2, id_Categoria);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
              Producto producto = new Producto();
                producto.setNombre(rs.getString(1));
                producto.setDescripcion(rs.getString(2));
                producto.setPrecio(rs.getFloat(3));

                listaS.add(producto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
        return listaS;
    }
}
