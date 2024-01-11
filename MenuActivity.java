package com.example.aplikasipesanmakanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    String namaUser;
    TextView txtNama;

    FirebaseUser user;
    FirebaseAuth mAuth;

    FirebaseFirestore fireDb;

    private RecyclerView recPenyetan;
    private RecyclerView recDrinks;
    private ArrayList<Penyetan> listPenyetan;
    private ArrayList<Drinks> listDrinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initFab();

        fireDb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        namaUser = user.getEmail();

        recPenyetan = findViewById(R.id.rec_penyetan);
        recDrinks = findViewById((R.id.rec_drinks));
        initDataPenyetan();
        initDataDrinks();

        recPenyetan.setAdapter(new PenyetanAdapter(listPenyetan));
        recPenyetan.setLayoutManager(new LinearLayoutManager(this));

        recDrinks.setAdapter(new DrinksAdapter(listDrinks));
        recDrinks.setLayoutManager(new LinearLayoutManager(this));

        txtNama = findViewById(R.id.txtNama);
        txtNama.setText("Hi, " + namaUser);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initFab(){
        FloatingActionButton fabCart = findViewById(R.id.fabCart);
        fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), CartActivity.class));
            }
        });

        FloatingActionButton fabLogout = findViewById(R.id.fabLogout);
        fabLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getBaseContext(), MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
    }

    private void initDataPenyetan(){
        this.listPenyetan =  new ArrayList<>();
        listPenyetan.add(new Penyetan("Ayam Goreng", "23000", R.drawable.ayam_goreng));
        listPenyetan.add(new Penyetan("Bakso", "20000", R.drawable.bakso));
        listPenyetan.add(new Penyetan("Lele Goreng", "13000", R.drawable.lele_goreng));
        listPenyetan.add(new Penyetan("Bakso Bakar", "10000", R.drawable.bakso_bakar));
        listPenyetan.add(new Penyetan("Kentang Goreng", "10000", R.drawable.kentang_goreng));
        listPenyetan.add(new Penyetan("Mie Ayam", "15000", R.drawable.mie_ayam));
    }

    private void initDataDrinks(){
        this.listDrinks = new ArrayList<>();
        listDrinks.add(new Drinks("Es Jeruk", "8000", R.drawable.es_jeruk));
        listDrinks.add(new Drinks("Es Teh", "5000", R.drawable.es_teh));
        listDrinks.add(new Drinks("Es Soda Gembira", "7000", R.drawable.es_soda_gembira));
        listDrinks.add(new Drinks("Kopi Susu", "5000", R.drawable.kopi_susu));
        listDrinks.add(new Drinks("Air Mineral", "3000", R.drawable.air_mineral));
        listDrinks.add(new Drinks("Es Kelapa Muda", "8000", R.drawable.es_kepala_muda));
    }
}