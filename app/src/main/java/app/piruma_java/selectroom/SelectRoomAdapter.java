package app.piruma_java.selectroom;

import android.content.Context;
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

public class SelectRoomAdapter extends RecyclerView.Adapter<SelectRoomAdapter.SelectViewHolder> {

    private Context context;
    private List<SelectRoom> selectRooms;
    private TextView txNamaRuang;
    private itemClickedListener itemClickedListener;


    public SelectRoomAdapter(Context context, List<SelectRoom> roomDeptList) {
        this.context = context;
        this.selectRooms = roomDeptList;

    }
    public interface itemClickedListener{
        void onItemClickedListener(String fasilitas, String Jadwal);
    }

    public void setOnItemClickedListener(itemClickedListener i){
        itemClickedListener = i;
    }

    public class SelectViewHolder extends RecyclerView.ViewHolder{
        public SelectViewHolder(final View itemView) {
            super(itemView);
            txNamaRuang = itemView.findViewById(R.id.sel_tx_nama_ruang);
        }

    }

    @Override
    public SelectRoomAdapter.SelectViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_select, viewGroup, false);

        return new SelectRoomAdapter.SelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SelectRoomAdapter.SelectViewHolder holder, final int position) {
    final SelectRoom selectRoom = selectRooms.get(position);
//    txNamaDept.setText(selectRoom.getDept());
//    txNamaFak.setText(selectRoom.getFak());
//    txJumlah.setText(selectRoom.getJml());

    txNamaRuang.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(context, selectRoom.getId_ruangan(), Toast.LENGTH_SHORT).show();
//            txKapasitas.setText(selectRoom.getKapasitas());
//            txFasilitas.setText(selectRoom.getFasilitas());
//            txJadwal.setText(selectRoom.getJadwal());
        }
    });

    if(itemClickedListener!=null){
        final String fasilitas = selectRoom.getKapasitas();
        final String jadwal = selectRoom.getJadwal();
        itemClickedListener.onItemClickedListener(fasilitas, jadwal);
    }

    }

    @Override
    public int getItemCount() {
        return selectRooms.size();
    }


}
