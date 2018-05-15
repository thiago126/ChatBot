package br.usjt.chatbot.model.dao;

import android.content.Context;
import android.net.ConnectivityManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import br.usjt.chatbot.model.entity.Mensagem;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tnf98 on 28/04/2018.
 */

public class MensagemDAORest {

    public Mensagem obterResposta(String pergunta) throws IOException, JSONException {
        String url = "https://westus.api.cognitive.microsoft.com/qnamaker/v2.0/knowledgebases/f1736255-e25a-43fc-9582-01163e2134ed/generateAnswer";

        OkHttpClient client = new OkHttpClient();
        ArrayList<Mensagem> mensagens = new ArrayList<>();
        MediaType mediaType = MediaType.parse("application/json; charset=UTF-8");

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("question", pergunta);

        Request.Builder builder = new Request.Builder();
        builder.url(url);

        RequestBody body = RequestBody.create(mediaType, jsonParam.toString());

        builder.post(body);

        Request request = builder
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("Ocp-Apim-Subscription-Key", "93599230040f4f6c823616d44ad5ef67")
                .build();

        Response response = client.newCall(request).execute();
        String resultado = response.body().string();

        Mensagem mensagem;
        try {
            JSONObject object = new JSONObject(resultado);

            JSONArray answers = object.getJSONArray("answers");

            int i = 0;
            JSONObject answer = answers.getJSONObject(i);

            mensagem = new Mensagem();
            mensagem.setMensagem(answer.getString("answer"));
            mensagem.setLado(false);
            mensagens.add(mensagem);


        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        return mensagem;
    }
}
