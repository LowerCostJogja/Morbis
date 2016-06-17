package com.example.lucifer.morbis.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lucifer.morbis.DetailHospital;
import com.example.lucifer.morbis.R;
import com.example.lucifer.morbis.model.HospitalModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lucifer on 3/28/2016.
 */
public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> {

    private ArrayList<HospitalModel> data;
    private Context context;
    private Context parentContext;

    public HospitalAdapter(Context context, ArrayList<HospitalModel> data) {
        this.context = context;
        this.data = data;
        notifyDataSetChanged();
        Log.e("Hospital Adapter", "called");

        Log.e("Data Adapter", String.valueOf(data));

    }

    @Override
    public HospitalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hospital, parent, false);

        parentContext = parent.getContext();

        return new HospitalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HospitalAdapter.HospitalViewHolder holder, final int position) {
//        Picasso.with(context).load(data.get(position).getImage()).error(R.drawable.muhammadiyah).into(holder.getImg());
//        holder.getImg().setImageResource(data.get(position).getImage());
        holder.getName().setText(data.get(position).getName());

        Log.e("Nama", data.get(position).getName());

        holder.getAddress().setText(data.get(position).getAddress());

        holder.getLl_hospital().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentContext, DetailHospital.class);
                intent.putExtra("hospital_name", data.get(position).getName());
                intent.putExtra("pk", data.get(position).getPk());
                intent.putExtra("hospital_address", data.get(position).getAddress());
                parentContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static class HospitalViewHolder extends RecyclerView.ViewHolder {

        final ImageView img;
        final TextView name;
        final TextView address;
        final RelativeLayout ll_hospital;

        public HospitalViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.img_hospital);
            name = (TextView) itemView.findViewById(R.id.hospital_name);
            address = (TextView) itemView.findViewById(R.id.hospital_address);
            ll_hospital = (RelativeLayout) itemView.findViewById(R.id.ll_hospital);
        }

        public ImageView getImg() {
            return img;
        }

        public TextView getName() {
            return name;
        }

        public TextView getAddress() {
            return address;
        }

        public RelativeLayout getLl_hospital() {
            return ll_hospital;
        }
    }
}
