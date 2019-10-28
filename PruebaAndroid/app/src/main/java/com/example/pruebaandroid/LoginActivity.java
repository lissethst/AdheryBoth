package com.example.pruebaandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText mEditTextCorreo;
    private EditText mEditTextContra;
    private Button mButtonLogin;

    FirebaseAuth mAuth;
    private String correo="";
    private String contrasena="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

                    Toast.makeText( LoginActivity.this ,"Complete los campos, por favor",Toast.LENGTH_SHORT).show();

                    Toast.makeText( LoginActivity.this ,"Complete Ojala se pueda",Toast.LENGTH_SHORT).show();

                    Toast.makeText( LoginActivity.this ,"Complete creo que si se pudo",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void loginUser(){
        mAuth.signInWithEmailAndPassword(correo,contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this , SuccesUser.class));
                   finish();

                }else{
                    Toast.makeText( LoginActivity.this ,"No se pudo iniciar sesion, compruebe los datos",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
