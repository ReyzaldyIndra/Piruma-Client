package app.piruma_java;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;
import app.piruma_java.model.RoomItem;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

    private Context mContext;
    public List<RoomItem> roomItemList;
    public TextView txNamaRuang;
    public TextView txNamaDept;
    public TextView txJumlahRuang;


    public MainAdapter(Context mContext, List<RoomItem> roomList){
        this.mContext = mContext;
        this.roomItemList = roomList;
    }


    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_room, parent, false);
        return new MainAdapter.MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MainAdapter.MainViewHolder holder, final int position) {
        final RoomItem roomItem = roomItemList.get(position);
        txNamaRuang.setText(roomItem.getName_room());
        txNamaDept.setText(roomItem.getName_dept());

    }

    @Override
    public int getItemCount() {
        return roomItemList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

       public MainViewHolder(final View view) {
            super(view);
            txNamaRuang = view.findViewById(R.id.txNamaRuang);
            txNamaDept = view.findViewById(R.id.txNamaDept);
            txJumlahRuang = view.findViewById(R.id.txDateAvail);
        }


    }
}
