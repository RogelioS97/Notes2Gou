package com.ivanfrescas.notes2gou;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.ivanfrescas.notes2gou.utlidades.Utilidades;

public class CrearCuenta extends AppCompatActivity {


    EditText campoCorreo,campoNombre,campoEscuela,campoContraseña;
    Button btn_crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        campoCorreo=(EditText)findViewById(R.id.correo);
        campoNombre=(EditText)findViewById(R.id.nombre);
        campoEscuela=(EditText)findViewById(R.id.escuela);
        campoContraseña=(EditText)findViewById(R.id.contraseña_crear);

        btn_crear=(Button)findViewById(R.id.btn_crearCuenta);
        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registrarAlumnos();
            }
        });

    }



    private void registrarAlumnos() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this ,"bd_alumnos",null,1);

        SQLiteDatabase db =conn.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(Utilidades.CAMPO_CORREO,campoCorreo.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_ESCUELA,campoEscuela.getText().toString());
        values.put(Utilidades.CAMPO_CONTRASEÑA,campoContraseña.getText().toString());

        Long idResulatnte=db.insert(Utilidades.TABLA_ALUMNO,Utilidades.CAMPO_NOMBRE,values);

        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Registro: "+idResulatnte+" exitoso", Toast.LENGTH_SHORT);

        toast1.show();

        db.close();
        limpiar();
    }


    public void limpiar(){

        campoCorreo.setText("");
        campoContraseña.setText("");
        campoEscuela.setText("");
        campoNombre.setText("");

    }

/*public void showToolbar(String tittle , boolean upButton){
Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
setSupportActionBar(toolbar);
}*/




}
