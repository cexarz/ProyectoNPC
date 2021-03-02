package bl;

import dal.Servicios;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Correo {

    private final Properties properties = new Properties();
    private Session session;
    MimeMultipart multipart = null;

    private void init() {
     
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.mail.sender", "npcprueba@gmail.com");
        properties.put("mail.smtp.password", "Npc12345");
        properties.put("mail.smtp.user", "npcprueba@gmail.com");

        properties.put("mail.smtp.auth", "true");
        session = Session.getDefaultInstance(properties);
    }

    //Correo sin adjuntos
    public boolean send(String destino, String copia, String asunto, String mensaje) {
        init();
        boolean r = true;
        List<String> adjuntos = new ArrayList<String>();
        multipart = new MimeMultipart("related");
        try {
            try {
                addContent(mensaje);
                
            } catch (Exception ex) {
                System.out.printf(ex.toString());
            }

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));

            if (!copia.equals("")) {
                message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(copia));
            }
            message.setSubject(asunto);
            message.setContent(multipart);
            Transport t = session.getTransport("smtp");
            t.connect((String) properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.password"));
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            if (!copia.equals("")) {
                t.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
            }
            t.close();
            System.out.printf("Correo enviado satisfactoriamente: " + destino);
            multipart = null;
            return r;
        } catch (MessagingException e) {
            System.out.printf(e.toString());
            r = false;
            return r;
        }
    }
    
    public boolean sendPagoUsuario(String destino, String copia, String asunto, String mensaje) {
        init();
        boolean r = true;
        List<String> adjuntos = new ArrayList<String>();
        multipart = new MimeMultipart("related");
        try {
            try {
                addContent(mensaje);
                addAttach("C:/NPC/DatosPago.png"); //ruta donde se encuentra el fichero que queremos adjuntar.
                             
            } catch (Exception ex) {
                System.out.printf(ex.toString());
            }

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));

            if (!copia.equals("")) {
                message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(copia));
            }
            message.setSubject(asunto);
            message.setContent(multipart);
            Transport t = session.getTransport("smtp");
            t.connect((String) properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.password"));
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            if (!copia.equals("")) {
                t.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
            }
            t.close();
            System.out.printf("Correo enviado satisfactoriamente: " + destino);
            multipart = null;
            return r;
        } catch (MessagingException e) {
            System.out.printf(e.toString());
            r = false;
            return r;
        }
    }
 

    public void addContent(String htmlText) throws Exception {
        // first part (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(htmlText, "text/html");
        // add it
        this.multipart.addBodyPart(messageBodyPart);
    }

    public void addCID(String cidname, String pathname) throws Exception {
        DataSource fds = new FileDataSource(pathname);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<" + cidname + ">");
        this.multipart.addBodyPart(messageBodyPart);
    }

    public void addAttach(String pathname) throws Exception {
        File file = new File(pathname);
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource ds = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(ds));
        messageBodyPart.setFileName(file.getName());
        messageBodyPart.setDisposition(Part.ATTACHMENT);
        this.multipart.addBodyPart(messageBodyPart);
    }
    
}
