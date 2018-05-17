package br.usjt.chatbot.pertencer;

import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.Date;

import br.usjt.chatbot.model.dao.UsuarioDAOFirebase;
import br.usjt.chatbot.model.entity.Mensagem;
import br.usjt.chatbot.model.entity.Relatorio;
import br.usjt.chatbot.model.entity.Usuario;
import br.usjt.chatbot.model.service.MensagemService;
import br.usjt.chatbot.model.service.RelatorioService;
import br.usjt.chatbot.model.service.UsuarioService;

/**
 * Created by tnf98 on 13/05/2018.
 */

public class ChatPertencer implements Pertencer {

    private Usuario usuario;
    private Relatorio relatorio;
    private MensagemService ms;
    private UsuarioService us;
    private RelatorioService rs;

    @Override
    public void onCreate() {
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public Mensagem obterResposta(String pergunta) throws IOException, JSONException {
        ms = new MensagemService();
        Log.d("BANCOOOOOOOOOOOOOO 3", "" + this.obterMensagem().getInteracao());
        Mensagem mensagem = ms.obterResposta(pergunta);
        if(this.obterMensagem().getInteracao() == 2){
            mensagem.setInteracao(3);
        }
        else if(this.obterMensagem().getInteracao() == 1){
            mensagem.setInteracao(2);
        }
        else if(this.obterMensagem().getInteracao() == 0){
            mensagem.setInteracao(1);
        }

        Mensagem pMensagem = new Mensagem();
        pMensagem.setMensagem(pergunta);
        pMensagem.setInteracao(mensagem.getInteracao());
        ms.insereMensagem(pMensagem);
        return mensagem;
}

    public Mensagem obterMensagem(){
        ms = new MensagemService();
        return ms.obterMensagem();
    }

    public void gerarRelatorio(String pergunta, String resposta, int interacao){
        Relatorio relatorio = new Relatorio();
        relatorio.setPergunta(pergunta);
        relatorio.setResposta(resposta);
        relatorio.setInteracao(interacao);
        us = new UsuarioService();
        relatorio.setUsuario(us.selecionaUsuario());
        relatorio.setData(new Date());
        rs = new RelatorioService();
        rs.criarRelatorio(relatorio);
    }

    public void excluirMensagem(){
        MensagemService mService = new MensagemService();
        mService.excluirMensagem();
    }

    public Mensagem obterRespostaInteracao(String pergunta) throws IOException, JSONException {
        ms = new MensagemService();
        Mensagem mensagem = ms.obterResposta(pergunta);
        return mensagem;
    }
}
