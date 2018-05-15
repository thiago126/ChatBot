package br.usjt.chatbot.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tnf98 on 28/04/2018.
 */

public class UsuarioDAODbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "usuario.db";
    public static final String SQL_CREATE_USUARIO =
            "CREATE TABLE " + UsuarioDAOContract.UsuarioEntry.TABLE_NAME + "(" +
                    UsuarioDAOContract.UsuarioEntry.COLUMN_NAME_NOME + " VARCHAR(200)," +
                    UsuarioDAOContract.UsuarioEntry.COLUMN_NAME_EMAIL + " VARCHAR(200)," +
                    UsuarioDAOContract.UsuarioEntry.COLUMN_NAME_TELEFONE + " VARCHAR(200))";

    public static final String SQL_DROP_USUARIO =
            "DROP TABLE IF EXISTS " + UsuarioDAOContract.UsuarioEntry.TABLE_NAME;

    public UsuarioDAODbHelper(Context contexto){
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DROP_USUARIO);
        db.execSQL(SQL_CREATE_USUARIO);
    }
}
