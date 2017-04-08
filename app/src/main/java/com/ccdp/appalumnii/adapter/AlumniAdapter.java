package com.ccdp.appalumnii.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccdp.appalumnii.R;
import com.ccdp.appalumnii.model.Alumni;

import java.util.ArrayList;

/**
 * Created by lenovo on 23/03/2017.
 */
public class AlumniAdapter  extends ArrayAdapter<Alumni> {

    private ArrayList<Alumni> dataset;
    Context context;

    public  AlumniAdapter (Context context, int resource, ArrayList<Alumni> objects){
        super(context, resource, objects);
        this.context = context;
        this.dataset = objects;
    }

    //View Holder
    private static class ViewHolder{
        TextView txtId;
        TextView txtNama;
        TextView txtNrp;
        TextView txtIdJurusan;
        TextView txtTglLulus;
        TextView txtTempatKerja;
        TextView txtTelpon;
        TextView txtEmail;
    }

    //getView

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Alumni alumni = getItem(position);

        viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.row_item, parent, false);
        viewHolder.txtId = (TextView) convertView.findViewById(R.id.id);
        viewHolder.txtNama = (TextView) convertView.findViewById(R.id.nama);
        viewHolder.txtNrp = (TextView) convertView.findViewById(R.id.nrp);
        viewHolder.txtIdJurusan = (TextView) convertView.findViewById(R.id.idJurusan);
        viewHolder.txtTglLulus = (TextView) convertView.findViewById(R.id.tglLulus);
        viewHolder.txtTempatKerja = (TextView) convertView.findViewById(R.id.tempatKerja);
        viewHolder.txtTelpon = (TextView) convertView.findViewById(R.id.telpon);
        viewHolder.txtEmail = (TextView) convertView.findViewById(R.id.email);

        try{
            String alumniId = String.valueOf(alumni.getId());
            viewHolder.txtId.setText(alumniId);
            viewHolder.txtNama.setText(alumni.getNama());
            viewHolder.txtNrp.setText(alumni.getNrp());
            viewHolder.txtIdJurusan.setText(alumni.getIdJurusan());
            viewHolder.txtTglLulus.setText(alumni.getTglLulus());
            viewHolder.txtTempatKerja.setText(alumni.getTempatKerja());
            viewHolder.txtTelpon.setText(alumni.getTelpon());
            viewHolder.txtEmail.setText(alumni.getEmail());
        }catch (Exception e){
            e.printStackTrace();
        }
        return convertView;
    }
}
