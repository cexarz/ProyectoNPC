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

    
    public void crearArchivoProducto(ImagenProducto img, int idCategoria) {
        OutputStream outputStream = null;
        if (idCategoria == 1) {
            try {
                File nuevofolder = new File(Servicios.rutaProductos + "/Impresoras/" + img.getIdProducto());
                // Crear la carpeta con el id del producto
                if (!nuevofolder.exists()) {
                    nuevofolder.mkdir();
                }
                // Crear el archivo en la carpeta
                outputStream = new FileOutputStream(new File(Servicios.rutaProductos + "/Impresoras/" + img.getIdProducto()+ "/" + img.getImagen()));
                int read;
                byte[] bytes = new byte[1024];
                while ((read = img.getInputstream().read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.toString());
            } finally {
                if (img.getInputstream() != null) {
                    try {
                        img.getInputstream().close();
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
        } else if(idCategoria == 2){
            try {
                File nuevofolder = new File(Servicios.rutaProductos + "/Computadoras/" + img.getIdProducto());
                // Crear la carpeta con el id del producto
                if (!nuevofolder.exists()) {
                    nuevofolder.mkdir();
                }
                // Crear el archivo en la carpeta
                outputStream = new FileOutputStream(new File(Servicios.rutaProductos + "/Computadoras/" + img.getIdProducto() + "/" + img.getImagen()));
                int read;
                byte[] bytes = new byte[1024];
                while ((read = img.getInputstream().read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.toString());
            } finally {
                if (img.getInputstream() != null) {
                    try {
                        img.getInputstream().close();
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
        } //Despues de aqui van los demas if else con las categorias que faltan
    }

}
