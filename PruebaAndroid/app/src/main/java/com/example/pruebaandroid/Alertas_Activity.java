package com.example.pruebaandroid;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pruebaandroid.model.Alerta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import android.widget.Button;

public class Alertas_Activity extends AppCompatActivity {

    private Button mButtonNewAlerta;

    private DatabaseReference databaseReference;
    private ListView listView;
    private FirebaseAuth mAuth;
    private  String idL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertas_);

        mButtonNewAlerta = (Button) findViewById(R.id.button_newAlert);

        mButtonNewAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Alertas_Activity.this,NewAlert_Activity.class));
            }
        });

        /*
        databaseReference = FirebaseDatabase.getInstance().getReference("Alertas");
        Toast.makeText( Alertas_Activity.this ,"Instancia firebase finalizada",Toast.LENGTH_SHORT).show();
        // listView.setAdapter(arrayAdapter);
        mAuth = FirebaseAuth.getInstance();
        idL=mAuth.getCurrentUser().getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText( Alertas_Activity.this ,"Data base change",Toast.LENGTH_SHORT).show();

                ArrayAdapter<String> arrayAdapter ;
                ArrayList<String> arrayList = new ArrayList<String>();
                for(DataSnapshot datasnapshot:dataSnapshot.getChildren()){
                    Alerta alertaEncontrado = datasnapshot.getValue(Alerta.class);
                    String Nombre = alertaEncontrado.getNombre();
                    String Paciente = alertaEncontrado.getPaciente();
                    if(Paciente.equals(idL)){
                        arrayList.add(Nombre);
                    }

                }
                arrayAdapter = new ArrayAdapter<String>(Alertas_Activity.this,android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(arrayAdapter);
                Toast.makeText( Alertas_Activity.this ,"Fin datbase change",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }


}
