package br.usjt.chatbot.pertencer;

/**
 * Created by tnf98 on 13/05/2018.
 */

public interface Pertencer {

    void onCreate();
    void onStart();
    void onRestart();
    void onPause();
    void onStop();
    void onResume();
    void onDestroy();
}
