package com.example.edumng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //필드 확인

        TextView txtUserid = findViewById(R.id.txtUsername);
        TextView txtUserpw = findViewById(R.id.txtUserpw);
        TextView txtUsername = findViewById(R.id.txtUsername);
        Button btnManage = findViewById(R.id.btnManage);

        Intent intent  = getIntent();
        String userid = intent.getExtras().getString("userid");
        //수신데이터를 view에 출력
        txtUserid.setText(userid);
        //관리자가 아니면 회원관리 버튼이 안보이게 처리
        if(!userid.equals("admin")) {
            btnManage.setVisibility(View.GONE);
        }

    }
}
