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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText mEditTextCorreo;
    private EditText mEditTextContra;
    private Button mButtonLogin;


    FirebaseAuth mAuth;
    private String correo="";
    private String contrasena="";
    private DatabaseReference mDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mEditTextCorreo = (EditText)findViewById(R.id.txt_correoPersona);
        mEditTextContra = (EditText)findViewById(R.id.txt_pasPersona);
        mButtonLogin = (Button) findViewById(R.id.btnLoginInter);

        mButtonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                correo=mEditTextCorreo.getText().toString();
                contrasena=mEditTextContra.getText().toString();

                if(!correo.isEmpty() && !contrasena.isEmpty()){
                    loginUser();
                }else{

                    Toast.makeText( MainActivity.this ,"Complete los campos, por favor",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private void loginUser(){

        mAuth.signInWithEmailAndPassword(correo,contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this , SelecPerfilActivity.class));
                    finish();

                }else{
                    Toast.makeText( MainActivity.this ,"No se pudo iniciar sesion, compruebe los datos",Toast.LENGTH_SHORT).show();

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
