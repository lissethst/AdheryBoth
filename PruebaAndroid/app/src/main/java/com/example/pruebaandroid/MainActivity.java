package com.example.pruebaandroid;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText mEditTextName;
    private EditText mEditTextApellidos;
    private EditText mEditTextCorreo;
    private EditText mEditTextContra;
    private Button mButtonRegistro;
    private Button mButtonLogin;

    private String nombre="";
    private String apellido="";
    private String correo="";
    private String contrasena="";

    FirebaseAuth mAuth;
    DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        mEditTextName = (EditText)findViewById(R.id.txt_nombrePersona);
        mEditTextApellidos = (EditText)findViewById(R.id.txt_apePersona);
        mEditTextCorreo = (EditText)findViewById(R.id.txt_correoPersona);
        mEditTextContra = (EditText)findViewById(R.id.txt_pasPersona);
        mButtonRegistro = (Button)findViewById(R.id.btnRegistroPaciente);
        mButtonLogin = (Button)findViewById(R.id.btnCuenta);

        mButtonRegistro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                nombre = mEditTextName.getText().toString();
                apellido = mEditTextApellidos.getText().toString();
                correo=mEditTextCorreo.getText().toString();
                contrasena=mEditTextContra.getText().toString();

                if(!nombre.isEmpty()){
                    registerUser();
                }else{
                    Toast.makeText( MainActivity.this ,"Debe Completar el nombre",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , LoginActivity.class));
                Toast.makeText( MainActivity.this ,"eS POR lOGIN BT",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(correo,contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String,Object> map = new HashMap<>();
                    map.put("Nombre",nombre);
                    map.put("Apellidos",apellido);
                    map.put("Correo",correo);
                    map.put("Contrasena",contrasena);

                    String id =mAuth.getCurrentUser().getUid();
                    mDataBase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(MainActivity.this,SuccesUser.class));
                                finish();
                            }else{
                                Toast.makeText( MainActivity.this ,"No se pudo crear los datos correctamente",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }else{
                    Toast.makeText( MainActivity.this ,"No se pudo registrar este usuario",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(  mAuth.getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this , SuccesUser.class));
            finish();
        }

    }
}
