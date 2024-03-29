/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import bl.Producto;
import java.sql.Connection;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author ferpm
 */
public class Servicios {

    public static String ruta = "C:/NPC/";
    public static String rutaProductos = "C:/NPC/Productos";

    public static String ObtenerTextoIndex() throws Exception {
        InformacionDal info = new InformacionDal();
        return info.ObtenerTextoIndex();
    }

    public static boolean ActualizarTextoIndex(String texto) throws Exception {
        InformacionDal info = new InformacionDal();
        return info.ActualizarTextoIndex(texto);
    }

    public static boolean AgregarProducto(Producto producto) throws Exception {
        InformacionDal info = new InformacionDal();
        return info.AgregarProducto(producto);
    }

    public static void AgregarImagenProducto(String imagen, int idProducto) throws Exception {
        InformacionDal info = new InformacionDal();
        info.AgregarImagenProducto(imagen, idProducto);
    }

    public static int TomarUltimoProducto() throws Exception {
        InformacionDal info = new InformacionDal();
        return info.TomarUltimoProducto();
    }

    public static List<Producto> retornaProductos() throws Exception {
        InformacionDal info = new InformacionDal();
        return info.retornaProductos();
    }

    public static List<Producto> ObtenerProductosCateg(int id_Categoria) throws Exception {
        InformacionDal info = new InformacionDal();
        return info.ObtenerProductosCateg(id_Categoria);
    }

    public static List<Producto> ObtenerProductos() throws Exception {
        InformacionDal info = new InformacionDal();
        return info.ObtenerProductos();
    }
    
    public static String ObtenerImagenProducto(int idProducto) throws Exception {
        InformacionDal info = new InformacionDal();
        return info.ObtenerImagenProducto(idProducto);
    }
    
    public static void AgregarCarrito(String ordenCompra, int idProducto, int cantidad, float precio, String nombreCliente) throws Exception {
        InformacionDal info = new InformacionDal();
        info.AgregarCarrito( ordenCompra,  idProducto,  cantidad,  precio,  nombreCliente);
    }
    
    public static int ObtenerCantidadProducto(int idProducto) throws Exception {
        InformacionDal info = new InformacionDal();
        return info.ObtenerCantidadProducto(idProducto);
    }
    
    public static void EditarProducto(int idProducto, int stock) throws Exception {
        InformacionDal info = new InformacionDal();
        info.EditarProducto(idProducto, stock);
    }
    
    public static int ObtenerConsecutivo() throws Exception {
        InformacionDal info = new InformacionDal();
        return info.ObtenerConsecutivo();
    }
    
    public static void AumentarConsecutivo() throws Exception {
        InformacionDal info = new InformacionDal();
        info.AumentarConsecutivo();
    }
    
    public static Producto ObtenerDetalleProducto(int idProducto) throws Exception {
        InformacionDal info = new InformacionDal();
        return info.ObtenerDetalleProducto(idProducto);
    }
    
    public static void EliminarProducto(int idProducto) throws SQLException{
        InformacionDal info = new InformacionDal();
        info.EliminarProducto(idProducto);
    }
    
    public static void ActualizarProducto(int idProducto, String codigo, String nombre, int idCategoria, int stock, String descripcion, float precio) throws Exception{
        InformacionDal info = new InformacionDal();
        info.ActualizarProducto(idProducto, codigo, nombre, idCategoria, stock, descripcion, precio);
    }
}
