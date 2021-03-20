package beans;

import bl.Producto;
import dal.Servicios;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Fernanda Porras
 */
@ManagedBean(name = "EditarProductoB")
@RequestScoped

public class EditarProductoBean {
    private List<Producto> productos = new ArrayList<>();
    private List<Producto> listaFiltrados = new ArrayList<>();
    
    public EditarProductoBean() throws IOException, Exception {
        productos = Servicios.ObtenerProductosCateg(1);

    }
    
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        Producto producto = (Producto) value;
        return producto.getCodigo().toLowerCase().contains(filterText) 
                || producto.getNombre().toLowerCase().contains(filterText)
                || producto.getDescripcion().toLowerCase().contains(filterText) ;
    }
    
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Producto> getListaFiltrados() {
        return listaFiltrados;
    }

    public void setListaFiltrados(List<Producto> listaFiltrados) {
        this.listaFiltrados = listaFiltrados;
    }
    
}
