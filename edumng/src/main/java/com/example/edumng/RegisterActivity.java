package com.example.edumng;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText txtUserid = findViewById(R.id.txtUserid);
        final EditText txtUserpw = findViewById(R.id.txtUserpw);
        final EditText txtUsername = findViewById(R.id.txtUsername);
        final Button btnRegister = findViewById(R.id.btnRegister);

        //바깥것을 참조하려면 final로 지정해주면 된다.
        //등록버튼을 누르면 onclick이 실행된다.
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid = txtUserid.getText().toString();
                String userpw = txtUserpw.getText().toString();
                String username = txtUsername.getText().toString();

                //4. 콜백 처리부분(volley 사용을 위한 ResponseListener 구현 부분)
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    //서버로부터 여기서 데이터를 받음
                    @Override //서버가 넘겨주는 결과값을 response가 받음
                    public void onResponse(String response) { //"{ success: false}"
                        try {
                            //서버로부터 받는 데이터는 JSON타입의 객체
                            JSONObject jsonResponse = new JSONObject(response); //string -> object

                            //그중 Key값이 "success"인 것을 가져온다.
                            boolean success = jsonResponse.getBoolean("success"); //(boolean)emp.get("success")
                            System.out.println("response");
                            //회원 가입 성공시 success값이 true임
                            if(success){
                                //알림상자를 만들어서 보여줌
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("register success!!")
                                        .setPositiveButton("ok", null)
                                        .create()
                                        .show(); //show()를 해줘야 화면이 보인다.

                                //그리고 첫화면(로그인페이지)으로 돌아감
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);

                            }
                            //회원 가입 실패시 success값이 false임
                            else{

                                //알림상자를 만들어서 보여줌
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("register fail!!")
                                        .setNegativeButton("ok", null)
                                        .create()
                                        .show();
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }

                    }
                };//responseListener 끝

                //volley 사용법
                //1. RequestObject를 생성한다. 이때 서버로부터 데이터를 받을 responseListener를 반드시 넘겨준다.
                RegisterRequest registerRequest = new RegisterRequest(userid, userpw, username, responseListener);
                //2. RequestQueue를 생성한다.
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                //3. RequestQueue에 RequestObject를 넘겨준다.
                queue.add(registerRequest); //서버 요청 시작
            }
        });
    }//end of override

} // end registerActivity
