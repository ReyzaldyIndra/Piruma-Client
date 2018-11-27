package app.piruma_java.selectroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.piruma_java.R;
import app.piruma_java.model.SelectRoom2;

public class SelectRoomAdapter2 extends RecyclerView.Adapter<SelectRoomAdapter2.ViewHolder> {
    private Context mContext;
    private List<SelectRoom2> list;
    private TextView jadwal_start, jadwal_end,jadwal_keterangan;


    public SelectRoomAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_2, parent, false);

        return new SelectRoomAdapter2.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final SelectRoom2 jadwal = list.get(position);
        jadwal_start.setText(jadwal.getJadwal_start());
        jadwal_end.setText(jadwal.getJadwal_end());
        jadwal_keterangan.setText(jadwal.getJadwal_keterangan());
    }


    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(final View itemView) {
            super(itemView);
            jadwal_start = itemView.findViewById(R.id.tx_jadwal_start);
            jadwal_end = itemView.findViewById(R.id.tx_jadwal_end);
            jadwal_keterangan = itemView.findViewById(R.id.tx_jadwal_keterangan);

        }
    }
}
