package br.usjt.chatbot;

import java.util.Arrays;

/**
 * Created by tnf98 on 19/04/2018.
 */

public class Resposta {

    private int id;
    private String[] resposta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getResposta() {
        return resposta;
    }

    public void setResposta(String[] resposta) {
        this.resposta = resposta;
    }

    @Override
    public String toString() {
        return "Resposta{" +
                "id=" + id +
                ", resposta=" + Arrays.toString(resposta) +
                '}';
    }
}
