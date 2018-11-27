package app.piruma_java.selectroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import app.piruma_java.R;
import app.piruma_java.model.SelectRoom;


public class SelectRoomAdapter extends RecyclerView.Adapter<SelectRoomAdapter.MyViewHolder> {

    private Context mContext;
    private List<SelectRoom> departemenList;
    private TextView namaRuangan;

    public interface OnItemClickListener {
        void onItemClick(SelectRoom item);
    }

    private final OnItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(final View view) {
            super(view);
            namaRuangan = view.findViewById(R.id.sel_tx_nama_ruang);
        }
        public void bind(final SelectRoom item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }


    }


    public SelectRoomAdapter(Context mContext, List<SelectRoom> departemenList, OnItemClickListener listener ) {
        this.mContext = mContext;
        this.departemenList = departemenList;
        this.listener = listener;
    }

    @Override
    public SelectRoomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select, parent, false);

        return new SelectRoomAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SelectRoomAdapter.MyViewHolder holder, final int position) {
        final SelectRoom departemen = departemenList.get(position);
        holder.bind(departemenList.get(position),listener);
        namaRuangan.setText(departemen.getRuang());
    }

    @Override
    public int getItemCount() {
        return departemenList.size();
    }
}