package br.usjt.chatbot.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.usjt.chatbot.model.entity.Mensagem;

/**
 * Created by tnf98 on 14/05/2018.
 */

public class MensagemDAODb {
    MensagemDAODbHelper dbHelper;

    public MensagemDAODb(Context contexto) {
        dbHelper = new MensagemDAODbHelper(contexto);
    }

    public void insereMensagem(Mensagem mensagem) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(MensagemDAOContract.MensagemEntry.TABLE_NAME, null, null);

        ContentValues values = new ContentValues();
        values.put(MensagemDAOContract.MensagemEntry.COLUMN_NAME_PERGUNTA, mensagem.getMensagem());
        values.put(MensagemDAOContract.MensagemEntry.COLUMN_NAME_INTERACAO, mensagem.getInteracao());

        db.insert(MensagemDAOContract.MensagemEntry.TABLE_NAME, null, values);
        db.close();
    }

    public Mensagem selecionaMensagem(){
        Mensagem mensagem = new Mensagem();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = {
                MensagemDAOContract.MensagemEntry.COLUMN_NAME_PERGUNTA,
                MensagemDAOContract.MensagemEntry.COLUMN_NAME_INTERACAO};

        Cursor c = db.query(MensagemDAOContract.MensagemEntry.TABLE_NAME, colunas, null, null, null, null,null);

        while (c.moveToNext()){
            mensagem.setMensagem(c.getString(c.getColumnIndex(MensagemDAOContract.MensagemEntry.COLUMN_NAME_PERGUNTA)));
            mensagem.setInteracao(c.getInt(c.getColumnIndex(MensagemDAOContract.MensagemEntry.COLUMN_NAME_INTERACAO))+1);
        }
        Log.d("Banco","" + mensagem.getInteracao());
        this.insereMensagem(mensagem);
        return mensagem;
    }

    public void excluirMensagem(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(MensagemDAOContract.MensagemEntry.TABLE_NAME, null, null);
        db.close();
    }
}
