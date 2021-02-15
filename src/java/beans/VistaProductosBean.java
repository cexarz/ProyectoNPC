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
@ManagedBean(name = "VistaProductosB")
@RequestScoped
public class VistaProductosBean {
    private List<Producto> productosImpresoras = new ArrayList<>();
    private List<Producto> productosEscritorio = new ArrayList<>();
    private List<Producto> productosPortatiles = new ArrayList<>();
    private String nombreImagen = "";

    private Producto productoSeleccionado;
    
    private List<Producto> listaCarrito = new ArrayList<>();
    
    public VistaProductosBean() throws IOException, Exception {
        productosImpresoras = Servicios.ObtenerProductosCateg(1);
        productosEscritorio = Servicios.ObtenerProductosCateg(2);
        productosPortatiles = Servicios.ObtenerProductosCateg(3);
        
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
            
            return new DefaultStreamedContent(new FileInputStream(new File("/C:/NPC/Productos/Impresoras/"+idProducto+"/", filename)));
        }
    }
    public void agregarCarrito() throws Exception{
        Producto productoCarrito = new Producto();
        productoCarrito.setId_Producto(this.productoSeleccionado.getId_Producto()); 
        productoCarrito.setNombre(this.productoSeleccionado.getNombre());
        productoCarrito.setPrecio(this.productoSeleccionado.getPrecio());
        productoCarrito.setImagen(this.productoSeleccionado.getImagen());
        productoCarrito.setCodigo(this.productoSeleccionado.getCodigo());
        productoCarrito.setStock(this.productoSeleccionado.getStock());
        productoCarrito.setCantidadCarrito(1);
        CarritoBean.agregarProductoCarrito(productoCarrito);
    }

    public List<Producto> getProductosImpresoras() {
        return productosImpresoras;
    }

    public void setProductosImpresoras(List<Producto> productosImpresoras) {
        this.productosImpresoras = productosImpresoras;
    }

    public List<Producto> getProductosEscritorio() {
        return productosEscritorio;
    }

    public void setProductosEscritorio(List<Producto> productosEscritorio) {
        this.productosEscritorio = productosEscritorio;
    }

    public List<Producto> getProductosPortatiles() {
        return productosPortatiles;
    }

    public void setProductosPortatiles(List<Producto> productosPortatiles) {
        this.productosPortatiles = productosPortatiles;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
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
