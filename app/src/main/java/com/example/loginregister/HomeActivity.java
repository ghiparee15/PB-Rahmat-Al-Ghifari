package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button btnUser;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Menerima username yang dikirimkan dari Login
        username = getIntent().getStringExtra("USERNAME");

        btnUser = findViewById(R.id.btnUser);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kirim username ke ProfileUser
                Intent profileIntent = new Intent(getApplicationContext(), ProfileUser.class);
                profileIntent.putExtra("USERNAME", username); // Mengirim username
                startActivity(profileIntent);
            }
        });
    }
}
