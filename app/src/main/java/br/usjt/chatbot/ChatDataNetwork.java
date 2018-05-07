package br.usjt.chatbot;

import android.content.Context;
import android.net.ConnectivityManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tnf98 on 28/04/2018.
 */

public class ChatDataNetwork {


    public static MensagemBot[] buscaMensagem(String url, String pergunta) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        ArrayList<MensagemBot> mensagemBots = new ArrayList<>();
        MediaType mediaType = MediaType.parse("application/json; charset=UTF-8");

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("question", pergunta);
        jsonParam.put("top", 3);

        Request.Builder builder = new Request.Builder();
        builder.url(url);

        Charset charset = Charset.forName(StandardCharsets.UTF_8.name());

        //RequestBody body = RequestBody.create(mediaType, jsonParam.toString());
        RequestBody body = new FormBody.Builder(charset).add("question",jsonParam.toString()).build();

        builder.post(body);

        Request request = builder
                .header("Content-Type", "application/json; charset=UTF-8")
                .header
                ("Ocp-Apim-Subscription-Key", "93599230040f4f6c823616d44ad5ef67")
                .build();

        Response response = client.newCall(request).execute();

        String resultado = response.body().string();
        try {
            JSONObject object = new JSONObject(resultado);

            JSONArray answers = object.getJSONArray("answers");

            int i = 0;
            JSONObject answer = answers.getJSONObject(i);

            MensagemBot mBot = new MensagemBot();
            mBot.setAnswer(answer.getString("answer"));
            mensagemBots.add(mBot);


        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        return mensagemBots.toArray(new MensagemBot[0]);
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
