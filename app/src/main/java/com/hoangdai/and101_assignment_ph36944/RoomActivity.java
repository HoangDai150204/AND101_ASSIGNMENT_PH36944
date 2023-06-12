package com.hoangdai.and101_assignment_ph36944;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;

import adapter.RoomAdapter;
import model.Room;

public class RoomActivity extends AppCompatActivity {
   private ArrayList<Room> listRoom;
   private ListView lvRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        lvRoom = findViewById(R.id.lvRoom);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Phòng ban");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back2);

        //adapter
        loadData(getDSPB());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menutoolbar, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Room> listFilter = new ArrayList<>();
                for (Room r:getDSPB()){
                    if (r.getNameRoom().toLowerCase().contains(newText.toLowerCase())){
                        listFilter.add(r);

                    }
                }
                loadData(listFilter);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void loadData(ArrayList<Room> list){
        RoomAdapter adapter = new RoomAdapter(RoomActivity.this, list);
        lvRoom.setAdapter(adapter);
    }

    public ArrayList<Room> getDSPB (){
        listRoom = new ArrayList<>();
        listRoom.add(new Room("Nhân sự"));
        listRoom.add(new Room("Hành chính"));
        listRoom.add(new Room("Đào tạo"));

        return listRoom;
    }
}