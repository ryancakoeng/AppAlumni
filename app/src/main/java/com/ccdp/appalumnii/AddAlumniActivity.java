package com.ccdp.appalumnii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ccdp.appalumnii.api.AlumniAPI;
import com.ccdp.appalumnii.model.Alumni;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddAlumniActivity extends AppCompatActivity {

    private EditText txtNama;
    private EditText txtNrp;
    private Spinner mSpinnerIdJurusan;
    private EditText txtTglLulus;
    private EditText txtTempatKerja;
    private EditText txtTelpon;
    private EditText txtEmail;
    private Button btnSave;

    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alumni);

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

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final AlumniAPI alumniAPI = retrofit.create(AlumniAPI.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Alumni> call = alumniAPI.insert(txtNama.getText().toString(),txtNrp.getText().toString(),mSpinnerIdJurusan.getSelectedItem().toString(),txtTglLulus.getText().toString(),txtTempatKerja.getText().toString(),txtTelpon.getText().toString(),txtEmail.getText().toString());
                call.enqueue(new Callback<Alumni>() {
                    @Override
                    public void onResponse(Call<Alumni> call, Response<Alumni> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(AddAlumniActivity.this,"data berhasil disimpan",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddAlumniActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(AddAlumniActivity.this,"Data gagal disimpan",Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onFailure(Call<Alumni> call, Throwable t) {
                        Toast.makeText(AddAlumniActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
