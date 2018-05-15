package br.usjt.chatbot.pertencer;

import android.widget.EditText;

import br.usjt.chatbot.model.entity.Usuario;
import br.usjt.chatbot.model.service.UsuarioService;

/**
 * Created by tnf98 on 13/05/2018.
 */

public class CadastroPertencer implements Pertencer {

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public Usuario entrar(EditText nome, EditText email, EditText telefone){
        Usuario usuario = new Usuario();
        usuario.setNome(nome.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setTelefone(telefone.getText().toString());

        UsuarioService service = new UsuarioService();
        service.inserir(usuario);
        return usuario;
    }
}
