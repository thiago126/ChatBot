package br.usjt.chatbot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static br.usjt.chatbot.UsuarioContract.UsuarioEntry.*;

/**
 * Created by tnf98 on 28/04/2018.
 */

public class UsuarioDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Usuarios.db";
    public static final String SQL_CREATE_USUARIO =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_NAME_NOME + "TEXT," +
                    COLUMN_NAME_EMAIL + "TEXT," +
                    COLUMN_NAME_TELEFONE + "TEXT)";

    public static final String SQL_DROP_USUARIO =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public UsuarioDbHelper (Context contexto){
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
