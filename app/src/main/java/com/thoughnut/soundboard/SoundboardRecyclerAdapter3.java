package com.thoughnut.soundboard;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SoundboardRecyclerAdapter3 extends RecyclerView.Adapter<SoundboardRecyclerAdapter3.SoundboardViewHolder>{

    private ArrayList<SoundObject> soundObjects;
    private Context mContext;
    String[] total = {""};

    public SoundboardRecyclerAdapter3(Context mContext, ArrayList<SoundObject> soundObjects){

        this.soundObjects = soundObjects;
        this.mContext = mContext;
    }

    @Override
    public SoundboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_item_glitch, null);


        return new SoundboardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SoundboardViewHolder holder, int position) {


        final SoundObject object = soundObjects.get(position);


        final Integer soundID = object.getItemID();


        holder.itemTextView.setText(object.getItemName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventHandlerClass.startMediaPlayer(v, soundID);
                notifyDataSetChanged();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                EventHandlerClass.popupM(v, object);
                return true;
            }
        });


    }


    @Override
    public int getItemCount() {
        return soundObjects.size();
    }


    public class SoundboardViewHolder extends RecyclerView.ViewHolder{
        Context context;
        TextView itemTextView;


        public SoundboardViewHolder(View itemView) {
            super(itemView);
            itemTextView = (TextView) itemView.findViewById(R.id.textViewItem);

        }
    }
}
