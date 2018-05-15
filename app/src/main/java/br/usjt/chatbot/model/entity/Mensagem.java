package br.usjt.chatbot.model.entity;

/**
 * Created by tnf98 on 07/05/2018.
 */

public class Mensagem {

    private String mensagem;
    private String nome;
    private int interacao;
    private boolean lado, satisfacao;

    public Mensagem(){

    }

    private Mensagem(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLado(boolean lado) {
        this.lado = lado;
    }

    public boolean isLado() {
        return lado;
    }

    public void setSatisfacao(boolean satisfacao) {
        this.satisfacao = satisfacao;
    }

    public boolean isSatisfacao(){
        return satisfacao;
    }

    public int getInteracao() {
        return interacao;
    }

    public void setInteracao(int interacao) {
        this.interacao = interacao;
    }
}

