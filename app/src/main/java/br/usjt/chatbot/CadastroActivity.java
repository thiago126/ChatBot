package br.usjt.chatbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

public class CadastroActivity extends AppCompatActivity {

    public EditText nome, email, telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void entrar(View view) {
        nome = findViewById(R.id.nome_editText);
        email = findViewById(R.id.email_editText);
        telefone = findViewById(R.id.telefone_editText);

        Usuario usuario = new Usuario();

        usuario.setNome(nome.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setTelefone(telefone.getText().toString());
        usuario.setDate(new Date());

        Database database = new Database();
        database.inserir(usuario);

        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }



}