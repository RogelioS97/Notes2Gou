package com.ivanfrescas.notes2gou;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ivanfrescas.notes2gou.entidades.Grupos;
import com.ivanfrescas.notes2gou.utlidades.Utilidades;

public class AgregarGrupo extends AppCompatActivity {

    private EditText nombre, profesor, correo, paginaweb;
    private Button btnagregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_grupo);


        btnagregar = findViewById(R.id.btnagregar);
        nombre = findViewById(R.id.etnombre);
        correo = findViewById(R.id.etcorreo);
        profesor = findViewById(R.id.etprofesor);
        paginaweb = findViewById(R.id.etpaginaweb);


        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarGrupos();
            }
        });
    }

    public void registrarGrupos(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_grupos",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();



        if (nombre.getText().toString().isEmpty() || profesor.getText().toString().isEmpty()){
            Toast.makeText(this,"Ingresa el nombre de la materia y el nombre del profesor",Toast.LENGTH_SHORT).show();
        } else {
            if (correo.getText().toString().isEmpty()){ correo.setText("Sin correo electronico");}
            if (paginaweb.getText().toString().isEmpty()) { paginaweb.setText("Sin pagina web"); }


            ContentValues values = new ContentValues();
            values.put(Utilidades.GRUPOS_NOMBRE,nombre.getText().toString());
            values.put(Utilidades.GRUPOS_PROFESOR,profesor.getText().toString());
            values.put(Utilidades.GRUPOS_CORREO,correo.getText().toString());
            values.put(Utilidades.GRUPOS_PAGINA,paginaweb.getText().toString());



            Long idResultante = db.insert(Utilidades.TABLA_GRUPOS,Utilidades.GRUPOS_NOMBRE,values);

            Toast.makeText(getApplicationContext(),"id = " + idResultante,Toast.LENGTH_SHORT).show();
            db.close();
            onBackPressed();
        }



    }
}
