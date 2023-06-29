package com.if4a.komputersparepart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.if4a.komputersparepart.API.APIRequestData;
import com.if4a.komputersparepart.API.RetroServer;
import com.if4a.komputersparepart.Model.ModelResponse;
import com.if4a.komputersparepart.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {

    private EditText etNama, etKategori, etDeskripsi, etType, etKapasitas;
    private Button btnSimpan;
    private String nama, kategori, deskripsi, type, kapasitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etKategori = findViewById(R.id.et_kategori);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etType = findViewById(R.id.et_type);
        etKapasitas = findViewById(R.id.et_kapasitas);
        btnSimpan = findViewById(R.id.btn_tambah);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                kategori = etKategori.getText().toString();
                deskripsi = etDeskripsi.getText().toString();
                type = etType.getText().toString();
                kapasitas = etKapasitas.getText().toString();

                if(nama.trim().isEmpty())
                {
                    etNama.setError("Nama Tidak Boleh Kosong");
                }
                else if(kategori.trim().isEmpty())
                {
                    etKategori.setError("Asal Tidak Boleh Kosong");
                }
                else if(deskripsi.trim().isEmpty())
                {
                    etDeskripsi.setError("Deskripsi Tidak Boleh Kosong");
                }
                else if(type.trim().isEmpty())
                {
                    etType.setError("Type Tidak Boleh Kosong");
                }
                else if(kapasitas.trim().isEmpty())
                {
                    etKapasitas.setError("Kapasitas Tidak Boleh Kosong");
                }
                else
                {
                    tambahKuliner();
                }

            }
        });

    }
    private void tambahKuliner()
    {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardCreate(nama, kategori, deskripsi, type, kapasitas);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : " + kode + ", Pesan :" + pesan , Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
