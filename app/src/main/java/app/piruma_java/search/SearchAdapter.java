package app.piruma_java.search;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import java.util.List;

import app.piruma_java.R;
import app.piruma_java.model.RoomAvail;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    public Context mContext;
    public List<RoomAvail> departemenList;
    public TextView namaDepartemen;
    private TextView namaFakultas;
    private TextView jumlahRuangan;


public SearchAdapter(Context mContext, List<RoomAvail> departemenList){
    this.mContext = mContext;
    this.departemenList = departemenList;

}
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_avail, viewGroup, false);

        return new SearchAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchAdapter.MyViewHolder myViewHolder, final int i) {
    final RoomAvail roomAvail = departemenList.get(i);
    namaDepartemen.setText(roomAvail.getDepartemen());
    namaFakultas.setText(roomAvail.getFakultas());
    jumlahRuangan.setText(roomAvail.getJumlah());
    }

    @Override
    public int getItemCount() {
        return departemenList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(final View view){
            super(view);
            namaDepartemen = view.findViewById(R.id.txtDepartemen);
            namaFakultas = view.findViewById(R.id.txtFakultas);
            jumlahRuangan = view.findViewById(R.id.txtJumlahRuangan);
        }
    }

}

