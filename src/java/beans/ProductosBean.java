package beans;

import bl.CrearArchivo;
import bl.Img;
import bl.Producto;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "productosB") //Aqui va el nombre del Bean
@ViewScoped // Aquí va el tipo de scope que quiere que tenga este controlador

public class ProductosBean {
    private int categoria = 0;
    private Producto producto;
    private Img imagen;
    private CrearArchivo ca = new CrearArchivo();
    
    public void AgregarProducto(){
        
        ca.crearArchivoProductos(imagen,producto);
    }
    
    public void obtenerImagenProducto(FileUploadEvent event) {
        try {
            imagen = new Img();
            imagen.setImagen(event.getFile().getFileName());
            imagen.setContenttype(event.getFile().getContentType());
            imagen.setInputstream(event.getFile().getInputstream());
            imagen.setTamano(event.getFile().getSize());

            
        } catch (Exception e) {
            System.err.println("Error al obtener el archivo " + e.toString());
        }
    }

    
    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Img getImagen() {
        return imagen;
    }

    public void setImagen(Img imagen) {
        this.imagen = imagen;
    }
    
    
}
