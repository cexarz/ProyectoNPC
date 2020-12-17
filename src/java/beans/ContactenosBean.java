package beans;

import bl.Crearcorreo;
import bl.DatosContactenos;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Fernanda Porras 
 */
@ManagedBean(name = "ContactenosB")
@RequestScoped

public class ContactenosBean {
    private DatosContactenos datosCon = new DatosContactenos();
    
    public void enviarCorreo() throws IOException{
        datosCon.getNombre();
        datosCon.getCorreo();
        datosCon.getDescripcion();
        enviarCorreoUsuario();
        enviarCorreoNpc();
        limpiarVariables();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        
    }
    
    public void enviarCorreoUsuario() {
        try {
            Crearcorreo cc = new Crearcorreo();
            String destino = datosCon.getCorreo();
            String copia = "";
            String asunto = "Envío de solicitud";
            String mensaje = " ";

            String[] parametros = new String[1];
            parametros[0] = datosCon.getNombre();
            boolean resp = cc.CorreoUsuario(destino, copia, asunto, parametros);
            if (resp == true) {
                System.out.println("Correo enviado con éxito");
            } else {
                System.out.println("El correo no se envió");
            }

        } catch (Exception e) {
            System.err.println("Error al enviar correo " + e.toString());
        }
    }
    public void enviarCorreoNpc() {
        try {
            Crearcorreo cc = new Crearcorreo();
            String destino = "npcprueba@gmail.com";
            String copia = "";
            String asunto = "Nueva solicitud";
            String mensaje = " ";

            String[] parametros = new String[3];
            parametros[0] = datosCon.getNombre();
            parametros[1] = datosCon.getCorreo();
            parametros[2] = datosCon.getDescripcion();
            boolean resp = cc.CorreoNpc(destino, copia, asunto, parametros);
            if (resp == true) {
                System.out.println("Correo enviado con éxito");
            } else {
                System.out.println("El correo no se envió");
            }

        } catch (Exception e) {
            System.err.println("Error al enviar correo " + e.toString());
        }
    }
    
    public void limpiarVariables(){
        datosCon.setNombre("");
        datosCon.setCorreo("");
        datosCon.setDescripcion("");
    }

    public DatosContactenos getDatosCon() {
        return datosCon;
    }

    public void setDatosCon(DatosContactenos datosCon) {
        this.datosCon = datosCon;
    }


    
}
