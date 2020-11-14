/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dal;

import bl.Producto;
import java.sql.Connection;

/**
 *
 * @author ferpm
 */
public class Servicios {
    public static String ruta = "C:/NPC/";
    
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
    
}
