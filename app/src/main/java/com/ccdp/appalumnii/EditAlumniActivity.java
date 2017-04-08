package com.ccdp.appalumnii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class EditAlumniActivity extends AppCompatActivity {

    private EditText txtNama;
    private EditText txtNrp;
    private Spinner mSpinnerIdJurusan;
    private EditText txtTglLulus;
    private EditText txtTempatKerja;
    private EditText txtTelpon;
    private EditText txtEmail;
    private Button btnSave;

    private Retrofit retrofit;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_alumni);

            txtNama = (EditText) findViewById(R.id.nama);
            txtNrp = (EditText) findViewById(R.id.nrp);
            mSpinnerIdJurusan = (Spinner) findViewById(R.id.idJurusan);
            txtTglLulus = (EditText) findViewById(R.id.tglLulus);
            txtTempatKerja = (EditText) findViewById(R.id.tempatKerja);
            txtTelpon = (EditText) findViewById(R.id.telpon);
            txtEmail = (EditText) findViewById(R.id.email);
            btnSave = (Button) findViewById(R.id.btn_save);

            String[] jurusanArray = new String[]{"1","2"};
            ArrayAdapter<String> jurusanAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,jurusanArray);
            jurusanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinnerIdJurusan.setAdapter(jurusanAdapter);

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
                        txtNama.setText(alumni.getNama());
                        txtNrp.setText(alumni.getNrp());
                        txtTglLulus.setText(alumni.getTglLulus());
                        txtTempatKerja.setText(alumni.getTempatKerja());
                        txtTelpon.setText(alumni.getTelpon());
                        txtEmail.setText(alumni.getEmail());
                    }catch (Exception e){
                        Toast.makeText(EditAlumniActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<AlumniResults> call, Throwable t) {
                    Toast.makeText(EditAlumniActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Call<Alumni> call = alumniAPI.update(txtNama.getText().toString(),txtNrp.getText().toString(),mSpinnerIdJurusan.getSelectedItem().toString(),txtTglLulus.getText().toString(),txtTempatKerja.getText().toString(),txtTelpon.getText().toString(),txtEmail.getText().toString(),id);
                    call.enqueue(new Callback<Alumni>() {
                        @Override
                        public void onResponse(Call<Alumni> call, Response<Alumni> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(EditAlumniActivity.this,"Data berhasil diubah",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditAlumniActivity.this,MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(EditAlumniActivity.this,"Data gagal diubah",Toast.LENGTH_SHORT);
                            }
                        }

                        @Override
                        public void onFailure(Call<Alumni> call, Throwable t) {
                            Toast.makeText(EditAlumniActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
    }
}
