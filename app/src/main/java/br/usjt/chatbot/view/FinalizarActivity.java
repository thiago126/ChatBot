package br.usjt.chatbot.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.usjt.chatbot.R;

public class FinalizarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar);
    }

    public void finalizar(View view){
        finishAffinity();
    }
}
