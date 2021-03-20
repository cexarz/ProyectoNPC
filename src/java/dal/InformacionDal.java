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

    public boolean AgregarProducto(Producto producto) throws Exception {
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

    public List<Producto> ObtenerProductosCateg(int id_Categoria) throws Exception {
        List<Producto> listaS = new ArrayList();
        try {
            CallableStatement cs = null;
            Connection cnx = csw.ObtenerConexion();
            cs = cnx.prepareCall("{ call retornaProductosPorCategoria (?)}");
            cs.setInt(1, id_Categoria);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId_Producto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecio(rs.getFloat(3));
                producto.setDescripcion(rs.getString(4));
                producto.setPrecio(rs.getFloat(5));
                producto.setImagen(rs.getString(6));
                producto.setStock(rs.getInt(7));
                listaS.add(producto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
        return listaS;
    }

    public List<Producto> ObtenerProductos() throws Exception {
        List<Producto> nombre = new ArrayList();
        try {
            CallableStatement cs = null;
            Connection cnx = csw.ObtenerConexion();
            cs = cnx.prepareCall("{ call ObtenerProductos}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setNombre(rs.getString(1));
                nombre.add(producto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
        return nombre;
    }

    public String ObtenerImagenProducto(int idProducto) throws Exception {
        String nombre = "";
        try {
            CallableStatement cs = null;
            Connection cnx = csw.ObtenerConexion();
            cs = cnx.prepareCall("{ call ObtenerImagenProducto (?)}");
            cs.setInt(1, idProducto);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {

                nombre = rs.getString(1);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
        return nombre;
    }

    public void AgregarCarrito(String ordenCompra, int idProducto, int cantidad, float precio, String nombreCliente) throws Exception {
        try {
            Connection cnx = csw.ObtenerConexion();
            CallableStatement cs = null;
            cs = cnx.prepareCall("{ call AgregarCompra (?,?,?,?,?) }");
            cs.setString(1, ordenCompra);
            cs.setInt(2, idProducto);
            cs.setInt(3, cantidad);
            cs.setFloat(4, precio);
            cs.setString(5, nombreCliente);
            cs.execute();

        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
    }

    public int ObtenerCantidadProducto(int idProducto) throws Exception {
        int cantidad = 0;
        try {
            CallableStatement cs = null;
            Connection cnx = csw.ObtenerConexion();
            cs = cnx.prepareCall("{ call ObtenerCantidadProducto (?)}");
            cs.setInt(1, idProducto);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                cantidad = rs.getInt(1);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
        return cantidad;
    }

    public void EditarProducto(int idProducto, int stock) throws Exception {
        Connection cnx = csw.ObtenerConexion();
        CallableStatement cs = null;
        cs = cnx.prepareCall("{ call EditarProducto (?,?) }");
        cs.setInt(1, idProducto);
        cs.setInt(2, stock);
        cs.execute();
    }

    public int ObtenerConsecutivo() throws Exception {
        int cantidad = 0;
        try {
            CallableStatement cs = null;
            Connection cnx = csw.ObtenerConexion();
            cs = cnx.prepareCall("{ call ObtenerConsecutivo }");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                cantidad = rs.getInt(1);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            csw.CerrarConexion();
        }
        return cantidad;
    }

    public void AumentarConsecutivo() throws Exception {
        Connection cnx = csw.ObtenerConexion();
        CallableStatement cs = null;
        cs = cnx.prepareCall("{ call AumentarConsecutivo }");
        cs.execute();
    }

    public void ActualizarProducto(Producto producto) throws Exception {
        Connection cnx = csw.ObtenerConexion();
        CallableStatement cs = null;
        cs = cnx.prepareCall("{ call ActualizarProducto (?,?,?,?,?,?,?) }");
        cs.setInt(1, producto.getId_Producto());
        cs.setString(2, producto.getCodigo());
        cs.setString(3, producto.getNombre());
        cs.setInt(4, producto.getId_Categoria());
        cs.setInt(5, producto.getStock());
        cs.setString(6, producto.getDescripcion());
        cs.setFloat(7, producto.getPrecio());
        cs.execute();
    }
}
