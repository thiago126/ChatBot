package br.usjt.chatbot.model.dao;

import android.provider.BaseColumns;

/**
 * Created by tnf98 on 14/05/2018.
 */

public class MensagemDAOContract {
    public static abstract class MensagemEntry implements BaseColumns {
        public static final String TABLE_NAME = "mensagem";
        public static final String COLUMN_NAME_PERGUNTA = "pergunta";
        public static final String COLUMN_NAME_INTERACAO = "interacao";
    }
}
