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

public class SpinnerPBAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Room> listPB;

    public SpinnerPBAdapter(Context context, ArrayList<Room> listPB) {
        this.context = context;
        this.listPB = listPB;
    }

    @Override
    public int getCount() {
        return listPB.size();
    }

    @Override
    public Object getItem(int position) {
        return listPB.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater iflater = ((Activity)context).getLayoutInflater();
        View view = iflater.inflate(R.layout.item_room, parent, false);

        TextView txtTenPB = view.findViewById(R.id.txtnameRoom);
        txtTenPB.setText(listPB.get(position).getNameRoom());
        return view;
    }
}
