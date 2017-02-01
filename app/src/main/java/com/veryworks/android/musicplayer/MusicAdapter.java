package com.veryworks.android.musicplayer;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pc on 2/1/2017.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.Holder> {
    ArrayList<Music> datas;
    Context context;

    public MusicAdapter(ArrayList<Music> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Music music = datas.get(position);
        holder.txtTitle.setText(music.title);
        holder.txtArtist.setText(music.artist);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView txtTitle, txtArtist;
        ImageView image;

        public Holder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtArtist = (TextView) itemView.findViewById(R.id.txtArtist);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
