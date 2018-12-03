package com.example.mertcantoptas.volleyloginregister.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mertcantoptas.volleyloginregister.Model.Paylasim;
import com.example.mertcantoptas.volleyloginregister.R;

import java.util.ArrayList;

public class PaylasimAdapter extends BaseAdapter {
    private ArrayList<Paylasim> paylasimlar;
    private LayoutInflater layoutInflater;

    public PaylasimAdapter(){

    }

    public PaylasimAdapter(ArrayList<Paylasim> paylasimlar, Context context){
        this.layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.paylasimlar = paylasimlar;
    }

    @Override
    public int getCount() {
        return paylasimlar.size();
    }

    @Override
    public Object getItem(int position) {
        return paylasimlar.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.paylasim_satiri,null);

        TextView tvEmail,tvIcerik,tvTarih;

        tvEmail = v.findViewById(R.id.tvMail);
        tvIcerik = v.findViewById(R.id.tvIcerik);
        tvTarih = v.findViewById(R.id.tvTarih);

        tvEmail.setText(paylasimlar.get(position).getPaylasanEmail());
        tvIcerik.setText(paylasimlar.get(position).getIcerik());
        tvTarih.setText(paylasimlar.get(position).getTarih());
        return v;
    }
}
