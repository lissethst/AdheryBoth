package com.example.pruebaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Alertas_Activity extends AppCompatActivity {

    private Button mButtonNewAlerta;
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
    }


}
