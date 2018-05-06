package br.usjt.chatbot;

import android.provider.BaseColumns;

/**
 * Created by tnf98 on 28/04/2018.
 */

public class UsuarioContract {
    public static abstract class UsuarioEntry implements BaseColumns{
        public static final String TABLE_NAME = "usuario";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_TELEFONE = "telefone";
    }
}
