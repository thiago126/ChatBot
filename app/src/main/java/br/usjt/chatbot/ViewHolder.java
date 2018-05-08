package br.usjt.chatbot;

import android.widget.TextView;

/**
 * Created by tnf98 on 08/05/2018.
 */

public class ViewHolder {

    private TextView textView;

    public ViewHolder(TextView textView){
        this.textView = textView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
