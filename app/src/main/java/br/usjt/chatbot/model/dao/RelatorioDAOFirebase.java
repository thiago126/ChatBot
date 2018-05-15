package br.usjt.chatbot.model.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.usjt.chatbot.model.entity.Relatorio;

/**
 * Created by tnf98 on 13/05/2018.
 */

public class RelatorioDAOFirebase {

    public void criarRelatorio(Relatorio relatorio) {
        try {
            DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();
            raiz.child("relatorio").push().setValue(relatorio);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
