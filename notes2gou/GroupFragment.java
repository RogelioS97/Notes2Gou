package com.ivanfrescas.notes2gou;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ivanfrescas.notes2gou.entidades.Grupos;
import com.ivanfrescas.notes2gou.utlidades.Utilidades;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {


    public GroupFragment() {
        // Required empty public constructor
    }

    ListView listViewGrupos;
    ArrayList<String> listaInformacion;
    ArrayList<Grupos> listaGrupos;
    ConexionSQLiteHelper conn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group, container, false);



        listViewGrupos = view.findViewById(R.id.listViewGrupos);

        conn = new ConexionSQLiteHelper(view.getContext(),"bd_grupos",null,1);

        consultarListaGrupos();

        final ArrayAdapter adaptador = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1,listaInformacion);
        listViewGrupos.setAdapter(adaptador);


        listViewGrupos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final int pos = position;

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                CharSequence []items = new CharSequence[3];
                items[0] = "Ver detalles";
                items[1] = "Eliminar Materia";
                items[2] = "Cancelar";
                builder.setTitle("Ver Grupos").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (i == 0) {

                            Grupos group = listaGrupos.get(pos);
                            Intent intent = new Intent(getActivity(),DetalleGrupos.class);

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("grupo",group);

                            intent.putExtras(bundle);
                            startActivity(intent);

                        } else if (i == 1) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                            builder.setTitle("Confirmar");
                            builder.setMessage("¿Estas seguro de eliminar esta materia?");

                            builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {

                                    // Do nothing, but close the dialog
                                    SQLiteDatabase dbe = conn.getWritableDatabase();
                                    String[] nom = {listaGrupos.get(pos).getNombre().toString()};

                                    dbe.delete(Utilidades.TABLA_GRUPOS,Utilidades.GRUPOS_NOMBRE+"=?",nom);
                                    Toast.makeText(getActivity(),"Materia eliminada ",Toast.LENGTH_SHORT).show();
                                    dbe.close();
                                    dialog.dismiss();

                                }
                            });

                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Do nothing
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AgregarGrupo.class);
                startActivity(intent);
            }
        });


        return view;
    }

    private void consultarListaGrupos() {

        SQLiteDatabase db = conn.getReadableDatabase();

        Grupos grupo = null;

        listaGrupos = new ArrayList<Grupos>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_GRUPOS,null);

        while (cursor.moveToNext()){
            grupo = new Grupos();
            grupo.setNombre(cursor.getString(0));
            grupo.setProfesor(cursor.getString(1));
            grupo.setCorreo(cursor.getString(2));
            grupo.setPaginaweb(cursor.getString(3));


            listaGrupos.add(grupo);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i=0; i<listaGrupos.size(); i++){
            listaInformacion.add(listaGrupos.get(i).getNombre() + "\nProfesor: " + listaGrupos.get(i).getProfesor());
        }

    }



}
