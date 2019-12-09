package com.example.pruebaandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SuccesUser extends AppCompatActivity {

    private Button mButtonCerrarSesion;
    private TextView mTextViewUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes_user);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mButtonCerrarSesion = findViewById(R.id.btnCerrarSesion);
        mTextViewUser = (TextView) findViewById(R.id.txtViewUser);

        mButtonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                    startActivity(new Intent(SuccesUser.this , MainActivity.class));
                    finish();
            }
        });

        obtenerDatos();
    }
    String tipo;
    private void obtenerDatos(){
        String id =mAuth.getCurrentUser().getUid();
        mDataBase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.child("Nombre").getValue().toString();
                    mTextViewUser.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
