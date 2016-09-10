package virgil.com.ksymediaplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

import virgil.com.ksymediaplayer.R;
import virgil.com.ksymediaplayer.model.NetDbAdapter;

/**
 * Created by liubohua on 16/7/20.
 */
public class HistoryActivity extends Activity{
    private ListView hislist;
    private ArrayList<String> listurl;
    private Cursor cursor;
    private NetDbAdapter NetDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listurl = new ArrayList<String>();

        hislist = (ListView) findViewById(R.id.list_history);
        NetDb = new NetDbAdapter(HistoryActivity.this);
        NetDb.open();
        cursor = NetDb.getAllData();
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            listurl.add( cursor.getString(cursor.getColumnIndex(NetDbAdapter.KEY_PATH)));
        }
        while(cursor.moveToNext()){
            listurl.add( cursor.getString(cursor.getColumnIndex(NetDbAdapter.KEY_PATH)));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,listurl);
        hislist.setAdapter(adapter);
        hislist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String path = listurl.get(i);
                Intent intent = new Intent(HistoryActivity.this,VideoPlayerActivity.class);
                intent.putExtra("path",path);
                startActivity(intent);
            }
        });

    }
}
