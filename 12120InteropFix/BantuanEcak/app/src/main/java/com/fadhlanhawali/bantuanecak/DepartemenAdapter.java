package com.fadhlanhawali.bantuanecak;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DepartemenAdapter extends RecyclerView.Adapter<DepartemenAdapter.MyViewHolder> {

    private Context mContext;
    private List<Departemen> departemenList;
    private TextView namaDepartemen,namaFakultas,jumlahRuangan;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(final View view) {
            super(view);
            namaDepartemen = view.findViewById(R.id.txtDepartemen);
            namaFakultas = view.findViewById(R.id.txtFakultas);
            jumlahRuangan = view.findViewById(R.id.txtJumlahRuangan);
        }

    }


    public DepartemenAdapter(Context mContext, List<Departemen> departemenList ) {
        this.mContext = mContext;
        this.departemenList = departemenList;
    }

    @Override
    public DepartemenAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_list, parent, false);

        return new DepartemenAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DepartemenAdapter.MyViewHolder holder, final int position) {
        final Departemen departemen = departemenList.get(position);
        namaDepartemen.setText(departemen.getDepartemen());
        namaFakultas.setText(departemen.getFakultas());
        jumlahRuangan.setText(departemen.getJumlah());
    }

    @Override
    public int getItemCount() {
        return departemenList.size();
    }
}