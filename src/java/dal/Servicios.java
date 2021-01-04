/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dal;

import bl.Producto;
import java.sql.Connection;
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
    
      public static List <Producto>retornaProductos() throws Exception {
        InformacionDal info = new InformacionDal();
        return info.retornaProductos();
    }
       public static List <Producto>ObtenerProductosCateg(int id_Producto, int id_Categoria) throws Exception {
        InformacionDal info = new InformacionDal();
        return info.ObtenerProductosCateg(id_Producto, id_Categoria);
    }
}
