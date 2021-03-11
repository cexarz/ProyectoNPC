package beans;

import bl.Crearcorreo;
import bl.Producto;
import dal.Servicios;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletRequest;
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
        int stockProducto = Servicios.ObtenerCantidadProducto(productoCarrito.getId_Producto());
        if (stockProducto > 0) {
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
            Servicios.EditarProducto(productoCarrito.getId_Producto(), productoCarrito.getStock() - 1);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }
    }

    public void eliminarProductoCarrito() throws IOException, Exception {
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
            int stockProducto = Servicios.ObtenerCantidadProducto(listaCarrito.get(index).getId_Producto());
            Servicios.EditarProducto(listaCarrito.get(index).getId_Producto(), stockProducto + listaCarrito.get(index).getCantidadCarrito());
            float precioEliminar = listaCarrito.get(index).getCantidadCarrito() * listaCarrito.get(index).getPrecio();
            listaCarrito.remove(index);
            FacesContext.getCurrentInstance().getExternalContext().redirect("Carrito.xhtml");
            if (listaCarrito.isEmpty()) {
                precioTotal = 0;
            } else {
                precioTotal -= precioEliminar;

            }
        }
    }

    public void procederCompra() throws IOException, Exception {
        enviarCorreoUsuario();
        enviarCorreoNpc();
        agregarOrden();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    
    public void agregarOrden() throws Exception {
        
        int consecutivo = Servicios.ObtenerConsecutivo();
        String ordenCompra = "Orden#" + consecutivo;
        for (int i = 0; i < listaCarrito.size(); i++) {
            Servicios.AgregarCarrito(ordenCompra, listaCarrito.get(i).getId_Producto(), listaCarrito.get(i).getCantidadCarrito(), precioTotal, nombreCliente);
        }
        Servicios.AumentarConsecutivo();
        listaCarrito.clear();
        precioTotal = 0;
    }

    /*public String generarConsecutivoOrden() throws Exception {
     String consecutivo = "";
     Calendar cal = Calendar.getInstance();
     int year = cal.get(Calendar.YEAR);
     if (String.valueOf(Servicios.TomarUltimoConsecutivoConsulta() + 1).length() == 1) {
     consecutivo = "C-AM-" + "00" + (Servicios.TomarUltimoConsecutivoConsulta() + 1) + "-" + year;
     } else if (String.valueOf(Servicios.TomarUltimoConsecutivoConsulta() + 1).length() == 2) {
     consecutivo = "C-AM-" + "0" + (Servicios.TomarUltimoConsecutivoConsulta() + 1) + "-" + year;
     } else {
     consecutivo = "C-AM-" + (Servicios.TomarUltimoConsecutivoConsulta() + 1) + "-" + year;
     }

     return consecutivo;
     }*/
    public void enviarCorreoUsuario() {
        try {
            Crearcorreo cc = new Crearcorreo();
            String destino = correoCliente;
            String copia = "";
            String asunto = "Pedido de Compra";
            String mensaje = " ";

            String[] parametros = new String[1];
            parametros[0] = nombreCliente;
            boolean resp = cc.CorreoCompraUsuario(destino, copia, asunto, parametros, listaCarrito, precioTotal);
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
