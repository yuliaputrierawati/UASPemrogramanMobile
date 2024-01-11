package com.example.aplikasipesanmakanan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    TextView txtTotal;

    RecyclerView recPesanan;
    String namaUser;

    FirebaseUser user;
    FirebaseAuth mAuth;

    FirebaseFirestore fireDb;
    PesananAdapter adapter;

    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recPesanan = findViewById(R.id.rec_records);
        recPesanan.setLayoutManager(new LinearLayoutManager(this));

        fireDb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        namaUser = user.getEmail();


        txtTotal = findViewById(R.id.txt_total);
        txtTotal.setText("0");
        getSumTotal();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        Query query = fireDb.collection("pesanan")
                .whereEqualTo("userId", user.getUid());

        FirestoreRecyclerOptions<Pesanan> options = new FirestoreRecyclerOptions.Builder<Pesanan>()
                .setQuery(query, Pesanan.class).build();
        adapter = new PesananAdapter(options);
        recPesanan.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void getSumTotal() {
        Query query = fireDb.collection("pesanan").whereEqualTo("userId", user.getUid());

        // Inisialisasi referensi ke koleksi di database Firestore
        Query collection = fireDb.collection("pesanan").whereEqualTo("userId", user.getUid());

        // Menggunakan metode get() untuk mengambil semua dokumen dalam koleksi
        collection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // Looping melalui setiap dokumen untuk menjumlahkan nilai yang diinginkan
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        int value = document.getLong("hargaPesanan").intValue();
                        int value2 = document.getLong("jumlahPesanan").intValue();
                        total += value * value2;
                    }
                    // Menetapkan hasil jumlah ke TextView
                    Locale locale = new Locale("in", "ID");
                    NumberFormat format = NumberFormat.getCurrencyInstance(locale);
                    format.setMaximumFractionDigits(0);
                    txtTotal.setText(format.format(total));
                } else {
                    // Menangani kesalahan jika terjadi saat mengambil dokumen
                }
            }
        });
    }
}