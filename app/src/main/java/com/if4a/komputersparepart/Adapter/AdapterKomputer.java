package com.if4a.komputersparepart.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.if4a.komputersparepart.API.APIRequestData;
import com.if4a.komputersparepart.API.RetroServer;
import com.if4a.komputersparepart.Activity.DetailActivity;
import com.if4a.komputersparepart.Activity.MainActivity;
import com.if4a.komputersparepart.Activity.UbahActivity;
import com.if4a.komputersparepart.Model.ModelKomputer;
import com.if4a.komputersparepart.Model.ModelResponse;
import com.if4a.komputersparepart.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterKomputer extends RecyclerView.Adapter<AdapterKomputer.VHKuliner> {

    private Context ctx;
    private List<ModelKomputer> listKuliner;

    public AdapterKomputer(Context ctx, List<ModelKomputer> listKuliner) {
        this.ctx = ctx;
        this.listKuliner = listKuliner;
    }

    @NonNull
    @Override
    public VHKuliner onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_kuliner, parent, false);
        return new VHKuliner(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHKuliner holder, int position) {
        ModelKomputer MK = listKuliner.get(position);
        holder.bind(MK);
        holder.tvId.setText(MK.getId());
        holder.tvNama.setText(MK.getNama());
        holder.tvKategori.setText(MK.getKategori());
        holder.tvDeskripsi.setText(MK.getDeskripsi());
        holder.tvType.setText(MK.getType());
        holder.tvKapasitas.setText(MK.getKapasitas());
        holder.cardView.setCardBackgroundColor(getRandomColor(holder.itemView.getContext()));
    }

    private int getRandomColor(Context context) {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.list_1);
        colorCode.add(R.color.list_2);
        colorCode.add(R.color.list_3);
        colorCode.add(R.color.list_4);
        colorCode.add(R.color.list_5);
        colorCode.add(R.color.list_6);
        colorCode.add(R.color.list_7);
        colorCode.add(R.color.list_8);
        colorCode.add(R.color.list_9);

        Random random = new Random();
        int number = random.nextInt(colorCode.size());
        return ContextCompat.getColor(context, colorCode.get(number));
    }

    @Override
    public int getItemCount() {
        return listKuliner.size();
    }

    public class VHKuliner extends RecyclerView.ViewHolder {
        TextView tvId, tvNama, tvKategori, tvDeskripsi, tvType, tvKapasitas;
        ImageView icKategori;
        CardView cardView;

        public VHKuliner(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvKategori = itemView.findViewById(R.id.tv_kategori);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
            tvType = itemView.findViewById(R.id.tv_type);
            tvKapasitas = itemView.findViewById(R.id.tv_kapasitas);
            cardView = itemView.findViewById(R.id.item_container);
            icKategori = itemView.findViewById(R.id.ic_kategori);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pindah = new Intent(ctx, DetailActivity.class);

                    pindah.putExtra("xId", tvId.getText().toString());
                    pindah.putExtra("xNama", tvNama.getText().toString());
                    pindah.putExtra("xKategori", tvKategori.getText().toString());
                    pindah.putExtra("xDeskripsi", tvDeskripsi.getText().toString());
                    pindah.putExtra("xType", tvType.getText().toString());
                    pindah.putExtra("xKapasitas", tvKapasitas.getText().toString());

                    ctx.startActivity(pindah);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Operasi apa yang akan dilakukan");
                    pesan.setCancelable(true);

                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hapusKuliner(tvId.getText().toString());
                            dialog.dismiss();
                        }
                    });

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent pindah = new Intent(ctx, UbahActivity.class);

                            pindah.putExtra("xId", tvId.getText().toString());
                            pindah.putExtra("xNama", tvNama.getText().toString());
                            pindah.putExtra("xKategori", tvKategori.getText().toString());
                            pindah.putExtra("xDeskripsi", tvDeskripsi.getText().toString());
                            pindah.putExtra("xType", tvType.getText().toString());
                            pindah.putExtra("xKapasitas", tvKapasitas.getText().toString());

                            ctx.startActivity(pindah);
                        }
                    });

                    pesan.show();
                    return false;
                }
            });
        }

        public void bind(ModelKomputer kuliner) {
            tvId.setText(kuliner.getId());
            tvNama.setText(kuliner.getNama());
            tvKategori.setText(kuliner.getKategori());
            tvDeskripsi.setText(kuliner.getDeskripsi());
            tvType.setText(kuliner.getType());
            tvKapasitas.setText(kuliner.getKapasitas());

            if (kuliner.getKategori().equalsIgnoreCase("RAM")) {
                icKategori.setImageResource(R.drawable.ic_memory);
            } else if (kuliner.getKategori().equalsIgnoreCase("Powersupply")) {
                icKategori.setImageResource(R.drawable.ic_power_supply);
            } else if (kuliner.getKategori().equalsIgnoreCase("Motherboard")) {
                icKategori.setImageResource(R.drawable.ic_motherboard);
            } else if (kuliner.getKategori().equalsIgnoreCase("Processor")) {
                icKategori.setImageResource(R.drawable.ic_processor);
            } else if (kuliner.getKategori().equalsIgnoreCase("Storage")) {
                icKategori.setImageResource(R.drawable.ic_storage);
            } else if (kuliner.getKategori().equalsIgnoreCase("VGA")) {
                icKategori.setImageResource(R.drawable.ic_video_card);
            } else {
                icKategori.setImageResource(R.drawable.ic_mall_sparepart_pc);
            }
        };

        private void hapusKuliner(String idKuliner)
        {
            APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = ARD.ardDelete(idKuliner);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode : " + kode + ", Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveKuliner();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghapus Data!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
