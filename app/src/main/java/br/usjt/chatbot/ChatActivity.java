package br.usjt.chatbot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private static EditText txtMsg;
    private Button btn;
    private String text;
    private Mensagem[] lista;
    public final static String URL = "https://westus.api.cognitive.microsoft.com/qnamaker/v2.0/knowledgebases/f1736255-e25a-43fc-9582-01163e2134ed/generateAnswer";
    private ListView listView;
    private ArrayList<Mensagem> mensagens;
    private Mensagem mensagem;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        btn = findViewById(R.id.btn_enviar);
        txtMsg = findViewById(R.id.txtMsg);
        listView = findViewById(R.id.listMsg);
        mensagens = new ArrayList<>();
        Mensagem mensagem = new Mensagem();
        mensagem.setMensagem("Olá, como posso te ajudar?");
        mensagem.setLado(false);
        mensagens.add(mensagem);
        adapter = new ChatAdapter(mensagens, this);
        listView.setAdapter(adapter);
    }

    public void enviarMensagem(View view) {
        String msg = txtMsg.getText().toString();
        if (msg.equals("")) {

        } else {

            final String answer = msg;
            Mensagem message = new Mensagem();
            message.setMensagem(answer);
            message.setLado(true);
            mensagens.add(message);
            adapter.notifyDataSetChanged();
            txtMsg.setText("");
            if (ChatDataNetwork.isConnected(this)) {

                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    lista = ChatDataNetwork.buscaMensagem(URL, answer);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mensagem = lista[0];
                                            mensagem.setMensagem(lista[0].getMensagem());
                                            mensagem.setLado(false);
                                            mensagens.add(mensagem);
                                            Intent intent = getIntent();
                                            Usuario usuario = new Usuario();
                                            usuario.setNome(intent.getStringExtra("nome"));
                                            usuario.setEmail(intent.getStringExtra("email"));
                                            usuario.setTelefone(intent.getStringExtra("telefone"));

                                            Relatorio relatorio = new Relatorio();
                                            relatorio.setData(new Date());
                                            relatorio.setResposta(lista[0].getMensagem());
                                            relatorio.setPergunta(answer);
                                            relatorio.setUsuario(usuario);
                                            FirebaseDb fdb = new FirebaseDb();
                                            fdb.inserir(relatorio);
                                            adapter.notifyDataSetChanged();
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
            }
        }
    }
}
