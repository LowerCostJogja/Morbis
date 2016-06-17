package com.example.lucifer.morbis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucifer.morbis.R;
import com.example.lucifer.morbis.model.RekmedItemDummy;

import java.util.ArrayList;

/**
 * Created by lucifer on 3/28/2016.
 */
public class RekmedAdapter extends RecyclerView.Adapter<RekmedAdapter.RekmedViewHolder> {

    private ArrayList<RekmedItemDummy> data;
    private Context context;

    public RekmedAdapter(Context context, ArrayList<RekmedItemDummy> data) {
        this.context = context;
        this.data = data;
        Log.e("Rekmed Adapter", "called");

    }

    @Override
    public RekmedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rekmed, parent, false);

        return new RekmedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RekmedAdapter.RekmedViewHolder holder, int position) {
        holder.getDate().setText(data.get(position).getDate());
        holder.getRiwayat().setText(data.get(position).getRiwayat());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class RekmedViewHolder extends RecyclerView.ViewHolder{

        final TextView date;
        final TextView riwayat;

        public RekmedViewHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.tanggal);
            riwayat = (TextView) itemView.findViewById(R.id.keterangan);

        }

        public TextView getDate() {
            return date;
        }

        public TextView getRiwayat() {
            return riwayat;
        }
    }
}
