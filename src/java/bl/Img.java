package bl;

import java.io.InputStream;

/**
 *
 * @author ferpm
 */
public class Img {
    private String imagen = "";
    private String contenttype = "";
    private InputStream inputstream = null;
    private long tamano = 0;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public InputStream getInputstream() {
        return inputstream;
    }

    public void setInputstream(InputStream inputstream) {
        this.inputstream = inputstream;
    }

    public long getTamano() {
        return tamano;
    }

    public void setTamano(long tamano) {
        this.tamano = tamano;
    }

    
}
