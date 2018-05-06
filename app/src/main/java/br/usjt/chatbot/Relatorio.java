package br.usjt.chatbot;

import android.widget.SectionIndexer;

import java.util.Date;

/**
 * Created by tnf98 on 25/04/2018.
 */

public class Relatorio {

    private String pergunta;
    private String resposta;
    private Date data;
    private Usuario usuario;

    public Relatorio() {
    }

    public Relatorio(String pergunta, String resposta, Date data, Usuario usuario) {
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.data = data;
        this.usuario = usuario;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
