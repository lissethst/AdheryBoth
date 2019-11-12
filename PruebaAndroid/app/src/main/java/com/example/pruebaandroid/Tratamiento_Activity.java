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

import com.example.pruebaandroid.model.Tratamientos;
import com.example.pruebaandroid.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tratamiento_Activity extends AppCompatActivity {

    private Button mButtonNewTratamiento;
    private DatabaseReference databaseReference;
    DatabaseReference referenciaPaciente;
    private ListView listView;
    private FirebaseAuth mAuth;
    String idL;
    String idPac;
    String nombreDevuelto = "";
    private Users nuevo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamiento_);

        databaseReference = FirebaseDatabase.getInstance().getReference("Tratamientos");
        referenciaPaciente = FirebaseDatabase.getInstance().getReference("Users");
        listView = (ListView) findViewById(R.id.list_trata);
        mAuth = FirebaseAuth.getInstance();
        idL=mAuth.getCurrentUser().getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayAdapter<String> arrayAdapter ;
                ArrayList<String> arrayList = new ArrayList<String>();
                int cont =0;
                for(DataSnapshot datasnapshot:dataSnapshot.getChildren()){
                    Tratamientos trataEncontrado = datasnapshot.getValue(Tratamientos.class);
                    String idMedico = trataEncontrado.getIdMedico();
                    cont++;
                    String idPaciente = trataEncontrado.getIdPaciente();
                    String name = trataEncontrado.getNombrePaciente();
                  // Toast.makeText( Tratamiento_Activity.this ,cont+".."+trataEncontrado.getIdMedico(),Toast.LENGTH_SHORT).show();

//                    String Fecha = trataEncontrado.getFechaRegistro();
                    if(idMedico.equals(idL)){
//                       idPac = idPaciente;
//                        Toast.makeText( Tratamiento_Activity.this ,idPac ,Toast.LENGTH_SHORT).show();
//                        obtenerNombrePaciente();
//                        Toast.makeText( Tratamiento_Activity.this ,nombreDevuelto,Toast.LENGTH_SHORT).show();
                            arrayList.add(name);

                    }

                }
                arrayAdapter = new ArrayAdapter<String>(Tratamiento_Activity.this,android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        mButtonNewTratamiento = findViewById(R.id.btnNewTrata);

        mButtonNewTratamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tratamiento_Activity.this , Regist_PacieTrata_Activity.class));
            }
        });
    }

    private void obtenerNombrePaciente( ){


        referenciaPaciente.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot datasnapshot:dataSnapshot.getChildren()){
                    Users userEncontrado = datasnapshot.getValue(Users.class);
                    String id;
                    id = datasnapshot.getKey();
                    String Nombre = userEncontrado.getNombre();
                    String Apellido = userEncontrado.getApellidos();


                    if(id.equals(idPac)){
                        nuevo = new Users();
                        nuevo.setNombre(Nombre+" "+Apellido);
                       //nombreDevuelto = Nombre +" " +Apellido;
                         Toast.makeText( Tratamiento_Activity.this ,nuevo.getNombre(),Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
