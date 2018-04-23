package br.usjt.chatbot;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by tnf98 on 18/04/2018.
 */

public class Database {

    public Usuario inserir(Usuario usuario){
        DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();
        raiz.child("usuarios").push().setValue(usuario);
        return usuario;
    }
}
