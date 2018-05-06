package br.usjt.chatbot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static br.usjt.chatbot.UsuarioContract.UsuarioEntry.*;

/**
 * Created by tnf98 on 28/04/2018.
 */

public class UsuarioDb {
    UsuarioDbHelper dbHelper;

    public UsuarioDb (Context contexto){
        dbHelper = new UsuarioDbHelper(contexto);
    }

    public void insereUsuario(Usuario usuario){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME_NOME, usuario.getNome());
        values.put(COLUMN_NAME_EMAIL, usuario.getEmail());
        values.put(COLUMN_NAME_TELEFONE, usuario.getTelefone());

        db.insert(TABLE_NAME, null, values);
    }

    public Usuario selecionaUsuario(){
        Usuario usuario = new Usuario();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = {
                COLUMN_NAME_NOME,
                COLUMN_NAME_EMAIL,
                COLUMN_NAME_TELEFONE};

        Cursor c = db.query(TABLE_NAME, colunas, null, null, null, null,null);

        while (c.moveToNext()){
            usuario.setNome(c.getString(c.getColumnIndex(COLUMN_NAME_NOME)));
            usuario.setEmail(c.getString(c.getColumnIndex(COLUMN_NAME_EMAIL)));
            usuario.setTelefone(c.getString(c.getColumnIndex(COLUMN_NAME_TELEFONE)));
        }
        return usuario;
    }
}
