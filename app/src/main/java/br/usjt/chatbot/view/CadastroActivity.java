package br.usjt.chatbot.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.usjt.chatbot.Contexto;
import br.usjt.chatbot.R;
import br.usjt.chatbot.model.dao.UsuarioDAOFirebase;
import br.usjt.chatbot.model.entity.Usuario;
import br.usjt.chatbot.model.service.UsuarioService;
import br.usjt.chatbot.pertencer.CadastroPertencer;

public class CadastroActivity extends AppCompatActivity {

    public EditText nome, email, telefone;
    private UsuarioService service;
    private CadastroPertencer pertencer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        nome = findViewById(R.id.nome_editText);
        email = findViewById(R.id.email_editText);
        telefone = findViewById(R.id.telefone_editText);
        pertencer = new CadastroPertencer();
        Contexto.setValue(this);
        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_FONE));
    }

    public void entrar(View view) {

        String name = nome.getText().toString();
        String mail = email.getText().toString();
        String tel = telefone.getText().toString();

        if (name.equals("") || mail.equals("") || tel.equals("")) {

        } else {
            pertencer.entrar(nome, email,telefone);
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        }
    }
}