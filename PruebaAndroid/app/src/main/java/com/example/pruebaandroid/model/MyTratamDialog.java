package com.example.pruebaandroid.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.pruebaandroid.R;
import com.example.pruebaandroid.Regist_PacieTrata_Activity;

import java.util.ArrayList;

public class MyTratamDialog extends DialogFragment {

    private EditText txtNombreMedicamento;
    private EditText txtCantidadMedicamento;
    private EditText txtConcentracion;

    private Button mButtonAgregarMedicamento;

    public ArrayAdapter<String> arrayAdapter ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_add_medicamento,container,false);
        mButtonAgregarMedicamento = view.findViewById(R.id.btnSalvTrata);
        txtNombreMedicamento = view.findViewById(R.id.txt_nameAddMedi);
        txtCantidadMedicamento = view.findViewById(R.id.txt_cantidad);
        txtConcentracion = view.findViewById(R.id.txt_concentracion);



        mButtonAgregarMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entrada = txtNombreMedicamento.getText().toString();
                String medCant = txtCantidadMedicamento.getText().toString();
                String medConcen = txtConcentracion.getText().toString();

                Medicamento nuevoMed = new Medicamento();
                nuevoMed.setNombre(entrada);
                nuevoMed.setCantidad(medCant);
                nuevoMed.setConcentracion(medConcen);

                if(!entrada.equals("")){
                   // ((Regist_PacieTrata_Activity)getActivity()).mTextPueba.setText(entrada);
                    ((Regist_PacieTrata_Activity)getActivity()).arrayList.add(entrada);
                    ((Regist_PacieTrata_Activity)getActivity()).arrayMedica.add(nuevoMed);
                    arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,((Regist_PacieTrata_Activity)getActivity()).arrayList);
                    ((Regist_PacieTrata_Activity)getActivity()).mListMedica.setAdapter(arrayAdapter);


                }
                getDialog().dismiss();
            }
        });
        return view;
    }
}
