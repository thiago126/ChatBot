package br.usjt.chatbot.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tnf98 on 14/05/2018.
 */

public class MensagemDAODbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "mensagem.db";
    public static final String SQL_CREATE_MENSAGEM =
            "CREATE TABLE " + MensagemDAOContract.MensagemEntry.TABLE_NAME + "(" +
                    MensagemDAOContract.MensagemEntry.COLUMN_NAME_PERGUNTA + " VARCHAR(200)," +
                    MensagemDAOContract.MensagemEntry.COLUMN_NAME_INTERACAO + " INT(10))";

    public static final String SQL_DROP_MENSAGEM =
            "DROP TABLE IF EXISTS " + MensagemDAOContract.MensagemEntry.TABLE_NAME;

    public MensagemDAODbHelper(Context contexto){
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_MENSAGEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DROP_MENSAGEM);
        db.execSQL(SQL_CREATE_MENSAGEM);
    }
}
