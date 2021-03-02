package bl;

import java.util.Calendar;
import java.util.List;

public class Crearcorreo {

    private Correo mail = new Correo();

    //Método que tiene el formato de los correos enviados por el sistema
    private String formatocorreo(String asunto) {
        String detalle = "";
        detalle = "<html><body>"
                + "<div align=\"center\" style=\"font-family:Arial, Helvetica, sans-serif; font-size:14px;\">"
                + "<table width=\"600px\" >"
                + "<tr><td  width=\"600px\" style=\"border-collapse:collapse; border-color:#585A5F; border-bottom-style:solid; border-left-style:none; border-right-style:none\">"
                + "<br><br>" + asunto + "<br><br>"
                + "</td></tr>"
                + "</table>"
                + "</div></body></html>";
        return detalle;
    }

    public boolean CorreoUsuario(String destino, String copia, String asunto, String[] parametros) throws Exception {
        boolean r = true;
        r = mail.send(destino, copia, asunto, DetalleCorreoUsuario(parametros));
        return r;
    }

    private String DetalleCorreoUsuario(String[] parametros) throws Exception {
        String detalle = "";
        String asunto = new String(saludo() + " Estimado(a): " + parametros[0] + "<br><br>"
                + "Reciba un cordial saludo de parte de NPC Technology, <br><br>"
                + "Su solicitud de contáctenos ha sido enviada." + "<br>"
                + "Le responderemos su solicitud lo más pronto posible." + "<br><br>"
                + "Por favor no responda este correo.  <br>");
        detalle = formatocorreo(OrtografiaDetalle(asunto));
        return detalle;
    }

    public boolean CorreoNpc(String destino, String copia, String asunto, String[] parametros) throws Exception {
        List<String> adjuntos = null;
        boolean r = true;
        r = mail.send(destino, copia, asunto, DetalleCorreoNpc(parametros));
        return r;
    }

    private String DetalleCorreoNpc(String[] parametros) throws Exception {
        String detalle = "";

        String asunto = new String("Un cliente quiere ponerse en contacto" + "<br>"
                + "Favor responder lo más antes posible." + "<br><br>"
                + "el cliente:" + parametros[0] + "<br><br>"
                + "con el correo: " + parametros[1] + "<br><br>"
                + "le escribio lo siguiente: " + parametros[2]);
        detalle = formatocorreo(OrtografiaDetalle(asunto));
        return detalle;
    }

    private String DetalleCorreoCompraUsuario(String[] parametros, List<Producto> listaCarrito, float precioTotal) throws Exception {
        String detalle = "";
        String items = "<html><body>\n<table style='border:2px solid black'>\n"+
                "\n<tr><th>Producto</th>\n<th>Cantidad</th>\n<th>Precio</th>\n</tr>";
        for (int i = 0; i < listaCarrito.size(); i++) {
            Producto producto = listaCarrito.get(i);
            items += "<tr>\n<th>" + producto.getNombre() + "</th>\n<th>" +
                    producto.getCantidadCarrito() + "</th>\n<th>" + producto.getPrecio() + "</th>\n</tr>";
        }
        items += "</table></body></html>";
        String asunto = new String(saludo() + " Estimado(a): " + parametros[0] + "<br><br>"
                + "Reciba un cordial saludo de parte de NPC Technology, <br><br>"
                + "Usted ha realizado un pedido de los siguientes productos:" + "<br><br>"
                + items + "<br>"
                + "<b>Total de la compra: "+ precioTotal + "</b>" + "<br><br>"
                + "Por favor no responda este correo.");
        detalle = formatocorreo(OrtografiaDetalle(asunto));
        return detalle;
    }

    private String DetalleCorreoCompraNpc(String[] parametros) throws Exception {
        String detalle = "";

        String asunto = new String("El cliente " + parametros[0] + "<br>"
                + "con el correo: " + parametros[1] + "<br><br>"
                + "ha realizado un pedido de los siguientes productos:" + "<br><br>");
        detalle = formatocorreo(OrtografiaDetalle(asunto));
        return detalle;
    }

    public boolean CorreoCompraUsuario(String destino, String copia, String asunto, String[] parametros, List<Producto> listaCarrito, float precioTotal) throws Exception {
        boolean r = true;
        r = mail.send(destino, copia, asunto, DetalleCorreoCompraUsuario(parametros, listaCarrito, precioTotal));
        return r;
    }

    public boolean CorreoCompraNpc(String destino, String copia, String asunto, String[] parametros) throws Exception {
        boolean r = true;
        r = mail.send(destino, copia, asunto, DetalleCorreoCompraNpc(parametros));
        return r;
    }

    //Saludo del correo
    private String saludo() {
        String saludo = "";
        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        switch (hora) {
            case 0:
                saludo = "Buenos Días";
                break;
            case 1:
                saludo = "Buenos Días";
                break;
            case 2:
                saludo = "Buenos Días";
                break;
            case 3:
                saludo = "Buenos Días";
                break;
            case 4:
                saludo = "Buenos Días";
                break;
            case 5:
                saludo = "Buenos Días";
                break;
            case 6:
                saludo = "Buenos Días";
                break;
            case 7:
                saludo = "Buenos Días";
                break;
            case 8:
                saludo = "Buenos Días";
                break;
            case 9:
                saludo = "Buenos Días";
                break;
            case 10:
                saludo = "Buenos Días";
                break;
            case 11:
                saludo = "Buenos Días";
                break;
            case 12:
                saludo = "Buenos Días";
                break;
            case 13:
                saludo = "Buenas Tardes";
                break;
            case 14:
                saludo = "Buenas Tardes";
                break;
            case 15:
                saludo = "Buenas Tardes";
                break;
            case 16:
                saludo = "Buenas Tardes";
                break;
            case 17:
                saludo = "Buenas Tardes";
                break;
            case 18:
                saludo = "Buenas Noches";
                break;
            case 19:
                saludo = "Buenas Noches";
                break;
            case 20:
                saludo = "Buenas Noches";
                break;
            case 21:
                saludo = "Buenas Noches";
                break;
            case 22:
                saludo = "Buenas Noches";
                break;
            case 23:
                saludo = "Buenas Noches";
                break;
            default:
                saludo = "";
                break;
        }
        return saludo;
    }

    private String OrtografiaDetalle(String asunto) {

        asunto = asunto.replace("Ñ", "&Ntilde;");
        asunto = asunto.replace("ñ", "&ntilde;");
        asunto = asunto.replace("Á", "&Aacute;");
        asunto = asunto.replace("á", "&aacute;");
        asunto = asunto.replace("É", "&Eacute;");
        asunto = asunto.replace("é", "&eacute;");
        asunto = asunto.replace("Í", "&Iacute;");
        asunto = asunto.replace("í", "&iacute;");
        asunto = asunto.replace("Ó", "&Oacute;");
        asunto = asunto.replace("ó", "&oacute;");
        asunto = asunto.replace("Ú", "&Uacute;");
        asunto = asunto.replace("ú", "&uacute;");
        return asunto;
    }

}
