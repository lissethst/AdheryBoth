package com.example.pruebaandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu_MedicoActivity extends AppCompatActivity {

    private CardView card_PerfilMedico;
    private CardView card_Pacientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__medico);
        card_PerfilMedico = (CardView) findViewById(R.id.id_perfil_especialista);
        card_Pacientes = findViewById(R.id.id_pacientes);

        card_Pacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(Menu_MedicoActivity.this,Lista_PacientesActivity.class);
                startActivity(perfil);
            }
        });
    }
}
