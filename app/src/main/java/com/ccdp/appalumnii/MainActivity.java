package com.ccdp.appalumnii;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ccdp.appalumnii.adapter.AlumniAdapter;
import com.ccdp.appalumnii.api.AlumniAPI;
import com.ccdp.appalumnii.model.Alumni;
import com.ccdp.appalumnii.model.AlumniResults;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Alumni> alumnis;
    private static AlumniAdapter adapter;
    Retrofit retrofit;
    AlumniAPI alumniAPI;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.lvnotes);

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        alumniAPI = retrofit.create(AlumniAPI.class);
        Call<AlumniResults> call = alumniAPI.all();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        call.enqueue(new Callback<AlumniResults>() {
            @Override
            public void onResponse(Call<AlumniResults> call, Response<AlumniResults> response) {
                try{
                    alumnis.clear();
                    AlumniResults results = response.body();
                    List<Alumni> mems = results.data;
                    for(Alumni m : mems){
                        alumnis.add(m);
                    }
                    adapter.notifyDataSetChanged();

                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }

                }catch (Exception e){

                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }

                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AlumniResults> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        if(alumnis == null){
            alumnis = new ArrayList<>();
        }

        adapter = new AlumniAdapter(getApplicationContext(),R.layout.row_item,alumnis);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Alumni alumni = alumnis.get(position);
                Intent intent = new Intent(MainActivity.this, ViewAlumniActivity.class);
                intent  .putExtra("id",alumni.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_add:
                Intent intent = new Intent(MainActivity.this,AddAlumniActivity.class);
                startActivity(intent);

                break;

            case R.id.action_exit:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
