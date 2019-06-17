package com.ivanfrescas.notes2gou;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewEventsActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private SQLiteDatabase db;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);



        listView = findViewById(R.id.ltvListaEventos);
        listView.setOnItemLongClickListener(this);

        Bundle bundle = getIntent().getExtras();
        int dia,mes,año;
        dia = mes = año = 0;

        dia = bundle.getInt("dia");
        mes = bundle.getInt("mes");
        año = bundle.getInt("año");
        String cadena = dia + " - " + mes + " - " + año;

        BDSQLite bd = new BDSQLite(getApplicationContext(),"Agenda", null,1);
        db = bd.getReadableDatabase();

        String sql = "select * from eventos where fechadesde='"+cadena+"'";
        Cursor c;

        String nombre, fechadesde,  descripcion, ubicacion, hora;
        try {
            c = db.rawQuery(sql, null);
            arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
            if (c.moveToNext()){
                do {
                    nombre = c.getString(1);
                    ubicacion = c.getString(2);
                    fechadesde = c.getString(3);
                    hora = c.getString(4);
                    descripcion = c.getString(7);
                    if(ubicacion.isEmpty()){ ubicacion = "Sin ubicación"; }
                    if(hora.isEmpty()){ hora = "Sin hora asignada";}

                    arrayAdapter.add(nombre + "\n" + ubicacion + "\n" + fechadesde + "\n" + hora + "\n" + descripcion);
                } while (c.moveToNext());
                listView.setAdapter(arrayAdapter);
            } else {
                Toast.makeText(getApplication(),"No hay eventos",Toast.LENGTH_SHORT).show();
                this.finish();
            }
        } catch (Exception ex) {
            Toast.makeText(getApplication(),"Error:" + ex.getMessage(), Toast.LENGTH_SHORT).show();
            this.finish();
        }


    }

    private void eliminar(String dato) {

        String []datos = dato.split("\n");

        String sql ="delete from eventos where nombreEvento='"+datos[0]+"' and" + " fechadesde='" + datos[2] +"'";
        Log.d("mytag", datos[0]);


        try {
            arrayAdapter.remove(dato);
            listView.setAdapter(arrayAdapter);
            db.execSQL(sql);
            Toast.makeText(getApplication(),"Evento eliminado",Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(getApplication(),"Error" + ex.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        CharSequence []items = new CharSequence[2];
        items[0] = "Eliminar evento";
        items[1] = "Cancelar";
        builder.setTitle("Eliminar Evento").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {

                    eliminar(adapterView.getItemAtPosition(position).toString());

                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        return false;
    }
}
