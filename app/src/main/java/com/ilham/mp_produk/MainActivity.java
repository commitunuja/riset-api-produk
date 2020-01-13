package com.ilham.mp_produk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.ilham.mp_produk.adapter.ProdukAdapter;
import com.ilham.mp_produk.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProdukAdapter produkAdapter;
    private List<Model> tvDataProduk;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        APIInterface service= ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call=service.getProduk();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                tvDataProduk = response.body();
                produkAdapter = new ProdukAdapter(MainActivity.this,tvDataProduk);
                recyclerView.setAdapter(produkAdapter);
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
//                result.setText(t.getMessage());
                Toast.makeText(MainActivity.this, String.valueOf(t), Toast.LENGTH_LONG).show();
            }
        });
    }
}
