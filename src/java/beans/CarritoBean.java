package beans;

import bl.Producto;
import dal.Servicios;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Fernanda Porras
 */
@ManagedBean(name = "CarritoB")
@RequestScoped
public class CarritoBean {

    private Producto productoSeleccionado;
    private static List<Producto> listaCarrito = new ArrayList<>();

    public CarritoBean() throws Exception {

    }

    public StreamedContent getImageProductoImpresora() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String filename = context.getExternalContext().getRequestParameterMap().get("imagen");
            String idProducto = context.getExternalContext().getRequestParameterMap().get("id");

            return new DefaultStreamedContent(new FileInputStream(new File("/C:/NPC/Productos/Impresoras/" + idProducto + "/", filename)));
        }
    }

    public static void agregarProductoCarrito(Producto productoCarrito) throws Exception {
        boolean existe = false;
        int contador = 0;
        int index = 0;
        while (contador < listaCarrito.size()) {
            if (listaCarrito.get(contador).getId_Producto() == productoCarrito.getId_Producto()) {
                existe = true;
                index = contador;
            }
            contador++;       
        }
        if (existe) {
            listaCarrito.get(index).setCantidadCarrito(listaCarrito.get(index).getCantidadCarrito() + 1);
        }else{
            listaCarrito.add(productoCarrito);
        }      
    }
    
    public void eliminarProductoCarrito(){
         this.productoSeleccionado.getId_Producto();
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

}
