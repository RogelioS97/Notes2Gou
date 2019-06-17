package com.ivanfrescas.notes2gou;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nombreEvento, ubicacion, fechadesde, horadesde;
    private EditText descripcion;

    private Button guardar, cancelar, hora, fecha;

    private int fdia, fmes, faño, horas, minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nombreEvento = findViewById(R.id.edtNombreEvento);
        ubicacion = findViewById(R.id.edtUbicacion);
        fechadesde = findViewById(R.id.edtFechaDesde);
        horadesde = findViewById(R.id.edtHoraInicio);
        descripcion = findViewById(R.id.edtDescripcion);

        Bundle bundle = getIntent().getExtras();
        int dia = 0, mes = 0, año = 0;
        dia = bundle.getInt("dia");
        mes = bundle.getInt("mes");
        año = bundle.getInt("año");
        fechadesde.setText(dia + " - " + mes + " - " + año);


        guardar = findViewById(R.id.btnGuardar);
        cancelar = findViewById(R.id.btnCancelar);
        hora = findViewById(R.id.btnHora);
        fecha = findViewById(R.id.btnFecha);
        guardar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        hora.setOnClickListener(this);
        fecha.setOnClickListener(this);

        final Calendar c = Calendar.getInstance();
        faño = c.get(Calendar.YEAR);
        fmes = c.get(Calendar.MONTH);
        fdia = c.get(Calendar.DAY_OF_MONTH);

    }


    @Override
    public void onClick(View view) {

        if (view.getId() == guardar.getId()) {
            if(nombreEvento.getText().toString().isEmpty()){
                Toast.makeText(getApplication(),"Ingresa el nombre del evento",Toast.LENGTH_SHORT).show();
            } else {
                BDSQLite bd = new BDSQLite(getApplication(), "Agenda", null, 1);
                SQLiteDatabase db = bd.getWritableDatabase();


                String sql = "insert into eventos " +
                        "(nombreEvento, ubicacion, fechadesde, horadesde, fechahasta, horahasta," +
                        "descripcion) values('" +
                        nombreEvento.getText() +
                        "','" + ubicacion.getText() +
                        "','" + fechadesde.getText() +
                        "','" + horadesde.getText() +
                        "','" + "" +
                        "','" + "" +
                        "','" + descripcion.getText() + "')";

                try {
                    db.execSQL(sql);
                    nombreEvento.setText("");
                    ubicacion.setText("");
                    fechadesde.setText("");
                    horadesde.setText("");
                    descripcion.setText("");
                    Toast.makeText(getApplication(), "Evento creado", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplication(), "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
                this.finish();
            }
        } else if(view.getId() == hora.getId()) {
            final Calendar c = Calendar.getInstance();
            horas = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if (minute < 10) {
                        horadesde.setText(hourOfDay + ":0" + minute);
                    } else {
                        horadesde.setText(hourOfDay + ":" + minute);
                    }

                }
            },horas,minutos,false);
            timePickerDialog.show();
        } else if(view.getId() == fecha.getId()){


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fechadesde.setText(dayOfMonth + " - " + (month + 1) + " - " + year);
                }
            },faño,fmes,fdia);
            datePickerDialog.show();
        } else{
            this.finish();
            return;
        }
    }


}
