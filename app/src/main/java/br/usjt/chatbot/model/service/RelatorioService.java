package br.usjt.chatbot.model.service;

import br.usjt.chatbot.model.dao.RelatorioDAOFirebase;
import br.usjt.chatbot.model.entity.Relatorio;

/**
 * Created by tnf98 on 14/05/2018.
 */

public class RelatorioService {

    private RelatorioDAOFirebase dao;

    public RelatorioService(){
        dao = new RelatorioDAOFirebase();
    }

    public void criarRelatorio(Relatorio relatorio){
        dao.criarRelatorio(relatorio);
    }
}
