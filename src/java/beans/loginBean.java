package beans;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fernanda Porras
 */

@ManagedBean(name = "LoginB")
@SessionScoped

public class loginBean {

    private String inputUser = "";
    private String inputPassword = "";
    private String user = "npcadmin";
    private String password = "npcadmin";

    public void validarlogin() throws IOException {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        if (inputUser.equals(user) && inputPassword.equals(password)) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("indexAdministrador.xhtml");
        } else {inputUser = inputPassword = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña inválida", ""));
        }

    }

    public String getInputUser() {
        return inputUser;
    }

    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    public String getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }

}
