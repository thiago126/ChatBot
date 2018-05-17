package br.usjt.chatbot.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private ChatPertencer pertencer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        btn = findViewById(btn_enviar);
        txtMsg = findViewById(R.id.txtMsg);
        listView = findViewById(listMsg);
        mensagens = new ArrayList<>();
        pertencer = new ChatPertencer();
        Contexto.setValue(this);

        mensagem = new Mensagem();
        mensagem.setMensagem(getString(chat));
        mensagem.setLado(false);
        mensagens.add(mensagem);
        adapter = new ChatAdapter(mensagens, this);
        listView.setAdapter(adapter);
        pertencer.excluirMensagem();
    }

    public void obterResposta(View view) {
        final String pergunta = txtMsg.getText().toString();
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
            btn.setEnabled(false);
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
        final int i = iMensagem.getInteracao();
        final Button btnSim = findViewById(btn_sim);
        final Button btnNao = findViewById(btn_nao);
        btnSim.setEnabled(false);
        btnNao.setEnabled(false);
        Log.d("BANCOOOOOOOOOOOOOOOOOOO", "" + i);
        if (NetworkStatus.isConnected()) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (i < 3) {
                                            txtMsg.setEnabled(true);
                                            btn.setEnabled(true);
                                        } else {
                                            Mensagem fMensagem = new Mensagem();
                                            fMensagem.setMensagem(getString(fimInteracao));
                                            fMensagem.setLado(false);
                                            fMensagem.setSatisfacao(false);
                                            mensagens.add(fMensagem);
                                            adapter.notifyDataSetChanged();
                                            pertencer.excluirMensagem();
                                            txtMsg.setEnabled(true);
                                            btn.setEnabled(true);
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
        txtMsg.setEnabled(true);
        btn.setEnabled(true);
    }
}