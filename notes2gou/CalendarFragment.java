package com.ivanfrescas.notes2gou;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment implements CalendarView.OnDateChangeListener {


    private CalendarView calendarView;


    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = v.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(this);










        return v;
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        CharSequence []items = new CharSequence[3];
        items[0] = "Agregar Evento";
        items[1] = "Ver Eventos";
        items[2] = "Cancelar";

        final int dia, mes, año;
        dia = dayOfMonth;
        mes = month + 1;
        año = year;


        builder.setTitle("Seleccione una tarea").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    Intent intent = new Intent(getContext(),AddActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("dia" , dia);
                    bundle.putInt("mes", mes);
                    bundle.putInt("año", año);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (i == 1) {
                    Intent intent = new Intent(getContext(),ViewEventsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("dia" , dia);
                    bundle.putInt("mes", mes);
                    bundle.putInt("año", año);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    return;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
