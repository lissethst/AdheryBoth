package com.example.pruebaandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

   // private EditText mEditTextDni;
    private EditText mEditTextName;
    private EditText mEditTextApellidos;
    private EditText mEditTextEdad;
    private EditText mEditTextCelular;
    private EditText mEditTextGlucosa;
    private EditText mEditTextColesterol;
    private EditText mEditTextSangre;

    private EditText mEditTextCorreo;
    private EditText mEditTextContra;


    private Button mButtonRegistro;
    private Button mButtonLogin;
    private Spinner mSpinnerType;

    private String dni;
    private String nombre="";
    private String apellido="";
    private String edad;
    private String glucosa="-";
    private String celular="";
    private String sangre="-";
    private String colesterol="-";
    private String correo="";
    private String contrasena="";

    FirebaseAuth mAuth;
    DatabaseReference mDataBase;

    String idL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        idL=mAuth.getCurrentUser().getUid();
        mDataBase = FirebaseDatabase.getInstance().getReference();


        mEditTextName = (EditText)findViewById(R.id.txt_nombrePersona);
        mEditTextApellidos = (EditText)findViewById(R.id.txt_apePersona);
        mEditTextEdad = (EditText)findViewById(R.id.txt_edadPersona);
        mEditTextCelular = (EditText)findViewById(R.id.txt_celularPersona);
        mEditTextGlucosa = (EditText)findViewById(R.id.txt_glucosaPersona);
        mEditTextColesterol = (EditText)findViewById(R.id.txt_colesterolPersona);
        mEditTextSangre = (EditText) findViewById(R.id.txt_sangrePersona);

        mEditTextCorreo = (EditText)findViewById(R.id.txt_correoPersona);
        mEditTextContra = (EditText)findViewById(R.id.txt_pasPersona);
        mButtonRegistro = (Button)findViewById(R.id.btnRegistroPaciente);

        mEditTextSangre.setVisibility(View.GONE);
        mEditTextGlucosa.setVisibility(View.GONE);
        mEditTextColesterol.setVisibility(View.GONE);


        mSpinnerType =(Spinner) findViewById(R.id.spinner_type);
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this,R.array.types,android.R.layout.simple_spinner_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerType.setAdapter(adapterType);
        mSpinnerType.setOnItemSelectedListener(this);


        mButtonRegistro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dni = "76433485";

                nombre = mEditTextName.getText().toString();
                apellido = mEditTextApellidos.getText().toString();
                edad=mEditTextEdad.getText().toString();
                celular=mEditTextCelular.getText().toString();
                colesterol=mEditTextColesterol.getText().toString();
                sangre=mEditTextSangre.getText().toString();
                glucosa=mEditTextGlucosa.getText().toString();
                correo=mEditTextCorreo.getText().toString();
                contrasena=mEditTextContra.getText().toString();

                if(!nombre.isEmpty()){
                    registerUser();
                }else{
                    Toast.makeText( RegisterActivity.this ,"Debe Completar el nombre",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void registerUser(){

        mAuth.createUserWithEmailAndPassword(correo,contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this ,"Succesfull",Toast.LENGTH_SHORT).show();

                    Map<String,Object> map = new HashMap<>();
                    map.put("Nombre",nombre);
                    map.put("Apellidos",apellido);
                    map.put("Edad",edad);
                    map.put("Celular",celular);


                    map.put("Correo",correo);
                    map.put("Contrasena",contrasena);
                    map.put("Tipo",type_user);
                    String id =mAuth.getCurrentUser().getUid();

                    map.put("Medico",idL);
                    map.put("Glucosa",glucosa);
                    map.put("Colesterol",colesterol);
                    map.put("Sangre",sangre);

                    mDataBase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(RegisterActivity.this,SuccesUser.class));
                                finish();
                            }else{
                                Toast.makeText( RegisterActivity.this ,"No se pudo crear los datos correctamente",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }else{
                    Toast.makeText( RegisterActivity.this ,"No se pudo registrar este usuario",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    String type_user ;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       String text = parent.getItemAtPosition(position).toString();
       if(text.equals("Paciente")){
           type_user = "2";
           idL = mAuth.getCurrentUser().getUid();
           mEditTextSangre.setVisibility(View.VISIBLE);
           mEditTextGlucosa.setVisibility(View.VISIBLE);
           mEditTextColesterol.setVisibility(View.VISIBLE);
       }else{
           idL = "-";
           type_user = "1";
           mEditTextSangre.setText("-");
           mEditTextColesterol.setText("-");
           mEditTextGlucosa.setText("-");

           mEditTextSangre.setVisibility(View.GONE);
           mEditTextGlucosa.setVisibility(View.GONE);
           mEditTextColesterol.setVisibility(View.GONE);
       }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
