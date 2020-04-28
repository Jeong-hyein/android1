package com.example.mymemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newMemo = (Button) findViewById(R.id.button2);
        newMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //화면전환시 데이터 송수신
                Intent intent = new Intent(getApplicationContext(), NewActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<HashMap<String, String>> list =
                new MemoDAO().selectAll(getApplicationContext());

        ListViewAdater adapter = new ListViewAdater();
        adapter.setList(list);
        ListView lvMemo = findViewById(R.id.lvMemo);
        lvMemo.setAdapter(adapter);
    }
}
