package com.example.pruebaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class Lista_PacientesActivity extends AppCompatActivity {
    private Button mButtonNewPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__pacientes);
        mButtonNewPaciente =(Button) findViewById(R.id.btnNewPaciente);

        mButtonNewPaciente.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lista_PacientesActivity.this , RegisterActivity.class));
            }
        });

    }
}
