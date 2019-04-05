package com.thoughnut.soundboard;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class SoundboardRecyclerAdapter2 extends RecyclerView.Adapter<SoundboardRecyclerAdapter2.SoundboardViewHolder>{

    ArrayList<SoundObject2> soundObjects;
    private Context mContext;
    String[] total = {""};

    public SoundboardRecyclerAdapter2(Context mContext, ArrayList<SoundObject2> soundObjects){

        this.soundObjects = soundObjects;
        this.mContext = mContext;
    }

    @Override
    public SoundboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_item, null);


        return new SoundboardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SoundboardViewHolder holder, int position) {


        final SoundObject2 object = soundObjects.get(position);


        final Uri soundID = object.getItemID();


        holder.itemTextView.setText(object.getItemName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                String p = total[0];
                String glitchstr = "831317";

                if(pos ==3) {
                    total[0] = p + "3";
                }
                if(pos==8){
                    total[0] = p + "8";
                }
                if(pos==13){
                    total[0] = p + "13";
                }
                if(pos==17){
                    total[0] = p + "17";
                }
                if (total[0].contains(glitchstr)){
                    total[0] = "";
                    //start glitchsequence
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Glitch.class);
                    context.startActivity(intent);


                }
                EventHandlerClass.startMediaPlayer2(v, soundID);
                notifyDataSetChanged();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EventHandlerClass.popupM2(v, object);
                return true;
            }
        });


    }


    @Override
    public int getItemCount() {
        return soundObjects.size();
    }


    public class SoundboardViewHolder extends RecyclerView.ViewHolder{


        TextView itemTextView;

        public SoundboardViewHolder(View itemView) {
            super(itemView);


            itemTextView = itemView.findViewById(R.id.textViewItem);
        }
    }
}
