package com.if4a.kulinerkita.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.if4a.kulinerkita.R;

public class TambahActivity extends AppCompatActivity {

    private EditText etNama, etAsal, etDeskripsiSingkat;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etAsal = findViewById(R.id.et_asal);
        etDeskripsiSingkat = findViewById(R.id.et_deskripsi_singkat);
        btnSimpan = findViewById(R.id.btn_tambah);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama, asal, deskripsiSingkat;

                nama = etNama.getText().toString();
                asal = etAsal.getText().toString();
                deskripsiSingkat = etDeskripsiSingkat.getText().toString();

                if(nama.trim().isEmpty())
                {
                    etNama.setError("Nama Tidak Boleh Kosong");
                }
                else if(asal.trim().isEmpty())
                {
                    etAsal.setError("Asal Tidak Boleh Kosong");
                }
                else if(deskripsiSingkat.trim().isEmpty())
                {
                    etDeskripsiSingkat.setError("Deskripsi Singkat Tidak Boleh Kosong");
                }
                else
                {
                    
                }

            }
        });

    }
}