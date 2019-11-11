package com.example.pruebaandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Perfil_Medico extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    private EditText mEditNameMedico;
    private EditText mEditApeMedico;
    private EditText mEditEdadMedico;
    private EditText mEditCelularMedico;
    private EditText mEditCorreoMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil__medico);

        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        mEditNameMedico = (EditText) findViewById(R.id.txt_nombre_medico);
        mEditApeMedico = (EditText) findViewById(R.id.txt_ape_medico);
        mEditEdadMedico = (EditText)findViewById(R.id.txt_edad_medico);
        mEditCelularMedico = (EditText)findViewById(R.id.txt_cel_medico) ;
        mEditCorreoMedico = (EditText) findViewById(R.id.txt_correo_medico);
        obtenerDatos();
    }

    private void obtenerDatos(){
        String id =mAuth.getCurrentUser().getUid();
        mDataBase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.child("Nombre").getValue().toString();
                    String ape = dataSnapshot.child("Apellidos").getValue().toString();
                    String edad = dataSnapshot.child("Edad").getValue().toString();
                    String celular = dataSnapshot.child("Celular").getValue().toString();
                    String correo = dataSnapshot.child("Correo").getValue().toString();
                    mEditNameMedico.setText(name);
                    mEditApeMedico.setText(ape);
                    mEditEdadMedico.setText(edad);
                    mEditCelularMedico.setText(celular);
                    mEditCorreoMedico.setText(correo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
