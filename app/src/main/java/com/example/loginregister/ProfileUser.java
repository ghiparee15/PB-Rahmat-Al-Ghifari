package com.example.loginregister;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileUser extends AppCompatActivity {

    private TextView tvUsername, tvNim, tvEmail;
    private ImageView ivProfilePicture;
    private DatabaseReference database;
    private String username;
    private Button btnPrevious, btnChange;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        btnPrevious = findViewById(R.id.btnPrevious);
        btnChange = findViewById(R.id.btnChange);

        username = getIntent().getStringExtra("USERNAME");

        if (username == null || username.isEmpty()) {
            Toast.makeText(this, "Username tidak tersedia", Toast.LENGTH_SHORT).show();
            return;
        }

        tvUsername = findViewById(R.id.tvUsername);
        tvNim = findViewById(R.id.tvNim);
        tvEmail = findViewById(R.id.tvEmail);
        ivProfilePicture = findViewById(R.id.ivProfilePicture);

        database = FirebaseDatabase.getInstance().getReference("users").child(username);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String usernameFromDb = dataSnapshot.child("username").getValue(String.class);
                    String nimFromDb = dataSnapshot.child("nim").getValue(String.class);
                    String emailFromDb = dataSnapshot.child("email").getValue(String.class);

                    tvUsername.setText(usernameFromDb);
                    tvNim.setText(nimFromDb);
                    tvEmail.setText(emailFromDb);

                    ivProfilePicture.setImageResource(R.drawable.ic_profile);
                } else {
                    Toast.makeText(ProfileUser.this, "Pengguna tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ProfileUser.this, "Gagal mengambil data pengguna", Toast.LENGTH_SHORT).show();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masuk = new Intent(getApplicationContext(), HomeActivity.class);
                masuk.putExtra("USERNAME", username);
                startActivity(masuk);
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(), Login.class);
                Toast.makeText(getApplicationContext(), "Menukar profile user", Toast.LENGTH_SHORT).show();
                startActivity(login);
            }
        });
    }
}
