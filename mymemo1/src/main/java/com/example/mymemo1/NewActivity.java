package com.example.mymemo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_memo);

        Button btnSave = (Button)findViewById(R.id.button3);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText editTitle = (EditText) findViewById(R.id.editTitle) ;
                String title = editTitle.getText().toString();
                EditText editContent = (EditText) findViewById(R.id.editContent) ;
                String content = editContent.getText().toString() ;

                MemoVO vo = new MemoVO();
                vo.setTitle(title);
                vo.setContent(content);
                new MemoDAO().insert(getApplicationContext(), vo);

              Intent intent = new Intent(getApplicationContext(), MainActivity.class);
              startActivity(intent);
              finish();
            }
        });
    }
}
