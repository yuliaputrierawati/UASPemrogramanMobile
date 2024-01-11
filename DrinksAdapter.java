package com.example.aplikasipesanmakanan;

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

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    ViewHolder holder;

    public DrinksAdapter(ArrayList<Drinks> listDrinks) {
        this.listDrinks = listDrinks;
    }

    private ArrayList<Drinks> listDrinks;

    @NonNull
    @Override
    public DrinksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(@NonNull DrinksAdapter.ViewHolder holder, int position) {
        Drinks drinks = listDrinks.get(position);
        holder.txtNamaDrinks.setText(drinks.getNamaDrinks());
        holder.txtHargaDrinks.setText(rp(Integer.parseInt(drinks.getHargaDrinks())));
        holder.imgDrinks.setImageResource(drinks.getImgDrinks());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listDrinks.get(position).getNamaDrinks().equals("Es Jeruk")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.es_jeruk);
                    intent.putExtra("NAMA_DEFAULT", "Es Jeruk");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Ada bulir jeruknya lohh");
                    intent.putExtra("HARGA_DEFAULT", "8000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Es Teh")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.es_teh);
                    intent.putExtra("NAMA_DEFAULT", "Es Teh");
                    intent.putExtra("DESKRIPSI_DEFAULT", "tehnya dari teh pilihan");
                    intent.putExtra("HARGA_DEFAULT", "5000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Es Soda Gembira")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.es_soda_gembira);
                    intent.putExtra("NAMA_DEFAULT", "Es Soda Gembira");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Beneran bikin gembira deh");
                    intent.putExtra("HARGA_DEFAULT", "7000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Kopi Susu")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.kopi_susu);
                    intent.putExtra("NAMA_DEFAULT", "Kopi Susu");
                    intent.putExtra("DESKRIPSI_DEFAULT", "enak pokoknya");
                    intent.putExtra("HARGA_DEFAULT", "5000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Air Mineral")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.air_mineral);
                    intent.putExtra("NAMA_DEFAULT", "Air Mineral");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Seger bukan main");
                    intent.putExtra("HARGA_DEFAULT", "3000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Es Kelapa Muda")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.es_kepala_muda);
                    intent.putExtra("NAMA_DEFAULT", "Es Kelapa Muda");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Seger dan banyak manfaatnya");
                    intent.putExtra("HARGA_DEFAULT", "8000");
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDrinks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNamaDrinks, txtHargaDrinks;
        public ImageView imgDrinks;
        public ConstraintLayout itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaDrinks = (TextView) itemView.findViewById(R.id.txtNamaItem);
            txtHargaDrinks = (TextView) itemView.findViewById(R.id.txtHargaItem);
            imgDrinks = (ImageView) itemView.findViewById(R.id.imgItem);
            this.itemView = (ConstraintLayout) itemView.findViewById(R.id.itemLayout);
        }
    }
}
