package main;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Utente {
    protected int userId;
    protected String user;
    protected String psw;
    protected int tipo;

    public Utente() {}

    public Utente(int userId, String user, String psw, int tipo) {
        this.userId = userId;
        this.user = user;
        this.psw = psw;
        this.tipo = tipo;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
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

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
