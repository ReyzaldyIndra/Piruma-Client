package app.piruma_java.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.piruma_java.model.*;
import app.piruma_java.R;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private Context mContext;
    private List<RoomItem> historyList;
    private TextView namaDepartemen,namaRuangan,date,time;

    public interface OnItemClickListener {
        void onItemClick(RoomItem item);
    }

    private final OnItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(final View view) {
            super(view);
            namaDepartemen = view.findViewById(R.id.txNamaDept);
            namaRuangan = view.findViewById(R.id.txNamaRuang);
            date = view.findViewById(R.id.txDate);
            time = view.findViewById(R.id.txTime);
        }
        public void bind(final RoomItem item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }


    }


    public MainAdapter(Context mContext, List<RoomItem> historyList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.historyList = historyList;
        this.listener = listener;
    }

    @Override
    public MainAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_room, parent, false);

        return new MainAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MainAdapter.MyViewHolder holder, final int position) {
        final RoomItem departemen = historyList.get(position);
        holder.bind(historyList.get(position),listener);
        namaDepartemen.setText(departemen.getName_dept());
        namaRuangan.setText(departemen.getName_room());
        date.setText(departemen.getDate());
        time.setText(departemen.getTime());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
}