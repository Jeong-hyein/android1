package com.example.eventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { //implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);

        //1. 무명(익명) 클래스
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "버튼1 클릭", Toast.LENGTH_LONG).show();
            }
        });
        //2. 인터페이스 구현 클래스
        button2.setOnClickListener(new ButtonEventHandler());

        //메소드가 있기때문에 this 사용가능?
        //button2.setOnClickListener(this);

        //4.하나의 이벤트 핸들러 여러개의 view(컨포넌트) 제어
        Button.OnClickListener myclick = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.button4) {
                    Toast.makeText(getApplicationContext(), "버튼4 클릭", Toast.LENGTH_LONG).show();
                } else if (v.getId() == R.id.button5) {
                    Toast.makeText(getApplicationContext(), "버튼5 클릭", Toast.LENGTH_LONG).show();
                }
            }
        };
        button4.setOnClickListener((myclick));
        button5.setOnClickListener((myclick));

    } // end of onCreate method

// 3. view onclick"butPlusListener" 속성
    public void butPlusListener(View v){
        Toast.makeText(getApplicationContext(), "plus", Toast.LENGTH_LONG).show();
    }

// implements View.OnClickListener 부분의 method
//    @Override
//    public void onClick(View v) {
//        Toast.makeText(getApplicationContext(),
//                "버튼3 클릭", Toast.LENGTH_LONG).show();
//    }


    //내부클래스 정의, 인터페이스 상속받아서 클래스 만듬
    class ButtonEventHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(),
                    "버튼2 클릭", Toast.LENGTH_LONG).show();
        }
    }


} // end of enventactivity class
