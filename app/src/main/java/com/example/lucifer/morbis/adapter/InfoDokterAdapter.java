package com.example.lucifer.morbis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lucifer.morbis.R;
import com.example.lucifer.morbis.model.InfoDoctor.MainDoctor;

import java.util.ArrayList;

/**
 * Created by lucifer on 4/25/2016.
 */
public class InfoDokterAdapter extends RecyclerView.Adapter<InfoDokterAdapter.DokterViewHolder> {

    private ArrayList<MainDoctor> data;
    private Context context;
    private Context parentContext;
    private int countNumber = 1;

    public InfoDokterAdapter(Context context, ArrayList<MainDoctor> data) {
        this.context = context;
        this.data = data;
        notifyDataSetChanged();
        Log.e("Info Dokter Adapter", "called");

        Log.e("Data Dokter Adapter", String.valueOf(data));

    }

    @Override
    public DokterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowinfodokter, parent, false);

        parentContext = parent.getContext();

        return new DokterViewHolder(v);

    }

    @Override
    public void onBindViewHolder(InfoDokterAdapter.DokterViewHolder holder, int position) {
        holder.getName().setText(data.get(position).getDoctor().getCitizen().getName());
        Log.e("Nama Dokter POS", data.get(position).getDoctor().getCitizen().getName());
        holder.getSpecialization().setText(data.get(position).getDoctor().getSpecialization().getName());
        Log.e("Nama Specialization POS", data.get(position).getDoctor().getSpecialization().getName());
        holder.getNumber().setText(String.valueOf(countNumber++));

    }

    @Override
    public int getItemCount() {
        /*Log.e("Jumlah Data Dokter", String.valueOf(data.size()));
        return data.size();*/

        return data == null ? 0 : data.size();

    }

    public static class DokterViewHolder extends RecyclerView.ViewHolder{

        final TextView number;
        final TextView name;
        final TextView specialization;

        public DokterViewHolder(View itemView) {
            super(itemView);

            number = (TextView) itemView.findViewById(R.id.num_dokter);
            name = (TextView) itemView.findViewById(R.id.dokter_name);
            specialization = (TextView) itemView.findViewById(R.id.spec_dokter);

        }

        public TextView getNumber() {
            return number;
        }

        public TextView getName() {
            return name;
        }

        public TextView getSpecialization() {
            return specialization;
        }
    }
}
