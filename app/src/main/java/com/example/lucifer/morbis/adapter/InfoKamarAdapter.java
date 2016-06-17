package com.example.lucifer.morbis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lucifer.morbis.R;
import com.example.lucifer.morbis.model.InfoKamar.MainRoom;

import java.util.ArrayList;

/**
 * Created by lucifer on 4/26/2016.
 */
public class InfoKamarAdapter extends RecyclerView.Adapter<InfoKamarAdapter.RoomViewHolder> {

    private ArrayList<MainRoom> data;
    private Context context;
    private int countNumber = 1;

    public InfoKamarAdapter(Context context, ArrayList<MainRoom> data) {
        this.context = context;
        this.data = data;
        Log.e("Kamar Adapter", "called");

    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowinfokamar, parent, false);

        return new RoomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(InfoKamarAdapter.RoomViewHolder holder, int position) {
        holder.getName().setText(data.get(position).getDepartement().getName() + " " + data.get(position).getName());
        holder.getKelas().setText(data.get(position).getWardClass().getName());
        holder.getStat_kosong().setText(data.get(position).getTotal_empty());
        holder.getStat_ada().setText(data.get(position).getTotal_filled());
        holder.getNumber().setText(String.valueOf(countNumber++));

    }

    @Override
    public int getItemCount() {

        Log.e("Jumlah Data", String.valueOf(data.size()));

        return data.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder{

        final TextView number;
        final TextView name;
        final TextView kelas;
        final TextView stat_kosong;
        final TextView stat_ada;

        public RoomViewHolder(View itemView) {
            super(itemView);

            number = (TextView) itemView.findViewById(R.id.no_kamar);
            name = (TextView) itemView.findViewById(R.id.nama_kamar);
            kelas = (TextView) itemView.findViewById(R.id.nama_kelas);
            stat_kosong = (TextView) itemView.findViewById(R.id.stat_kosong);
            stat_ada = (TextView) itemView.findViewById(R.id.stat_ada);

        }

        public TextView getNumber() {
            return number;
        }

        public TextView getName() {
            return name;
        }

        public TextView getKelas() {
            return kelas;
        }

        public TextView getStat_kosong() {
            return stat_kosong;
        }

        public TextView getStat_ada() {
            return stat_ada;
        }
    }
}
