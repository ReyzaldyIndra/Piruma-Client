package app.piruma_java.selectroom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.piruma_java.R;
import app.piruma_java.model.SelectRoom;

public class SelectRoomAdapter extends RecyclerView.Adapter<SelectRoomAdapter.SelectViewHolder>{

    private Context mContext;
    private List<SelectRoom> selectRooms;
    private TextView txNamaDept;
    private TextView txNamaFak;
    private TextView txJumlah;
    private TextView txNamaRuang;
    private TextView txFasilitas;
    private TextView txJadwal;
    private TextView txKapasitas;

    public SelectRoomAdapter(Context mContext, List<SelectRoom> roomDeptList){
        this.mContext = mContext;
        this.selectRooms = roomDeptList;

    }


    @Override
    public SelectRoomAdapter.SelectViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_select, viewGroup, false);
        return new SelectRoomAdapter.SelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SelectRoomAdapter.SelectViewHolder holder, final int position) {
    final SelectRoom selectRoom = selectRooms.get(position);
    txNamaDept.setText(selectRoom.getDept());
    txNamaFak.setText(selectRoom.getFak());
    txJumlah.setText(selectRoom.getJml());
    txNamaRuang.setText(selectRoom.getRuang());
    txFasilitas.setText(selectRoom.getFasilitas());
    txJadwal.setText(selectRoom.getJadwal());
    txKapasitas.setText(selectRoom.getKapasitas());
    }

    @Override
    public int getItemCount() {
        return selectRooms.size();
    }


    public class SelectViewHolder extends RecyclerView.ViewHolder{
        public SelectViewHolder(final View itemView) {
            super(itemView);
            txNamaDept = itemView.findViewById(R.id.sel_tx_nama_dept);
            txNamaFak = itemView.findViewById(R.id.sel_tx_nama_fak);
            txJumlah = itemView.findViewById(R.id.sel_tx_jumlah);
            txNamaRuang = itemView.findViewById(R.id.sel_tx_nama_ruang);
            txFasilitas = itemView.findViewById(R.id.sel_tx_fasilitas);
            txJadwal = itemView.findViewById(R.id.sel_tx_jadwal);
            txKapasitas = itemView.findViewById(R.id.sel_tx_kapasitas);
        }
    }
}
