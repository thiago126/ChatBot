package br.usjt.chatbot;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

/**
 * Created by tnf98 on 18/04/2018.
 */

public class FirebaseDb {

    public Usuario inserir(Usuario usuario) {

        try {
            DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();
            raiz.child("usuarios").push().setValue(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void inserir(Relatorio relatorio) {
        try {
            DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();
            raiz.child("relatorio").push().setValue(relatorio);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
