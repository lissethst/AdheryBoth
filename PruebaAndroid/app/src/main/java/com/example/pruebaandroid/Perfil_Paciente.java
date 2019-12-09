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

public class Perfil_Paciente extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    private EditText mEditNamePaciente;
    private EditText mEditApePaciente;
    private EditText mEditEdadPaciente;
    private EditText mEditCorreoPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil__paciente);

        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        mEditNamePaciente = (EditText) findViewById(R.id.txt_nombre_paciente);
        mEditApePaciente = (EditText) findViewById(R.id.txt_ape_paciente);
        mEditEdadPaciente = (EditText)findViewById(R.id.txt_edad_paciente);
        mEditCorreoPaciente = (EditText) findViewById(R.id.txt_correo_paciente);
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
                    String correo = dataSnapshot.child("Correo").getValue().toString();
                    mEditNamePaciente.setText(name);
                    mEditApePaciente.setText(ape);
                    mEditCorreoPaciente.setText(correo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
