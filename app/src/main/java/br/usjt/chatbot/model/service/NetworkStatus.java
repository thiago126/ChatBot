package br.usjt.chatbot.model.service;

import android.content.Context;
import android.net.ConnectivityManager;

import br.usjt.chatbot.Contexto;

/**
 * Created by tnf98 on 13/05/2018.
 */

public class NetworkStatus {
    public static boolean isConnected(){
        Context context = Contexto.getValue();
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
