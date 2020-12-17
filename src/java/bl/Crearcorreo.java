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
        String asunto = new String(saludo() + " Estimado(a): " + parametros[0] +"<br><br>"
                + "Reciba un cordial saludo de parte de NPC Technology, <br><br>"
                + "Su solicitud de contáctenos ha sido enviada." + "<br>"
                + "Pronto el poderosísimo NPC le responderá"  +"<br><br>"
                + "Salu2" + "<br>"
                + "TE AMO VAMPIRINA" + "<br>"
                + "Por favor no responda este correo joputa <br>");
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

        String asunto = new String("Holap poderosísimo NPC, novio de la poderosísima Vampirina, <br><br>"
                + "Un emoxito cliente kiere contactar con usted" + "<br>"
                + "Respondale rapidin para que no se enoje"  +"<br><br>"
                + "el clientecito necito y tontito "+ parametros[0] +"<br><br>"
                + "con el correo "+ parametros[1] +"<br><br>"
                +"le escribio lo siguiente jeje "+ parametros[2] +"<br><br>"
                + "Salu2 y me saludas a Vampi" + "<br>");
        detalle = formatocorreo(OrtografiaDetalle(asunto));
        return detalle;
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
