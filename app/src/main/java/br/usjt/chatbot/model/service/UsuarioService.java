package br.usjt.chatbot.model.service;

import android.content.Context;

import br.usjt.chatbot.Contexto;
import br.usjt.chatbot.model.dao.UsuarioDAODb;
import br.usjt.chatbot.model.dao.UsuarioDAOFirebase;
import br.usjt.chatbot.model.entity.Usuario;

/**
 * Created by tnf98 on 13/05/2018.
 */

public class UsuarioService {

    private UsuarioDAOFirebase daoFirebase;
    private UsuarioDAODb daoDb;

    public UsuarioService(){
        daoFirebase = new UsuarioDAOFirebase();
        daoDb = new UsuarioDAODb(Contexto.getValue());
    }

    public Usuario inserir(Usuario usuario){
        daoDb.insereUsuario(usuario);
        return daoFirebase.inserir(usuario);
    }

    public Usuario selecionaUsuario(){
        return daoDb.selecionaUsuario();
    }

}
