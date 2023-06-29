package com.if4a.komputersparepart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.if4a.komputersparepart.R;

public class DetailActivity extends AppCompatActivity {

    private String yId, yNama, yKategori, yDeskripsi, yType, yKapasitas;
    private TextView tvNama, tvKategori, tvDeskripsi, tvType, tvKapasitas;
    private ImageView icKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yKategori = ambil.getStringExtra("xKategori");
        yDeskripsi = ambil.getStringExtra("xDeskripsi");
        yType = ambil.getStringExtra("xType");
        yKapasitas = ambil.getStringExtra("xKapasitas");

        tvNama = findViewById(R.id.tv_nama);
        tvKategori = findViewById(R.id.tv_kategori);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
        tvType = findViewById(R.id.tv_type);
        tvKapasitas = findViewById(R.id.tv_kapasitas);
        icKategori = findViewById(R.id.ic_detail_kategori);

        tvNama.setText(yNama);
        tvKategori.setText(yKategori);
        tvDeskripsi.setText(yDeskripsi);
        tvType.setText(yType);
        tvKapasitas.setText(yKapasitas);

        if (yKategori.equalsIgnoreCase("RAM")) {
            icKategori.setImageResource(R.drawable.ic_memory);
        } else if (yKategori.equalsIgnoreCase("Powersupply")) {
            icKategori.setImageResource(R.drawable.ic_power_supply);
        } else if (yKategori.equalsIgnoreCase("Motherboard")) {
            icKategori.setImageResource(R.drawable.ic_motherboard);
        } else if (yKategori.equalsIgnoreCase("Processor")) {
            icKategori.setImageResource(R.drawable.ic_processor);
        } else if (yKategori.equalsIgnoreCase("Storage")) {
            icKategori.setImageResource(R.drawable.ic_storage);
        } else if (yKategori.equalsIgnoreCase("VGA")) {
            icKategori.setImageResource(R.drawable.ic_video_card);
        } else {
            icKategori.setImageResource(R.drawable.ic_mall_sparepart_pc);
        }
    }
}
