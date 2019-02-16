package com.example.mertcantoptas.volleyloginregister.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mertcantoptas.volleyloginregister.R;
import com.example.mertcantoptas.volleyloginregister.databinding.LoginActivityBinding;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    LoginActivityBinding binding;

    RequestQueue requestQueue;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    public void login(final  String email, final  String password ){
       StringRequest request = new StringRequest(

               Request.Method.POST,
               "http:/192.168.1.54:8888/volley/login.php",
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {

                       if("100".equals(response)){


                           startActivity(new Intent(getApplicationContext(), LoggedActivity.class));

                           editor.putString("email", binding.etLoginEmail.getText().toString());
                           editor.putString("sifre", binding.etLoginPassword.getText().toString());
                           editor.commit();

                           finish();

                       }else  if ("101".equals(response)){
                           Toast.makeText(getApplicationContext(), "Mail adresi veya şifre hatalı", Toast.LENGTH_SHORT).show();
                       }else if ("102".equals(response)){

                           Toast.makeText(getApplicationContext(), "Sunucu ile bağlantı yok", Toast.LENGTH_SHORT).show();
                       }

                   }
               },

               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                       Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                   }
               }

       )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("email", email);
                map.put("password",password);
                return map;
            }
        };

       requestQueue.add(request);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState   ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();


        binding.etLoginEmail.setText(sharedPreferences.getString("email",""));
        binding.etLoginPassword.setText(sharedPreferences.getString("sifre", ""));

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login(binding.etLoginEmail.getText().toString(), binding.etLoginPassword.getText().toString());


            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });


    }
}
