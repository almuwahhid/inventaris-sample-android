package bnsp.com.oopbnsp1.Adapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bnsp.com.oopbnsp1.R;
import bnsp.com.oopbnsp1.helper.Database;
import bnsp.com.oopbnsp1.helper.Handler;
import bnsp.com.oopbnsp1.kelas.Berkas;
import bnsp.com.oopbnsp1.kelas.Inventaris;
import bnsp.com.oopbnsp1.kelas.Kategori;
import bnsp.com.oopbnsp1.kelas.Perkakas;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdapterUtama extends RecyclerView.Adapter<AdapterUtama.Wadah>{

    Handler handler;
    ArrayList<Inventaris> all_data;
    Context ctx;
    String nim;
    final private clickAdapter klik;

    public void setAll_data(ArrayList<Inventaris> all_data) {
        this.all_data = all_data;
    }

    public AdapterUtama(ArrayList<Inventaris> all_data, Context ctx, String nim, clickAdapter klik) {
        this.all_data = all_data;
        this.ctx = ctx;
        this.nim = nim;
        this.klik = klik;
    }

    public interface clickAdapter{
        void klikItem(int position);
    }

    @Override
    public Wadah onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_utama, parent, false);
        return new Wadah(v);
    }

    @Override
    public void onBindViewHolder(Wadah holder, final int position) {
        final Inventaris inventaris = all_data.get(position);

        Kategori berkas = new Berkas();
        Kategori perkakas = new Perkakas();

        if(inventaris.getId_kategori() == berkas.getId_kategori()){
            holder.txt1.setText(berkas.getNama_kategori());
        }else {
            holder.txt1.setText(perkakas.getNama_kategori());
        }
        holder.txt2.setText(inventaris.getNama_inv());
        Log.d("asd", "onBindViewHolder 1: "+inventaris.getNim_inv());
        Log.d("asd", "onBindViewHolder 2: "+nim);

        if(!inventaris.getNim_inv().equals(nim)){
            holder.btn.setVisibility(View.GONE);
        }

        handler = new Handler(ctx);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(handler.deleteData(inventaris.getId())){
                    Toast.makeText(ctx, "Data Sudah dihapus", Toast.LENGTH_SHORT).show();
                    klik.klikItem(position);
                }else{
                    Toast.makeText(ctx, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return all_data.size();
    }

    public class Wadah extends RecyclerView.ViewHolder {
        TextView txt1, txt2;
        Button btn;
        public Wadah(View itemView) {
            super(itemView);
            txt1 = (TextView) itemView.findViewById(R.id.adapter_ktg);
            txt2 = (TextView) itemView.findViewById(R.id.adapter_inv);
            btn = (Button) itemView.findViewById(R.id.adapter_btn);
        }
    }
}
