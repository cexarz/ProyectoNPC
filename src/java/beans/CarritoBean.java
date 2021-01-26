package beans;

import bl.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fernanda Porras
 */
@ManagedBean(name = "CarritoB")
@RequestScoped
public class CarritoBean {
    private Producto productoSeleccionado;
    private Producto itemCarrito;
    private List<Producto> listaCarrito = new ArrayList<>();
    
    public CarritoBean(){
        /*HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        itemCarrito = (Producto) httpSession.getAttribute("itemCarrito");
        listaCarrito.add(itemCarrito);*/
    }
    
    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public List<Producto> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(List<Producto> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }

    public Producto getItemCarrito() {
        return itemCarrito;
    }

    public void setItemCarrito(Producto itemCarrito) {
        this.itemCarrito = itemCarrito;
    }
    
}
