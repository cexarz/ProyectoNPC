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
                File nuevofolder = new File(Servicios.rutaProductos + "/Escritorio/" + img.getIdProducto());
                // Crear la carpeta con el id del producto
                if (!nuevofolder.exists()) {
                    nuevofolder.mkdir();
                }
                // Crear el archivo en la carpeta
                outputStream = new FileOutputStream(new File(Servicios.rutaProductos + "/Escritorio/" + img.getIdProducto() + "/" + img.getImagen()));
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
        } else if(idCategoria == 3){
            try {
                File nuevofolder = new File(Servicios.rutaProductos + "/Portatiles/" + img.getIdProducto());
                // Crear la carpeta con el id del producto
                if (!nuevofolder.exists()) {
                    nuevofolder.mkdir();
                }
                // Crear el archivo en la carpeta
                outputStream = new FileOutputStream(new File(Servicios.rutaProductos + "/Portatiles/" + img.getIdProducto() + "/" + img.getImagen()));
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
    } else if(idCategoria == 4){
            try {
                File nuevofolder = new File(Servicios.rutaProductos + "/Camaras/" + img.getIdProducto());
                // Crear la carpeta con el id del producto
                if (!nuevofolder.exists()) {
                    nuevofolder.mkdir();
                }
                // Crear el archivo en la carpeta
                outputStream = new FileOutputStream(new File(Servicios.rutaProductos + "/Camaras/" + img.getIdProducto() + "/" + img.getImagen()));
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
    } else if(idCategoria == 5){
            try {
                File nuevofolder = new File(Servicios.rutaProductos + "/CCTV/" + img.getIdProducto());
                // Crear la carpeta con el id del producto
                if (!nuevofolder.exists()) {
                    nuevofolder.mkdir();
                }
                // Crear el archivo en la carpeta
                outputStream = new FileOutputStream(new File(Servicios.rutaProductos + "/CCTV/" + img.getIdProducto() + "/" + img.getImagen()));
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
    } else if(idCategoria == 6){
            try {
                File nuevofolder = new File(Servicios.rutaProductos + "/Protectores/" + img.getIdProducto());
                // Crear la carpeta con el id del producto
                if (!nuevofolder.exists()) {
                    nuevofolder.mkdir();
                }
                // Crear el archivo en la carpeta
                outputStream = new FileOutputStream(new File(Servicios.rutaProductos + "/Protectores/" + img.getIdProducto() + "/" + img.getImagen()));
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
    } else if(idCategoria == 7){
            try {
                File nuevofolder = new File(Servicios.rutaProductos + "/Oficina/" + img.getIdProducto());
                // Crear la carpeta con el id del producto
                if (!nuevofolder.exists()) {
                    nuevofolder.mkdir();
                }
                // Crear el archivo en la carpeta
                outputStream = new FileOutputStream(new File(Servicios.rutaProductos + "/Oficina/" + img.getIdProducto() + "/" + img.getImagen()));
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
    }
    }
}
