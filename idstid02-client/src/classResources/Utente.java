/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classResources;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Utente {
    protected int utenteId;
    protected String user;
    protected String psw;
    protected int tipo;

    public Utente() {
    }

    public Utente(int id, String user, String psw, int tipo) {
        this.utenteId = id;
        this.user = user;
        this.psw = psw;
        this.tipo = tipo;
    }

    public Integer getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int id) {
        this.utenteId = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo) {
        this.tipo = tipo;
    }    
}
