package beans;

import bl.CrearArchivo;
import bl.Img;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ferpm
 */
@ManagedBean(name = "CambioIndexB")
@RequestScoped

public class CambioIndexBean {

    private Img imagen;
    private CrearArchivo ca = new CrearArchivo();
    private String texto;

    public void obtenerArchivo(FileUploadEvent event) {
        try {
            imagen = new Img();
            imagen.setImagen("imagenIndex.png");
            imagen.setContenttype(event.getFile().getContentType());
            imagen.setInputstream(event.getFile().getInputstream());
            imagen.setTamano(event.getFile().getSize());
            
            ca.creararchivos(imagen);
        } catch (Exception e) {
            System.err.println("Error al obtener el archivo " + e.toString());
        }
    }

    
    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String filename = context.getExternalContext().getRequestParameterMap().get("filename");
            return new DefaultStreamedContent(new FileInputStream(new File("/C:/NPC/", filename)));
        }
    }
    
    public Img getImagen() {
        return imagen;
    }

    public void setImagen(Img imagen) {
        this.imagen = imagen;
    }

    public CrearArchivo getCa() {
        return ca;
    }

    public void setCa(CrearArchivo ca) {
        this.ca = ca;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
}
