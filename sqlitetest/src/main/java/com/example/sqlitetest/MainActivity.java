package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

//    SQLiteDatabase sqliteDB;
//    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load_values();              // 전체 데이터 조회

        //save 클릭 이벤트(클릭했을때 save_values()를 호출)
        Button btnsave = findViewById(R.id.buttonSave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {save_values();
            }
        }); //save_values()는 method로 만들어둠

        ListView listview = findViewById(R.id.lvCustom);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                Map<String, String> item = (Map)parent.getItemAtPosition(position) ;

                String name = item.get("name") ;
                String phone = item.get("phone");
                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                // TODO : use item data.
            }
        }) ;

    }// end of oncreate

    private void save_values() {
            EditText editTextNo = (EditText) findViewById(R.id.editTextNo) ;
            String no = editTextNo.getText().toString();
            EditText editTextName = (EditText) findViewById(R.id.editTextName) ;
            String name = editTextName.getText().toString() ;
            EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone) ;
            String phone = editTextPhone.getText().toString() ;
            CheckBox checkBoxOver20 = (CheckBox) findViewById(R.id.checkBoxOver20) ;
            boolean isOver20 = checkBoxOver20.isChecked();

            ContactVO vo = new ContactVO();
            vo.set_no(Integer.parseInt(no));
            vo.setName(name);
            vo.setPhone(phone);
            vo.setOver20(isOver20 == true ? 1 : 0); //true면 1 false면 0

            new ContactDAO().insert(getApplicationContext(), vo);
            load_values(); //전체조회
        }


    private void load_values() {
        //MVC 중에서 Model listView에 뿌릴거.
        ArrayList<HashMap<String, String>> list =
                new ContactDAO().selectAll(getApplicationContext());

        //adaptor( == Controller), 연결하는거 Model이랑 View랑
        SimpleAdapter simpleAdapter =
                new SimpleAdapter(this,
                                list,               //Model(data), 넘어가는값 map
                                android.R.layout.simple_list_item_2, //string값을 두개 뿌릴수있는,view
                                new String[]{"_no", "name"},
                                new int[]{android.R.id.text1, android.R.id.text2});
        //ListView에 adeptor 연결
        ListView listView = findViewById(R.id.lvContact);
        listView.setAdapter(simpleAdapter);

        //두번째 listView에 CustomAdaptor 연결
        ListViewAdaptor adapter = new ListViewAdaptor(); //ter인데 tor로 만듬
        adapter.setList(list);
        ListView lvCustom = findViewById(R.id.lvCustom);
        lvCustom.setAdapter(adapter);
    }


//    private SQLiteDatabase init_database() {
//        dbHelper = new DBHelper(getApplicationContext());  //DB, table 생성, init_tables() 지움
//        SQLiteDatabase db = dbHelper.getWritableDatabase(); //DB
//        return db ;
//    }

}// end of main
