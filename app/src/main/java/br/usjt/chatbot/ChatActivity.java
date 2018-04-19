package br.usjt.chatbot;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import ai.api.http.HttpClient;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {
    private EditText txtMsg;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        btn = findViewById(R.id.btn_enviar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrieveFeedTask task = new RetrieveFeedTask();
                task.execute();
            }
        });
    }


    // Create GetText Metod
    public void GetText() throws UnsupportedEncodingException {

        String text = "";
        BufferedReader reader = null;

        // Send data
        try {

            // Defined URL  where to send data
            URL url = new URL("https://westus.api.cognitive.microsoft.com/qnamaker/v2.0/knowledgebases/f1736255-e25a-43fc-9582-01163e2134ed/generateAnswer");
            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Ocp-Apim-Subscription-Key", "93599230040f4f6c823616d44ad5ef67");
            conn.setRequestProperty("Content-Type", "application/json");

            //Create JSONObject here
            txtMsg = findViewById(R.id.txtMsg);
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("question", txtMsg.getText().toString());


            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(jsonParam.toString());
            wr.flush();
            Log.d("karma", "json is " + jsonParam);

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;


            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }
            text = sb.toString();

            Log.d("karma ", "response is " + text);
            TextView txt = findViewById(R.id.msg1);
            txt.setText(text);
        } catch (Exception ex) {
            Log.d("karma", "exception at last " + ex);
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Log.d("karma", "called");
                GetText();
                Log.d("karma", "after called");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.d("karma", "Exception occurred " + e);
            }

            return null;
        }
    }
}
