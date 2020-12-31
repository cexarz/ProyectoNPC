package bl;

import java.io.InputStream;

/**
 * @author Fernanda Porras
 */
public class ImagenProducto {

    private int idImagen = 0;
    private String imagen = "";
    private String contenttype = "";
    private InputStream inputstream = null;
    private String categoria = "";
    private long tamano = 0;
    private int idProducto = 0;

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public long getTamano() {
        return tamano;
    }

    public void setTamano(long tamano) {
        this.tamano = tamano;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }


}