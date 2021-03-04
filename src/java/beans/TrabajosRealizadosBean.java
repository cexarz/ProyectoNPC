package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "TrabajosRealizadosB")
@RequestScoped

public class TrabajosRealizadosBean {

    private List<String> images;
    public List<String> nombre;

    public void init() {

        images = new ArrayList<String>();
        for (int i = 1; i <= 3; i++) {
            images.add("imagen" + i + ".png");
        }
    }

    public List<String> getImages() {
        return images;
    }

    public List<String> getNombre() {
        return nombre;
    }

    public void setNombre(List<String> nombre) {
        this.nombre = nombre;
    }

}
