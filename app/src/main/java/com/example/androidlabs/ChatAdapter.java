package com.example.androidlabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private List<MsgLayout> msgModels;
    private Context context;
    private LayoutInflater inflater;

    public ChatAdapter(List<MsgLayout> msgModels, Context context) {
        this.msgModels = msgModels;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return msgModels.size();
    }

    @Override
    public Object getItem(int position) {
        return msgModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view = null;

        if (view == null){
            if (msgModels.get(position).isSend()){
                view = inflater.inflate(R.layout.activity_main_send, null);

            }else {
                view = inflater.inflate(R.layout.activity_main_recieve, null);
            }

        }
        TextView  messageText = (TextView)view.findViewById(R.id.commitTxt);
        messageText.setText(msgModels.get(position).message);
        return view;
    }
}