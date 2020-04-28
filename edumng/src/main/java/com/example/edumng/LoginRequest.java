package com.example.edumng;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    final static private String URL = "http://10.0.2.2/androidServer/Login.do";
    private Map<String, String> parameters;

    //생성자, 서버로 넘겨줄 값들
    public LoginRequest(String userid, String userpw, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        //map에 담는다.
        parameters = new HashMap<>();
        parameters.put("userid", userid);
        parameters.put("userpw", userpw);
        System.out.println("request");
    }

    //PUT이나 POST요구할때 Map타입을 리턴함
    @Override
    //getParams에 넘겨준다.
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}
