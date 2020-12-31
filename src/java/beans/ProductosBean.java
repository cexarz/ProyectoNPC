package beans;

import bl.CrearArchivo;
import bl.ImagenProducto;
import bl.Img;
import bl.Producto;
import dal.Servicios;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "productosB") //Aqui va el nombre del Bean
@ViewScoped // Aquí va el tipo de scope que quiere que tenga este controlador

public class ProductosBean {
    private int categoria = 0;
    private Producto producto = new Producto();
    private ImagenProducto imagen;
    private CrearArchivo ca = new CrearArchivo();
    private List<ImagenProducto> listaImagenes = new ArrayList<>();
    
    public void AgregarProducto() throws Exception{
        
        producto.getCodigo();
        producto.getNombre();
        producto.getId_Categoria();
        producto.getStock();
        producto.getDescripcion();
        producto.getPrecio();
        Servicios.AgregarProducto(producto);
        int ultimoProducto = Servicios.TomarUltimoProducto();
        if (listaImagenes != null) {
                    for (int i = 0; i < listaImagenes.size(); i++) {
                        Servicios.AgregarImagenProducto(listaImagenes.get(i).getImagen(), ultimoProducto);
                        listaImagenes.get(i).setIdProducto(ultimoProducto);
                        ca.crearArchivoProducto(listaImagenes.get(i), producto.getId_Categoria());
                    }
                }
        
    }
    
    public void obtenerImagenProducto(FileUploadEvent event) {
        try {
            imagen = new ImagenProducto();
            imagen.setImagen(event.getFile().getFileName());
            imagen.setContenttype(event.getFile().getContentType());
            imagen.setInputstream(event.getFile().getInputstream());
            imagen.setTamano(event.getFile().getSize());
            if (producto.getId_Categoria() == 1) {
                imagen.setCategoria("Impresora");
            } else if (producto.getId_Categoria() == 2) {
                imagen.setCategoria("Computadoras");
            }//Aqui ponga los else if de acuerdo a las demás categorias que faltan
            listaImagenes.add(imagen);

        } catch (Exception e) {
            System.err.println("Error al obtener el archivo " + e.toString());
        }
    }

     public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String filename = context.getExternalContext().getRequestParameterMap().get("filename");
            return new DefaultStreamedContent(new FileInputStream(new File("/C:/NPC/Productos/", filename)));
        }
    }
    
    /*public void limpiarVariables() {
        this.producto = new Producto();
        this.ca = new CrearArchivo();
        this. = "";
        this.identificacion = "";
        listaTestigos.clear();
        lista_Adj.clear();
        this.nombrecompleto = "";
        selectadjsol = new AdjuntoSolicitud();
        this.telefono = "";
        sol = new Solicitud();
        this.provincia = 0;
    }*/

    
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

    public ImagenProducto getImagen() {
        return imagen;
    }

    public void setImagen(ImagenProducto imagen) {
        this.imagen = imagen;
    }

    public List<ImagenProducto> getListaImagenes() {
        return listaImagenes;
    }

    public void setListaImagenes(List<ImagenProducto> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }


    public CrearArchivo getCa() {
        return ca;
    }

    public void setCa(CrearArchivo ca) {
        this.ca = ca;
    }
   
}
