package com.example.mertcantoptas.volleyloginregister.Activity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mertcantoptas.volleyloginregister.Adapter.PaylasimAdapter;
import com.example.mertcantoptas.volleyloginregister.Model.Paylasim;
import com.example.mertcantoptas.volleyloginregister.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoggedActivity extends AppCompatActivity {
    PaylasimAdapter adapter;
    ArrayList<Paylasim> paylasim = new ArrayList<>();
    ListView listView;
    SharedPreferences sharedPreferences;

    RequestQueue queue;

    public  void  icerikEkle(final  String icerikYazisi){
        StringRequest istek = new StringRequest(

                Request.Method.POST,
                "http:/10.109.206.22:8888/volley/icerik_ekle.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    /*

                    gönderilen sayfadan gelen yanıtı yakalamadır.

                     */
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("icerik", icerikYazisi);
                map.put("mail", sharedPreferences.getString("email", "") );
                return map;

                /*
                php sayfasına değer gönderme yeri

                 */
            }
        };

        queue.add(istek);


    }

    public  void  dialogCagir(){

        final Dialog d = new Dialog(LoggedActivity.this);
        d.setContentView(R.layout.yazi_ekle_layout);

        final EditText etIcerik = d.findViewById(R.id.etIcerik);
        Button btn = d.findViewById(R.id.btnEkle);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                icerikEkle(etIcerik.getText().toString());

                d.dismiss();

            }
        });

        d.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logged, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.item_add){
            dialogCagir();
        } else  if (item.getItemId() == R.id.item_yenile){

            paylasim.clear();
            paylasimlariListele();
        }
        return true;
    }

    public void paylasimlariListele(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http:/10.109.206.22:8888/volley/paylasim_listele.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("RESPONSE",response);

                        try {
                            JSONObject object = new JSONObject(response);

                            JSONArray array = object.optJSONArray("paylasimlar");
                            Log.d("DENEME", "Eleman Sayısı:"+ array.length());

                            int elemansayisi = array.length();

                            for (int i =0; i<elemansayisi; i++){

                                JSONObject eleman = array.getJSONObject(i);

                                int id = eleman.getInt("id");
                                String tarih = eleman.getString("tarih");
                                String paylasan_mail= eleman.getString("paylasan_mail");
                                String paylasim_icerik = eleman.getString("paylasim_icerik");


                                paylasim.add(
                                        new Paylasim(
                                                id,
                                                tarih,
                                                paylasan_mail,
                                                paylasim_icerik
                                        )
                                );

                            }

                            adapter = new PaylasimAdapter(paylasim,getApplicationContext());
                            listView.setAdapter(adapter);

                            } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        queue.add(stringRequest);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        queue = Volley.newRequestQueue(getApplicationContext());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        listView = findViewById(R.id.listViewPaylasimlar);

        paylasimlariListele();


    }
}
