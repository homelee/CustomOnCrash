package uuxia.com.library.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * The type Popup authenticator.
 */
public class PopupAuthenticator extends Authenticator {
    private String userName;
    private String pwd;

    /**
     * Instantiates a new Popup authenticator.
     *
     * @param paramString1 the param string 1
     * @param paramString2 the param string 2
     */
    public PopupAuthenticator(String paramString1, String paramString2) {
        this.userName = paramString1;
        this.pwd = paramString2;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.userName, this.pwd);
    }
}
