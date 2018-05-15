package br.usjt.chatbot.model.service;

import org.json.JSONException;

import java.io.IOException;

import br.usjt.chatbot.Contexto;
import br.usjt.chatbot.model.dao.MensagemDAODb;
import br.usjt.chatbot.model.dao.MensagemDAORest;
import br.usjt.chatbot.model.entity.Mensagem;

/**
 * Created by tnf98 on 14/05/2018.
 */

public class MensagemService {

    private MensagemDAORest daoRest;
    private MensagemDAODb daoDb;

    public MensagemService(){
        daoRest = new MensagemDAORest();
        daoDb = new MensagemDAODb(Contexto.getValue());
    }

    public Mensagem obterResposta(String pergunta) throws IOException, JSONException {
        return daoRest.obterResposta(pergunta);
    }

    public void insereMensagem(Mensagem mensagem){
        daoDb.insereMensagem(mensagem);
    }

    public Mensagem obterMensagem(){
        return daoDb.selecionaMensagem();
    }

    public void excluirMensagem(){
        daoDb.excluirMensagem();
    }

}
