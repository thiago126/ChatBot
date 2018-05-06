package br.usjt.chatbot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

public class ChatActivity extends AppCompatActivity {

    private static EditText txtMsg;
    private TextView textView;
    private Button btn;
    private String text;
    private MensagemBot[] mBots;
    public final static String URL = "https://westus.api.cognitive.microsoft.com/qnamaker/v2.0/knowledgebases/f1736255-e25a-43fc-9582-01163e2134ed/generateAnswer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        btn = findViewById(R.id.btn_enviar);
        txtMsg = findViewById(R.id.txtMsg);
        textView = findViewById(R.id.msg_01);
    }

    public void enviarMensagem(View view) {
        final String mensagem = txtMsg.getText().toString();
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
                                        textView.setText(bot.getAnswer());
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