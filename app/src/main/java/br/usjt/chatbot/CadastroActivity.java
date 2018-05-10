package br.usjt.chatbot;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

public class CadastroActivity extends AppCompatActivity {

    public EditText nome, email, telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        nome = findViewById(R.id.nome_editText);
        email = findViewById(R.id.email_editText);
        telefone = findViewById(R.id.telefone_editText);

        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, "(##)####-#####"));
    }

    public void entrar(View view) {

        String name = nome.getText().toString();
        String mail = email.getText().toString();
        String tel = telefone.getText().toString();

        if (name.equals("") || mail.equals("") || tel.equals("")) {

        } else {

            Usuario usuario = new Usuario();

            usuario.setNome(nome.getText().toString());
            usuario.setEmail(email.getText().toString());
            usuario.setTelefone(telefone.getText().toString());


            FirebaseDb database = new FirebaseDb();
            database.inserir(usuario);
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("nome", usuario.getNome());
            intent.putExtra("email",usuario.getEmail());
            intent.putExtra("telefone", usuario.getTelefone());
            startActivity(intent);
        }
    }
}