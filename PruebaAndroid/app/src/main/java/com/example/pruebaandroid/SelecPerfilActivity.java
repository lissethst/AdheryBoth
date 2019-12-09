package com.example.pruebaandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SelecPerfilActivity extends AppCompatActivity {

    private Button mButtonPerfilMedico;
    private Button mButtonPerfilPaciente;
    private TextView mTextView;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selec_perfil);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mButtonPerfilPaciente = findViewById(R.id.btnPerfilPaciente);
        mButtonPerfilMedico = findViewById(R.id.btnPerfilMedico);
        mTextView = (TextView) findViewById(R.id.txtView);
        obtenerDatos();

        mButtonPerfilMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(tipo.equals("1")){
                   startActivity(new Intent(SelecPerfilActivity.this , Menu_MedicoActivity.class));
                   finish();
               }else{
                   Toast.makeText( SelecPerfilActivity.this ,"No tiene los permisos de Medico Especialista",Toast.LENGTH_SHORT).show();

               }
            }
        });

        mButtonPerfilPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tipo.equals("2")){
                    startActivity(new Intent(SelecPerfilActivity.this , Menu_PacienteActivity.class));
                    finish();
                }else{
                    Toast.makeText( SelecPerfilActivity.this ,"No tiene los permisos de Paciente",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    String tipo;
    private void obtenerDatos(){
        String id =mAuth.getCurrentUser().getUid();

        mDataBase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    tipo = dataSnapshot.child("Tipo").getValue().toString();
                    mTextView.setText(tipo);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
