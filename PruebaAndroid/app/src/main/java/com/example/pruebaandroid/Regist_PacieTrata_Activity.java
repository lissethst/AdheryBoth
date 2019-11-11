package com.example.pruebaandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pruebaandroid.model.Medicamento;
import com.example.pruebaandroid.model.MyTratamDialog;
import com.example.pruebaandroid.model.Tratamiento;
import com.example.pruebaandroid.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Regist_PacieTrata_Activity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    private DatabaseReference mDataRef;
    private DatabaseReference mDataTrataRef;
    private DatabaseReference mDataMedicaRef;
    private EditText mTextViewUser;
    private Spinner mSpinnerType;
    private EditText mContIndica;


    private Button mButtonOpenAddMedica;
    public ListView mListMedica;
    public ArrayList<Medicamento> arrayMedica;
    public ArrayList<String> arrayList;
    // ArrayList<Medicamento> arrayTrataMedi;
    String idPac;
    String idTrata;
    String fecha;

    private ImageButton mButtonBuscarTra;
    private Button mButtonSalvarTrata;
    private EditText txtPaciTrata;
    private EditText txtPacEncontrado;

    public String indicaciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist__pacie_trata_);

        mAuth = FirebaseAuth.getInstance();
        arrayMedica = new ArrayList<Medicamento>();

        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDataRef = FirebaseDatabase.getInstance().getReference("Users");
        mDataTrataRef = FirebaseDatabase.getInstance().getReference("Tratamientos");
      //  mDataMedicaRef = FirebaseDatabase.getInstance().getReference("Tratamientos");

        mTextViewUser = findViewById(R.id.txt_TrataEspecia);
        mContIndica = findViewById(R.id.text_cont_indicaciones);
        mButtonBuscarTra = findViewById(R.id.imageButtonPaci);
        mButtonSalvarTrata = findViewById(R.id.btnSalvTrata);

        //--------------------
        mButtonOpenAddMedica = findViewById(R.id.btnAddMedicamento);
        mListMedica = findViewById(R.id.list_medica);
        arrayList = new ArrayList<String>();
        //arrayTrataMedi = new ArrayList<Medicamento>();
        //--------------------
        txtPaciTrata = findViewById(R.id.txtBuscPaciTrata);
        txtPacEncontrado = findViewById(R.id.txt_idPaci);
        mSpinnerType =(Spinner) findViewById(R.id.spinner_typetrata);
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this,R.array.types_medica,android.R.layout.simple_spinner_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerType.setAdapter(adapterType);
        mSpinnerType.setOnItemSelectedListener(this);
        obtenerDatos();

        final Calendar c = Calendar.getInstance();
        int año = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        fecha = formatter.format(c.getTime());



        mButtonOpenAddMedica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTratamDialog trata = new MyTratamDialog();
                trata.show(getSupportFragmentManager(),"MyTratamDialog");

            }
        });

        mButtonSalvarTrata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Guardar los datos del Tratamiento en nodos y subnodos
                indicaciones=mContIndica.getText().toString();
                Map<String,Object> map = new HashMap<>();
                map.put("IdPaciente",idPac);
                String id =mAuth.getCurrentUser().getUid();
                map.put("IdMedico",id);
                map.put("FechaReg",fecha);
                map.put("Indicaciones",indicaciones);

              //  mDataTrataRef.push().setValue(map); //Crea el Tratamiento con datos iniciales
                //Busca el id del paciente y trae el id del tratamiento asociado
                idTrata = mDataTrataRef.push().getKey();
                mDataTrataRef.child(idTrata).setValue(map);

                //Recorre el arrayList de Medicamentos y agrega
                for(int i=0;i<arrayMedica.size();i++){
                    Medicamento nuevoMe = new Medicamento();
                    nuevoMe.setNombre(arrayMedica.get(i).getNombre());
                    nuevoMe.setCantidad(arrayMedica.get(i).getCantidad());
                    nuevoMe.setConcentracion(arrayMedica.get(i).getConcentracion());
                     mDataTrataRef.child(idTrata).child("Medicamentos").child("Medi"+(i+1)).setValue(nuevoMe);
                    // mDataMedicaRef.push().setValue(nuevoMe);
                }

                Toast.makeText( Regist_PacieTrata_Activity.this ,"Se registro con Exito el Tratamiento ",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        mButtonBuscarTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // baseDatabase.getInstance(mDataBase = Fire).getReference("Users");
                mDataRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot datasnapshot:dataSnapshot.getChildren()){
                            Users nameEncontrado = datasnapshot.getValue(Users.class);
                            idPac = datasnapshot.getKey();
                            String Nombre = nameEncontrado.getNombre();
                            String Apellidos = nameEncontrado.getApellidos();
                            if(Nombre.equals(txtPaciTrata.getText().toString())){
                                Toast.makeText( Regist_PacieTrata_Activity.this ,"Aqui "+idPac,Toast.LENGTH_SHORT).show();
                                txtPacEncontrado.setText(Nombre +" "+Apellidos);
                                txtPaciTrata.setText("");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



    }

    private void obtenerDatos(){
        String id =mAuth.getCurrentUser().getUid();
        mDataBase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.child("Nombre").getValue().toString();
                    mTextViewUser.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
String type_trata;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if(text.equals("Farmacologico")){
            type_trata = "F001";
        }else{

            type_trata = "N001";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
