package bl;

import dal.Servicios;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Fernanda Porras
 */
@ManagedBean(name = "MostrarProductosB")
@ViewScoped

public class MostrarProductos {

    private List<Producto> productos;

    public MostrarProductos() throws IOException, Exception {
        productos = Servicios.ObtenerProductos();

    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
