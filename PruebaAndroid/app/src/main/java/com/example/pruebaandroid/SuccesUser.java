package com.example.pruebaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SuccesUser extends AppCompatActivity {

    private Button mButtonCerrarSesion;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes_user);
        mAuth = FirebaseAuth.getInstance();
        mButtonCerrarSesion = findViewById(R.id.btnCerrarSesion);

        mButtonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(SuccesUser.this , MainActivity.class));

                finish();
            }
        });
    }
}
