package com.ilham.mp_produk;

import com.ilham.mp_produk.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("produk")
    abstract Call<List<Model>> getProduk();
}
