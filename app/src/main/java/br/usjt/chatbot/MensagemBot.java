package br.usjt.chatbot;

import java.util.ArrayList;

/**
 * Created by tnf98 on 28/04/2018.
 */

public class MensagemBot {

    private String answer;
    private ArrayList<String> question;
    private double score;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<String> getQuestion() {
        return question;
    }

    public void setQuestion(ArrayList<String> question) {
        this.question = question;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
