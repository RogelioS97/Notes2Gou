package com.ivanfrescas.notes2gou;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    TextView tvFrases,eventoshoy;
    View v;
    ImageView calendarioVacio;
    private SQLiteDatabase db;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_home, container, false);

        tvFrases = v.findViewById(R.id.tvFrases);
        eventoshoy = v.findViewById(R.id.eventoshoy);
        calendarioVacio = v.findViewById(R.id.calendariovacio);


        Random rand = new Random();
        int n = rand.nextInt(12);
        tvFrases.setText(llenadoFrases(n));

        listView = v.findViewById(R.id.ltvListaEventos);
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = (calendar.get(Calendar.MONTH) + 1);
        int año = calendar.get(Calendar.YEAR);
        String cadena = dia + " - " + mes + " - " + año;
        BDSQLite bd = new BDSQLite(getContext(),"Agenda", null,1);
        db = bd.getReadableDatabase();
        String sql = "select * from eventos where fechadesde='"+cadena+"'";
        Cursor c;

        String nombre, fechadesde,  descripcion, ubicacion, hora;
        try {
            c = db.rawQuery(sql, null);
            arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);
            if (c.moveToNext()){
                eventoshoy.setText("Eventos del dia");
                calendarioVacio.setVisibility(View.GONE);
                do {
                    nombre = c.getString(1);
                    ubicacion = c.getString(2);
                    fechadesde = c.getString(3);
                    hora = c.getString(4);
                    descripcion = c.getString(7);
                    if(ubicacion.isEmpty()){ ubicacion = "Sin ubicación"; }
                    if(hora.isEmpty()){ hora = "Sin hora asignada";}

                    arrayAdapter.add(nombre + "\n" + hora + "\n" + descripcion);
                } while (c.moveToNext());
                listView.setAdapter(arrayAdapter);
            } else {
                calendarioVacio.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Toast.makeText(getActivity(),"Error:" + ex.getMessage(), Toast.LENGTH_SHORT).show();

        }




        return v;
    }



    public String llenadoFrases (int n){
        String[] frases = {"Los sueños se realizan cuando mantienes el compromiso con ellos",
                "Por muy larga que sea la tormenta, el sol siempre vuelve a brillar entre las nubes.",
                "Mis sueños son mentiras que algún día dejarán de serlo.",
                "Cada día es una serie de conflictos entre el camino correcto y el camino fácil",
                "Haz siempre tu mejor esfuerzo. Puede que no siempre ganes, pero siempre estarás orgullo de ti mismo.",
                "Te das cuenta de que tú eres asi. Que nunca te vas a conformar con ir andando pudiendo volar.",
                "Trabaja en silencio, que el éxito se encargue de hacer todo el ruido.",
                "Si tú sabes lo que vales, ve y consigue lo que mereces.",
                "No tengas miedo a fracasar, ten miedo a no intentarlo.",
                "Un viaje muy largo se inicia con un sólo paso.",
                "No tienes que ser grande para empezar, pero debes empezar para ser grande.",
                "Soy un optimista. No tiene mucho sentido ser otra cosa."};

        return frases[n];
    }

}
