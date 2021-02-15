package beans;

import bl.Crearcorreo;
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
    private static float precioTotal = 0;

    private String mensaje = "";
    
    private String nombreCliente = "";
    private String correoCliente = "";

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
            precioTotal += listaCarrito.get(index).getPrecio();
        } else {
            listaCarrito.add(productoCarrito);
            precioTotal += productoCarrito.getPrecio();
        }
    }

    public void eliminarProductoCarrito() throws IOException {
        int producto = this.productoSeleccionado.getId_Producto();
        boolean encontro = false;
        int contador = 0;
        int index = 0;
        while (contador < listaCarrito.size()) {
            if (listaCarrito.get(contador).getId_Producto() == producto) {
                encontro = true;
                index = contador;
            }
            contador++;
        }
        if (encontro) {
            listaCarrito.remove(index);
            if (listaCarrito.isEmpty()) {
                precioTotal = 0;
            } else {
                precioTotal -= listaCarrito.get(index).getCantidadCarrito() * listaCarrito.get(index).getPrecio();
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("Carrito.xhtml");
        }
    }
   
    public void procederCompra() throws IOException{     
        enviarCorreoUsuario();
        //enviarCorreoNpc();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        
    }
    
    public void enviarCorreoUsuario() {
        try {
            Crearcorreo cc = new Crearcorreo();
            String destino = correoCliente;
            String copia = "";
            String asunto = "Factura de Compra";
            String mensaje = " ";

            String[] parametros = new String[1];
            parametros[0] = nombreCliente;
            boolean resp = cc.CorreoCompraUsuario(destino, copia, asunto, parametros);
            if (resp == true) {
                System.out.println("Correo enviado con éxito");
            } else {
                System.out.println("El correo no se envió");
            }

        } catch (Exception e) {
            System.err.println("Error al enviar correo " + e.toString());
        }
    }
    
    public void enviarCorreoNpc() {
        try {
            Crearcorreo cc = new Crearcorreo();
            String destino = "npcprueba@gmail.com";
            String copia = "";
            String asunto = "Nueva Compra";
            String mensaje = " ";

            String[] parametros = new String[2];
            parametros[0] = nombreCliente;
            parametros[1] = correoCliente;
            boolean resp = cc.CorreoCompraNpc(destino, copia, asunto, parametros);
            if (resp == true) {
                System.out.println("Correo enviado con éxito");
            } else {
                System.out.println("El correo no se envió");
            }

        } catch (Exception e) {
            System.err.println("Error al enviar correo " + e.toString());
        }
    }

    /*public void hayStock() {
        int stock = this.productoSeleccionado.getCantidadCarrito();
        if (stock < this.productoSeleccionado.getStock()) {
            mensaje = "No hay esa cantidad disponible.";
        } else {
            mensaje = "";
        }
    }*/

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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

}
