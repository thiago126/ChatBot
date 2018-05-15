package br.usjt.chatbot.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tnf98 on 10/04/2018.
 */

public class Usuario implements Serializable{

    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;
    private String telefone;

    public Usuario(){

    }

    public Usuario(String nome, String email, String telefone) {
        setNome(nome);
        setEmail(email);
        setTelefone(telefone);
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

    @Override
    public String toString() {
        return "Usuario{" +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
