package com.hasibezafer.gykkarabuk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChampActivity extends AppCompatActivity {

    final List<User> users = new ArrayList<User>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champ);

        users.add(new User("Gülşah Cesur",true,"1000ml"));
        users.add(new User("Mine Başol",true,"700ml"));
        users.add(new User("Ayşenur Zengin",true,"600ml"));
        users.add(new User("Mehmet Aca",false,"500ml"));
        users.add(new User("Hasibe Zafer",true,"400ml"));
        users.add(new User("Fatma Kürcan",true,"400ml"));
        users.add(new User("Yasemin Sekmen",true,"350ml"));
        users.add(new User("Ali Osman",false,"120ml"));
        users.add(new User("Sena Kılıç",true,"100ml"));
        users.add(new User("Zafer Albayrak",false,"5ml"));
        users.add(new User("Evin Keleş",true,"5ml"));


        final ListView listView = (ListView) findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this,users);
        listView.setAdapter(adapter);


    }
}
