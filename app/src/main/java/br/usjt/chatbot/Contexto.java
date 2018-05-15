package br.usjt.chatbot;

import android.content.Context;

/**
 * Created by tnf98 on 13/05/2018.
 */

public class Contexto {

    private static Context contexto;

    public static Context getValue(){
        return contexto;
    }

    public static void setValue(Context c){
        contexto = c;
    }
}
