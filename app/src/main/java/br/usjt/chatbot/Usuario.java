package br.usjt.chatbot;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tnf98 on 10/04/2018.
 */

public class Usuario {

    private String nome;
    private String email;
    private String telefone;
    private Date date;

    public Usuario(){

    }

    public Usuario(String nome, String email, String telefone, Date date) {
        setNome(nome);
        setEmail(email);
        setTelefone(telefone);
        setDate(date);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
