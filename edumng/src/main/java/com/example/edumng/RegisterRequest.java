package com.example.edumng;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//서버에서 응답이오면 해결할거다.
public class RegisterRequest extends StringRequest {
    //현재 안드로이드앱을 에뮬레이터로 돌리므로 에뮬레이터가 설치된 서버에 있는 아파치 서버에 접근하려면
    //다음과 같이 10.0.2.2:포트번호 로 접근해야합니다
    final static private String URL = "http://10.0.2.2/androidServer/Register.do";
    private Map<String, String> parameters;

    //생성자, 서버로 넘겨줄 값들
    public RegisterRequest(String userid, String userpw, String username, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        //map에 담는다.
        parameters = new HashMap<>();
        parameters.put("userid", userid);
        parameters.put("userpw", userpw);
        parameters.put("username", username);
        System.out.println("request");
    }

    //PUT이나 POST요구할때 Map타입을 리턴함
    @Override
    //getParams에 넘겨준다.
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}
