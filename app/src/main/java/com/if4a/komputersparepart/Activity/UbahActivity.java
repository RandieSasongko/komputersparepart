package com.if4a.komputersparepart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class UbahActivity extends AppCompatActivity {
    private String yId, yNama, yKategori, yDeskripsi, yType, yKapasitas;
    String id, nama, kategori, deskripsi, type, kapasitas;
    private EditText etNama, etKategori, etDeskripsi, etType, etKapasitas;
    private Button btnUbah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yKategori = ambil.getStringExtra("xKategori");
        yDeskripsi = ambil.getStringExtra("xDeskripsi");
        yType = ambil.getStringExtra("xType");
        yKapasitas = ambil.getStringExtra("xKapasitas");

        btnUbah = findViewById(R.id.btn_ubah);
        etNama = findViewById(R.id.et_nama);
        etKategori = findViewById(R.id.et_kategori);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etType = findViewById(R.id.et_type);
        etKapasitas = findViewById(R.id.et_kapasitas);

        etNama.setText(yNama);
        etKategori.setText(yKategori);
        etDeskripsi.setText(yDeskripsi);
        etType.setText(yType);
        etKapasitas.setText(yKapasitas);

        btnUbah.setOnClickListener(new View.OnClickListener() {
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
                    ubahKuliner();
                }

            }
        });
    }

    private void ubahKuliner()
    {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardUpdate(yId, nama, kategori, deskripsi, type, kapasitas);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode : " + kode + ", Pesan :" + pesan , Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Serve : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}