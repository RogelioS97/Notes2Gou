package com.ivanfrescas.notes2gou;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ivanfrescas.notes2gou.utlidades.Utilidades;

import static android.icu.text.MessagePattern.ArgType.SELECT;

public class Login extends AppCompatActivity {

    Button btn_login;
    EditText username,contraseña;
ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        conn=new ConexionSQLiteHelper(this,"bd_alumnos",null,1);

    btn_login=(Button) findViewById(R.id.login);
    username=(EditText)findViewById(R.id.username);
    contraseña=(EditText)findViewById(R.id.password);

    btn_login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ingreso();
            //ingresar2();
        }
    });

    }

    public void goCrearCuenta(View view ){
        Intent intent = new Intent(this,CrearCuenta.class);
        startActivity(intent);


    }

    public void ingreso(){

        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={username.getText().toString()};
        String[] campos={Utilidades.CAMPO_CONTRASEÑA};

                try{


                    Cursor cursor=db.query(Utilidades.TABLA_ALUMNO,campos,Utilidades.CAMPO_NOMBRE+"=?",parametros,null,null,null);

                    cursor.moveToFirst();
                    String contra;



                    contra=cursor.getString(0);




                    if(contra.equals(contraseña.getText().toString())){

                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Binevenido Hermano Merengue", Toast.LENGTH_SHORT);

                        toast1.show();
                        Intent intent = new Intent(this,MainActivity.class);
                        startActivity(intent);

                    }


                    if(contraseña.getText().toString().equals("")){
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Ingrese contraseña", Toast.LENGTH_SHORT);

                        toast1.show();


                    }




                    cursor.close();



                }
                    catch(Exception e){
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Usuairo o contraseña incorrecta", Toast.LENGTH_SHORT);

                        toast1.show();
                        limpiar();


                    }




    }
public void limpiar(){
        contraseña.setText("");
        username.setText("");

}


    }


