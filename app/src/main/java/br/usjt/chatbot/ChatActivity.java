package br.usjt.chatbot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private static EditText txtMsg;
    private TextView textView;
    private Button btn;
    private String text;
    private MensagemBot[] mBots;
    public final static String URL = "https://westus.api.cognitive.microsoft.com/qnamaker/v2.0/knowledgebases/f1736255-e25a-43fc-9582-01163e2134ed/generateAnswer";
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> mensagens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        btn = findViewById(R.id.btn_enviar);
        txtMsg = findViewById(R.id.txtMsg);
        textView = findViewById(R.id.textViewMensagem);
        listView = findViewById(R.id.listMsg);

        mensagens = new ArrayList<>();
        mensagens.add("Olá, no que posso te ajudar?");
        adapter = new ArrayAdapter<>(
                ChatActivity.this,
                R.layout.activity_chat,
                R.id.textViewMensagem,
                mensagens
        );
        listView.setAdapter(adapter);
    }

    public void enviarMensagem(View view) {
        String msg = txtMsg.getText().toString();
        if (msg.equals("")) {

        } else {

            final String mensagem = msg;
            mensagens.add(mensagem);
            adapter.notifyDataSetChanged();
            txtMsg.setText("");
            if (ChatDataNetwork.isConnected(this)) {

                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    mBots = ChatDataNetwork.buscaMensagem(URL, mensagem);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            MensagemBot bot = mBots[0];
                                            mensagens.add(bot.getAnswer());
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
