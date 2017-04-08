package com.ccdp.appalumnii;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ccdp.appalumnii.api.AlumniAPI;
import com.ccdp.appalumnii.model.Alumni;
import com.ccdp.appalumnii.model.AlumniResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewAlumniActivity extends AppCompatActivity {

    private TextView txtId;
    private TextView txtNama;
    private TextView txtNrp;
    private TextView txtIdJurusan;
    private TextView txtTglLulus;
    private TextView txtTempatKerja;
    private TextView txtTelpon;
    private TextView txtEmail;
    private Button btnDelete,btnEdit;
    private int id;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_alumni);

        txtId = (TextView) findViewById(R.id.id);
        txtNama = (TextView) findViewById(R.id.nama);
        txtNrp = (TextView) findViewById(R.id.nrp);
        txtIdJurusan = (TextView) findViewById(R.id.idJurusan);
        txtTglLulus = (TextView) findViewById(R.id.tglLulus);
        txtTempatKerja = (TextView) findViewById(R.id.tempatKerja);
        txtTelpon = (TextView) findViewById(R.id.telpon);
        txtEmail = (TextView) findViewById(R.id.email);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnEdit = (Button) findViewById(R.id.btn_edit);

        //ambil data terbaru dari server
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final AlumniAPI alumniAPI = retrofit.create(AlumniAPI.class);
        Call<AlumniResults> call = alumniAPI.find(id);
        call.enqueue(new Callback<AlumniResults>() {
            @Override
            public void onResponse(Call<AlumniResults> call, Response<AlumniResults> response) {
                try {
                    AlumniResults results = response.body();
                    List<Alumni> mems = results.data;
                    Alumni alumni = mems.get(0);
                    Log.d("REQUEST = ",call.request().toString());
                    String strId = String.valueOf(alumni.getId());
                    txtId.setText(strId);
                    txtNama.setText(alumni.getNama());
                    txtNrp.setText(alumni.getNrp());
                    txtIdJurusan.setText(alumni.getIdJurusan());
                    txtTglLulus.setText(alumni.getTglLulus());
                    txtTempatKerja.setText(alumni.getTempatKerja());
                    txtTelpon.setText(alumni.getTelpon());
                    txtEmail.setText(alumni.getEmail());
                }catch (Exception e){
                    Toast.makeText(ViewAlumniActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AlumniResults> call, Throwable t) {
                Toast.makeText(ViewAlumniActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewAlumniActivity.this);
                builder.setTitle("Konfirmasi");
                builder.setMessage("Apakah anda yakin ingin menghapus data ?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        AlumniAPI alumniAPI = retrofit.create(AlumniAPI.class);
                        Call<Alumni> call = alumniAPI.delete(id);
                        Log.d("call request",call.request().toString());
                        call.enqueue(new Callback<Alumni>() {
                            @Override
                            public void onResponse(Call<Alumni> call, Response<Alumni> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(ViewAlumniActivity.this,"data berhasil dihapus",Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                }else{
                                    Toast.makeText(ViewAlumniActivity.this,"Data gagal dihapus",Toast.LENGTH_SHORT);
                                }
                            }

                            @Override
                            public void onFailure(Call<Alumni> call, Throwable t) {
                                Toast.makeText(ViewAlumniActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ViewAlumniActivity.this,EditAlumniActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(ViewAlumniActivity.this);


    }
}
