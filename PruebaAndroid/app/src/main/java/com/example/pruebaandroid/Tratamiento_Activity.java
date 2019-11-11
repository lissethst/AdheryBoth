package com.example.pruebaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tratamiento_Activity extends AppCompatActivity {

    private Button mButtonNewTratamiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamiento_);
        mButtonNewTratamiento = findViewById(R.id.btnNewTrata);

        mButtonNewTratamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tratamiento_Activity.this , Regist_PacieTrata_Activity.class));
            }
        });
    }
}
