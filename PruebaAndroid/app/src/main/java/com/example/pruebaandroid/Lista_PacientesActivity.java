package com.example.pruebaandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pruebaandroid.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Lista_PacientesActivity extends AppCompatActivity {
    private Button mButtonNewPaciente;
    private DatabaseReference databaseReference;
    private ListView listView;


    private FirebaseAuth mAuth;
    private  String idL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__pacientes);
        databaseReference = FirebaseDatabase.getInstance().getReference(getString(R.string.nodoUser));
        listView = (ListView) findViewById(R.id.list_pacientes);

       // listView.setAdapter(arrayAdapter);
        mAuth = FirebaseAuth.getInstance();
        idL=mAuth.getCurrentUser().getUid();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayAdapter<String> arrayAdapter ;
                ArrayList<String> arrayList = new ArrayList<String>();
                for(DataSnapshot datasnapshot:dataSnapshot.getChildren()){
                    Users userEncontrado = datasnapshot.getValue(Users.class);
                    String Nombre = userEncontrado.getNombre();
                    String Medico = userEncontrado.getMedico();
                    if(Medico.equals(idL)){
                        arrayList.add(Nombre);
                    }

                }
                arrayAdapter = new ArrayAdapter<String>(Lista_PacientesActivity.this,android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mButtonNewPaciente =(Button) findViewById(R.id.btnNewPaciente);

        mButtonNewPaciente.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lista_PacientesActivity.this , RegisterActivity.class));
            }
        });



    }
}
