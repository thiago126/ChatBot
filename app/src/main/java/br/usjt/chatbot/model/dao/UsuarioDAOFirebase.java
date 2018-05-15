package br.usjt.chatbot.model.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.usjt.chatbot.model.entity.Relatorio;
import br.usjt.chatbot.model.entity.Usuario;

/**
 * Created by tnf98 on 18/04/2018.
 */

public class UsuarioDAOFirebase {

    public Usuario inserir(Usuario usuario) {

        try {
            DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();
            raiz.child("usuarios").push().setValue(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

}
