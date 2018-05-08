package br.usjt.chatbot;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private static EditText txtMsg;
    private Button btn;
    private String text;
    private Mensagem[] lista;
    public final static String URL = "https://westus.api.cognitive.microsoft.com/qnamaker/v2.0/knowledgebases/f1736255-e25a-43fc-9582-01163e2134ed/generateAnswer";
    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> mensagens;
    private Mensagem mensagem;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        btn = findViewById(R.id.btn_enviar);
        txtMsg = findViewById(R.id.txtMsg);
        listView = findViewById(R.id.listMsg);
        layout = findViewById(R.id.layout_msg);
        mensagens = new ArrayList<>();
        mensagens.add("Olá, no que posso te ajudar?");
        layout.setGravity(Gravity.RIGHT);
        adapter = new ArrayAdapter<>(ChatActivity.this,
                R.layout.linha_mensagem,
                R.id.texto_mensagem,
                mensagens );
        listView.setAdapter(adapter);
    }

    public void enviarMensagem(View view) {
        String msg = txtMsg.getText().toString();
        if (msg.equals("")) {

        } else {

            final String answer = msg;
            mensagens.add(answer);
            adapter.notifyDataSetChanged();
            txtMsg.setText("");
            layout.setBackgroundResource(R.drawable.msg_user);
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
                                            mensagens.add(mensagem.getMensagem());
                                            layout.setBackgroundResource(R.drawable.msg_bot);
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
