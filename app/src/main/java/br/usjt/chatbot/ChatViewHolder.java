package br.usjt.chatbot;

import android.widget.TextView;

/**
 * Created by tnf98 on 08/05/2018.
 */

public class ChatViewHolder {

    private TextView textView;

    public ChatViewHolder(TextView textView){
        this.textView = textView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
