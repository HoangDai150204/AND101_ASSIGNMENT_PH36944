package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hoangdai.and101_assignment_ph36944.R;

import java.util.ArrayList;

import model.Room;

public class RoomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Room> listRoom;

    public RoomAdapter(Context context, ArrayList<Room> listRoom) {
        this.context = context;
        this.listRoom = listRoom;
    }

    @Override
    public int getCount() {
        return listRoom.size();
    }

    @Override
    public Object getItem(int position) {
        return listRoom.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_room, parent, false);

        TextView txtNameRoom = view.findViewById(R.id.txtnameRoom);
        txtNameRoom.setText(listRoom.get(position).getNameRoom());
        return view;
    }
}
