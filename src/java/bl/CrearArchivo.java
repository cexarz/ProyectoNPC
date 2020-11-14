package bl;

import dal.Servicios;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author ferpm
 */
public class CrearArchivo {

    public void crearArchivoImagenIndex(Img imagen) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(Servicios.ruta + "/" + imagen.getImagen()));
            int read;
            byte[] bytes = new byte[1024];
            while ((read = imagen.getInputstream().read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
        } finally {
            if (imagen.getInputstream() != null) {
                try {
                    imagen.getInputstream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void crearArchivoProductos(Img imagen, Producto idProducto) {

    }

}
