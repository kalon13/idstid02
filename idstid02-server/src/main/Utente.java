/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Utente {
    private int id;
    private String user;
    private String psw;
    private int tipo;

    public Utente() {
    }

    public Utente(int id, String user, String psw, int tipo) {
        this.id = id;
        this.user = user;
        this.psw = psw;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
