package com.example.aplikasipesanmakanan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PenyetanAdapter extends RecyclerView.Adapter<PenyetanAdapter.ViewHolder> {

    ViewHolder holder;

    public PenyetanAdapter(ArrayList<Penyetan> listPenyetan) {
        this.listPenyetan = listPenyetan;
    }

    private ArrayList<Penyetan> listPenyetan;

    @NonNull
    @Override
    public PenyetanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder =  new ViewHolder(inflater.inflate(R.layout.item_menu, parent, false));

        return holder;
    }

    public String rp(int txt){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(txt); // Integer.toString(total);
    }

    @Override
    public void onBindViewHolder(@NonNull PenyetanAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Penyetan penyetan = listPenyetan.get(position);
        holder.txtNamaPenyetan.setText(penyetan.getNamaPenyetan());
        holder.txtHargaPenyetan.setText(rp(Integer.parseInt(penyetan.getHargaPenyetan())));
        holder.imgPenyetan.setImageResource(penyetan.getImgPenyetan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listPenyetan.get(position).getNamaPenyetan().equals("Ayam Goreng")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.ayam_goreng);
                    intent.putExtra("NAMA_DEFAULT", "Ayam Goreng");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Ayam Goreng Jamin Gurih Sambal Mantap deh");
                    intent.putExtra("HARGA_DEFAULT", "23000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listPenyetan.get(position).getNamaPenyetan().equals("kentang goreng")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.kentang_goreng);
                    intent.putExtra("NAMA_DEFAULT", "kentang goreng");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Kentang goreng si paling kremez deh");
                    intent.putExtra("HARGA_DEFAULT", "25000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listPenyetan.get(position).getNamaPenyetan().equals("mie ayam")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.mie_ayam);
                    intent.putExtra("NAMA_DEFAULT", "mie ayam");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Gak akan Cukup kalo pesen 1 doang");
                    intent.putExtra("HARGA_DEFAULT", "13000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listPenyetan.get(position).getNamaPenyetan().equals("bakso bakar")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.bakso_bakar);
                    intent.putExtra("NAMA_DEFAULT", "bakso bakar");
                    intent.putExtra("DESKRIPSI_DEFAULT", "soal rasa Juara deh kalo ini");
                    intent.putExtra("HARGA_DEFAULT", "10000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listPenyetan.get(position).getNamaPenyetan().equals("bakso")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.bakso);
                    intent.putExtra("NAMA_DEFAULT", "Bakso");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Menu andalan anak muda");
                    intent.putExtra("HARGA_DEFAULT", "20000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listPenyetan.get(position).getNamaPenyetan().equals("lel goreng")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.lele_goreng);
                    intent.putExtra("NAMA_DEFAULT", "Lele Goreng");
                    intent.putExtra("DESKRIPSI_DEFAULT", "jgn coba deh kalo gk suka pedes");
                    intent.putExtra("HARGA_DEFAULT", "15000");
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPenyetan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNamaPenyetan, txtHargaPenyetan;
        public ImageView imgPenyetan;
        public ConstraintLayout itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaPenyetan = (TextView) itemView.findViewById(R.id.txtNamaItem);
            txtHargaPenyetan = (TextView) itemView.findViewById(R.id.txtHargaItem);
            imgPenyetan = (ImageView) itemView.findViewById(R.id.imgItem);
            this.itemView = (ConstraintLayout) itemView.findViewById(R.id.itemLayout);
        }
    }
}
