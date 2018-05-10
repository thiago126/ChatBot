package br.usjt.chatbot;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tnf98 on 08/05/2018.
 */

public class ChatAdapter extends BaseAdapter {

    private ArrayList<Mensagem> list;
    private Activity activity;
    private TextView textView;

    public ChatAdapter(ArrayList<Mensagem> list, Activity activity){
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)
                    activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(list.get(position).isLado() == false){
                view = inflater.inflate(R.layout.linha_mensagem, parent, false);
            }else {
                view = inflater.inflate(R.layout.linha_mensagem_user, parent, false);
            }
            textView = view.findViewById(R.id.texto_mensagem);
            ChatViewHolder chatViewHolder = new ChatViewHolder(textView);
            view.setTag(chatViewHolder);
        }
        ChatViewHolder chatViewHolder = (ChatViewHolder)view.getTag();
        chatViewHolder.getTextView().setText(list.get(position).getMensagem());
        return view;
    }
}
