package br.usjt.chatbot.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.usjt.chatbot.model.entity.Usuario;

/**
 * Created by tnf98 on 28/04/2018.
 */

public class UsuarioDAODb {
    UsuarioDAODbHelper dbHelper;

    public UsuarioDAODb(Context contexto){
        dbHelper = new UsuarioDAODbHelper(contexto);
    }

    public void insereUsuario(Usuario usuario){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(UsuarioDAOContract.UsuarioEntry.TABLE_NAME, null, null);

        ContentValues values = new ContentValues();

        values.put(UsuarioDAOContract.UsuarioEntry.COLUMN_NAME_NOME, usuario.getNome());
        values.put(UsuarioDAOContract.UsuarioEntry.COLUMN_NAME_EMAIL, usuario.getEmail());
        values.put(UsuarioDAOContract.UsuarioEntry.COLUMN_NAME_TELEFONE, usuario.getTelefone());

        db.insert(UsuarioDAOContract.UsuarioEntry.TABLE_NAME, null, values);
        db.close();
    }

    public Usuario selecionaUsuario(){
        Usuario usuario = new Usuario();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = {
                UsuarioDAOContract.UsuarioEntry.COLUMN_NAME_NOME,
                UsuarioDAOContract.UsuarioEntry.COLUMN_NAME_EMAIL,
                UsuarioDAOContract.UsuarioEntry.COLUMN_NAME_TELEFONE};

        Cursor c = db.query(UsuarioDAOContract.UsuarioEntry.TABLE_NAME, colunas, null, null, null, null,null);

        while (c.moveToNext()){
            usuario.setNome(c.getString(c.getColumnIndex(UsuarioDAOContract.UsuarioEntry.COLUMN_NAME_NOME)));
            usuario.setEmail(c.getString(c.getColumnIndex(UsuarioDAOContract.UsuarioEntry.COLUMN_NAME_EMAIL)));
            usuario.setTelefone(c.getString(c.getColumnIndex(UsuarioDAOContract.UsuarioEntry.COLUMN_NAME_TELEFONE)));
        }
        return usuario;
    }
}
