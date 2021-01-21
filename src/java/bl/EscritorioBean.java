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
@ManagedBean(name = "EscritorioB")
@ViewScoped

public class EscritorioBean {

    private List<Producto> productos;

    public EscritorioBean() throws IOException, Exception {
        productos = Servicios.ObtenerProductosCateg(1);

    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
