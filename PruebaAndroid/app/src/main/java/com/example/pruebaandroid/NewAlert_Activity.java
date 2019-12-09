package com.example.pruebaandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NewAlert_Activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;

    private EditText editText_nombreAlertaNuevo;
    private EditText editText_fechaInicioNuevo;
    private EditText editText_dosisNuevo;
    private EditText editText_duracionNuevo;
    private Spinner mSpinnerFrecuencia;
    private Spinner mSpinnerIntervalo;
    private TimePicker timePickerHoraInicio;

    private Button mButtonGuardar;

    private String nombreAlerta;
    private String fechaInicio;
    private String dosis;
    private String duracion;
    private String frecuencia;
    private String intervalo;
    private String horaInicio;

    String idL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alert_);

        mAuth = FirebaseAuth.getInstance();
        idL=mAuth.getCurrentUser().getUid();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        editText_nombreAlertaNuevo = (EditText)findViewById(R.id.editText_nombreAlertaNuevo);
        editText_fechaInicioNuevo = (EditText)findViewById(R.id.editText_fechaInicioNuevo);
        editText_dosisNuevo = (EditText)findViewById(R.id.editText_dosisNuevo);
        editText_duracionNuevo = (EditText)findViewById(R.id.editText_duracionNuevo);
        mSpinnerFrecuencia = (Spinner) findViewById(R.id.id_spinner_frecuencia);
        mSpinnerIntervalo = (Spinner) findViewById(R.id.id_spinner_intervalo);
        timePickerHoraInicio = (TimePicker) findViewById(R.id.id_horaInicioNuevo);

        mButtonGuardar = (Button)findViewById(R.id.button_guardar);

        mButtonGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                nombreAlerta = editText_nombreAlertaNuevo.getText().toString();
                fechaInicio = editText_fechaInicioNuevo.getText().toString();
                dosis=editText_dosisNuevo.getText().toString();
                duracion = editText_duracionNuevo.getText().toString();
                frecuencia = mSpinnerFrecuencia.getSelectedItem().toString();
                intervalo = mSpinnerIntervalo.getSelectedItem().toString();
                if(!nombreAlerta.isEmpty() && !fechaInicio.isEmpty() ){
                    registerAlert();
                }else{
                    Toast.makeText( NewAlert_Activity.this ,"Debe Completar todos los datos",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void registerAlert(){
        Toast.makeText(NewAlert_Activity.this ,"Succesfull",Toast.LENGTH_SHORT).show();

        Map<String,Object> map = new HashMap<>();
        map.put("Nombre",nombreAlerta);
        map.put("FechaInicio",fechaInicio);
        map.put("Dosis",dosis);
        map.put("Duracion",duracion);
        map.put("Paciente",idL);
        map.put("Frecuencia",frecuencia);
        map.put("Intervalo",intervalo);
        //String id = "A00002";
        String id = mDataBase.push().getKey();
        mDataBase.child("Alertas").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task2) {
                if(task2.isSuccessful()){
                    startActivity(new Intent(NewAlert_Activity.this,SuccesUser.class));
                    //finish();
                    Intent alertas = new Intent(NewAlert_Activity.this,Alertas_Activity.class);
                    startActivity(alertas);
                }else{
                    Toast.makeText( NewAlert_Activity.this ,"No se pudo crear la alerta",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
