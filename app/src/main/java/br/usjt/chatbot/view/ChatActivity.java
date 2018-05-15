package br.usjt.chatbot.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.usjt.chatbot.Contexto;
import br.usjt.chatbot.R;
import br.usjt.chatbot.model.entity.Mensagem;
import br.usjt.chatbot.model.service.NetworkStatus;
import br.usjt.chatbot.pertencer.ChatPertencer;

import static br.usjt.chatbot.R.id.btn_enviar;
import static br.usjt.chatbot.R.id.btn_nao;
import static br.usjt.chatbot.R.id.btn_sim;
import static br.usjt.chatbot.R.id.listMsg;
import static br.usjt.chatbot.R.string.chat;
import static br.usjt.chatbot.R.string.fimInteracao;

public class ChatActivity extends AppCompatActivity {

    private EditText txtMsg;
    private Button btn;
    private ListView listView;
    private ArrayList<Mensagem> mensagens;
    private Mensagem mensagem;
    private ChatAdapter adapter;
    private Button btnSim, btnNao;
    private ChatPertencer pertencer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        btn = findViewById(btn_enviar);
        txtMsg = findViewById(R.id.txtMsg);
        listView = findViewById(listMsg);
        btnSim = findViewById(btn_sim);
        btnNao = findViewById(btn_nao);
        mensagens = new ArrayList<>();
        pertencer = new ChatPertencer();
        Contexto.setValue(this);

        mensagem = new Mensagem();
        mensagem.setMensagem(getString(chat));
        mensagem.setLado(false);
        mensagens.add(mensagem);
        adapter = new ChatAdapter(mensagens, this);
        listView.setAdapter(adapter);
    }

    public void obterResposta(View view) {
        final Mensagem iMensagem = pertencer.obterMensagem();
        final String pergunta = iMensagem.getMensagem();
        final int i = iMensagem.getInteracao();
        if (pergunta.equals("")) {

        } else {
            final Mensagem uMensagem = new Mensagem();
            uMensagem.setMensagem(pergunta);
            uMensagem.setLado(true);
            uMensagem.setSatisfacao(false);
            mensagens.add(uMensagem);
            adapter.notifyDataSetChanged();
            txtMsg.setText("");
            txtMsg.setEnabled(false);
            if (NetworkStatus.isConnected()) {
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    runOnUiThread(new Runnable() {
                                        final Mensagem cMensagem = pertencer.obterResposta(pergunta);

                                        @Override
                                        public void run() {
                                            mensagens.add(cMensagem);
                                            adapter.notifyDataSetChanged();

                                            Mensagem sMensagem = new Mensagem();
                                            sMensagem.setLado(false);
                                            sMensagem.setSatisfacao(true);
                                            mensagens.add(sMensagem);
                                            pertencer.gerarRelatorio(pergunta, cMensagem.getMensagem(),
                                                    iMensagem.getInteracao());
                                            adapter.notifyDataSetChanged();
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
            }
        }
    }

    public void nao(View view) {
        final Mensagem iMensagem = pertencer.obterMensagem();
        final String pergunta = iMensagem.getMensagem();
        final int i = iMensagem.getInteracao();
        if (NetworkStatus.isConnected()) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                runOnUiThread(new Runnable() {
                                    final Mensagem cMensagem = pertencer.obterRespostaInteracao
                                            (pergunta);

                                    @Override
                                    public void run() {
                                        if (i < 4) {
                                            int i = cMensagem.getInteracao();
                                            mensagens.add(cMensagem);
                                            adapter.notifyDataSetChanged();

                                            Mensagem sMensagem = new Mensagem();
                                            sMensagem.setLado(false);
                                            sMensagem.setSatisfacao(true);
                                            mensagens.add(sMensagem);
                                            pertencer.gerarRelatorio(pergunta,
                                                    cMensagem.getMensagem(),
                                                    cMensagem.getInteracao());
                                            adapter.notifyDataSetChanged();
                                        } else {
                                            Mensagem fMensagem = new Mensagem();
                                            fMensagem.setMensagem(getString(fimInteracao));
                                            fMensagem.setLado(false);
                                            fMensagem.setSatisfacao(false);
                                            mensagens.add(fMensagem);
                                            adapter.notifyDataSetChanged();
                                            pertencer.excluirMensagem();
                                            txtMsg.setEnabled(true);
                                        }
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
        }
    }

    public void encerrar(View view) {
        Intent intent = new Intent(this, FinalizarActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //adapter.remover();
        //mensagens.remove(mensagens.size()-1);
        //adapter.notifyDataSetChanged();
        txtMsg.setEnabled(true);
    }
}