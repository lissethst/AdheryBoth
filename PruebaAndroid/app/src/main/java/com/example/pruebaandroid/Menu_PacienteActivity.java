package com.example.pruebaandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Menu_PacienteActivity extends AppCompatActivity {
    CardView card_view_chat;
    CardView card_view_perfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__paciente);

        card_view_chat = (CardView) findViewById(R.id.id_chat);
        card_view_perfil = (CardView) findViewById(R.id.id_perfil_paciente) ;

        card_view_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://bot.dialogflow.com/024d1435-e0a9-41af-90bb-9bedf7fcacb3");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        card_view_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(Menu_PacienteActivity.this,Perfil_Paciente.class);
                startActivity(perfil);
            }
        });



    }
}
