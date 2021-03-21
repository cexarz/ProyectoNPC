package beans;

import bl.Producto;
import dal.Servicios;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Fernanda Porras
 */
@ManagedBean(name = "EditarProductoB")
@ViewScoped

public class EditarProductoBean {

    private List<Producto> productos = new ArrayList<>();
    private List<Producto> listaFiltrados = new ArrayList<>();
    private Producto productoSeleccionado = new Producto();

    public EditarProductoBean() throws IOException, Exception {
        productos = Servicios.retornaProductos();
    }

//    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
//        if (filterText == null || filterText.equals("")) {
//            return true;
//        }
//        Producto producto = (Producto) value;
//        return producto.getCodigo().toLowerCase().contains(filterText)
//                || producto.getNombre().toLowerCase().contains(filterText)
//                || producto.getDescripcion().toLowerCase().contains(filterText);
//    }

    public void editarProducto(int idProducto) throws IOException, Exception {
        Servicios.ActualizarProducto(idProducto, productoSeleccionado.getCodigo(), productoSeleccionado.getNombre(), productoSeleccionado.getId_Categoria(), productoSeleccionado.getStock(),
                productoSeleccionado.getDescripcion(), productoSeleccionado.getPrecio());
        FacesContext.getCurrentInstance().getExternalContext().redirect("EditarProductos.xhtml");
    }
    
    public void eliminarProducto(int idProducto) throws SQLException, IOException{
        Servicios.EliminarProducto(idProducto);
        FacesContext.getCurrentInstance().getExternalContext().redirect("EditarProductos.xhtml");
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

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }


}
